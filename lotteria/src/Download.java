import javax.swing.*;
import java.util.Scanner;

public class Download implements Runnable{
	JButton connect;
	JButton disconnect;
	JButton start;
	JButton stop;
	JButton randomize;
	JButton reset;
	LotteryComboBox[][] mat;
	Scanner in;

	Download(Scanner in, JButton connect, JButton disconnect, JButton start,
			 JButton stop, JButton randomize, JButton reset, LotteryComboBox[][] mat){
		this.in = in;
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.randomize = randomize;
		this.reset = reset;
		this.mat = mat;
	}

	@Override
	public void run() {
		while(in.hasNext()){
			String msg = in.nextLine();
			int c = 0;
			System.out.println(msg + " " + c);
			if (msg.equals("done")){
				start.setEnabled(true);
				stop.setEnabled(false);
				randomize.setEnabled(true);
				reset.setEnabled(true);

				for (int i = 0; i < 5; i++){
					for (int j = 0; j < 5; j++){
						if (mat[i][j].isChecked()){
							c++;
						}
					}
				}

				JOptionPane.showMessageDialog(null, "hai indovinato " + c + " numeri!");

				for (int i = 0; i < 5; i++){
					for (int j = 0; j < 5; j++){
						mat[i][j].setReadOnly(false);
					}
				}
				return;
			}

			else if (msg.equals("interrupted")){

				JOptionPane.showMessageDialog(null, "Hai interrotto l'estrazione");

				for (int i = 0; i < 5; i++){
					for (int j = 0; j < 5; j++){
						mat[i][j].setReadOnly(false);
					}
				}
			}

			else {
				String[] s = msg.split(":");
				int ix = Integer.parseInt(s[0]) - 1;

				for (int i = 0; i < 5; i++) {
					if (mat[ix][i].getSelectedItem().equals(s[1])) {
						mat[ix][i].setChecked(true);
					}
				}

			}
		}
	}
}
