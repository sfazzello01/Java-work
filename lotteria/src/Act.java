import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class Act implements ActionListener {
	JButton connect;
	JButton disconnect;
	JButton start;
	JButton stop;
	JButton randomize;
	JButton reset;
	static LotteryComboBox[][] mat;
	JTextArea addr;
	JTextArea prt;

	Scanner in;
	static Socket s;
	static PrintWriter out;
	Thread t;
	Download d;


	Act(JButton connect, JButton disconnect, JButton start,
		JButton stop, JButton randomize, JButton reset, LotteryComboBox[][] mat, JTextArea addr, JTextArea prt){
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.randomize = randomize;
		this.reset = reset;
		this.mat = mat;
		this.addr = prt;
		this.prt = prt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {

			if (e.getActionCommand().equals("Randomize")){
				randomize.setEnabled(false);
				for (int i = 0; i < 5; i++){
					ArrayList<Integer> arr = new ArrayList<>();
					int j = 0;
					while(arr.size() < 5){
						int rnd = (int)(Math.random()*90 + 1);
						if (!arr.contains(rnd)){
							arr.add(rnd);
							mat[i][j].setChecked(false);
							mat[i][j].setSelectedIndex(rnd);
							j++;
						}
					}
				}
				randomize.setEnabled(true);
			}

			if (e.getActionCommand().equals("Connect")){
				connect.setEnabled(false);
				start.setEnabled(true);
				randomize.setEnabled(true);
				reset.setEnabled(true);

				s = new Socket("localhost", 4400);
			}

			if (e.getActionCommand().equals("Start")){
				start.setEnabled(false);
				stop.setEnabled(true);
				randomize.setEnabled(false);
				reset.setEnabled(false);

				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());

				for (int i = 0; i < 5; i++){
					for (int j = 0; j < 5; j++){
						mat[i][j].setReadOnly(true);
						if (!mat[i][j].isNumberSelected())
						{
							start.setEnabled(true);
							stop.setEnabled(false);
							reset.setEnabled(true);
							randomize.setEnabled(true);
							JOptionPane.showMessageDialog(null, "ERRORE MIAOOO");
							return;
						}
					}
				}

				out.println("start");
				out.flush();

				d = new Download(in, connect, disconnect, start, stop, randomize, reset, mat);
				t = new Thread(d);
				t.start();
			}

			if (e.getActionCommand().equals("Stop")){
				stop.setEnabled(false);
				start.setEnabled(true);
				randomize.setEnabled(true);
				disconnect.setEnabled(true);
				reset.setEnabled(true);

				out.println("stop");
				out.flush();
			}

			if (e.getActionCommand().equals("Reset")){
				reset.setEnabled(false);
				randomize.setEnabled(true);

				for (int i = 0; i < 5; i++){
					for (int j = 0; j < 5; j++){
						mat[i][j].setSelectedIndex(0);
					}
				}
			}

			if (e.getActionCommand().equals("Disconnect")){
				disconnect.setEnabled(false);
				connect.setEnabled(true);
				start.setEnabled(false);
				stop.setEnabled(false);
				randomize.setEnabled(false);
				reset.setEnabled(false);

				out.println("Disconnect");
				out.flush();
			}

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
