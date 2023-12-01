import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Act implements ActionListener {
	JTextArea addr;
	JTextArea prt;

	HashMap<Integer, cartella> cartelle;

	JButton connect;
	JButton disconnect;
	JButton start;
	JButton stop;
	JTextArea console;

	static public Socket s;
	static public Scanner in;
	static public PrintWriter out;
	static public Thread t;
	static public Download d;

	Act(JTextArea addr, JTextArea prt, HashMap<Integer, cartella> cartelle , JButton connect,
		JButton disconnect, JButton start, JButton stop, JTextArea console){
		this.addr = addr;
		this.prt = prt;
		this.cartelle = cartelle;
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.console = console;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("Connect")) {
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);

				s = new Socket(addr.getText(), Integer.parseInt(prt.getText()));
			}
			if (e.getActionCommand().equals("Disconnect")){
				connect.setEnabled(true);
				start.setEnabled(false);
				stop.setEnabled(false);
				disconnect.setEnabled(false);

				out.println("disconnect");
				out.flush();
			}
			if (e.getActionCommand().equals("Start")){
				start.setEnabled(false);
				stop.setEnabled(true);
				connect.setEnabled(false);

				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
				d = new Download(cartelle, in, out, console);
				t = new Thread(d);
				t.start();
				out.println("start");
				out.flush();
			}
			if (e.getActionCommand().equals("Stop")){
				start.setEnabled(true);

				for (cartella i : cartelle.values()){
					i.setBackground(Color.WHITE);
				}

				out.println("stop");
				out.flush();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
