import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Download implements Runnable {
	Scanner in;
	HashMap<Integer, cartella> cartelle;
	PrintWriter out;
	JTextArea console;
	Download(HashMap<Integer, cartella> cartelle, Scanner in, PrintWriter out, JTextArea console) {
		this.console = console;
		this.in = in;
		this.out = out;
		this.cartelle = cartelle;
	}

	@Override
	public void run() {
		String n = in.nextLine();
		while (!n.equals("+")) {
			if (cartelle.containsKey(Integer.parseInt(n))) {
				cartelle.get(Integer.parseInt(n)).setBackground(Color.GREEN);
			}
			console.append("estratto: " + n + "\n" );
			n = in.nextLine();
		}
		console.append("-fine partita-");
	}
}
