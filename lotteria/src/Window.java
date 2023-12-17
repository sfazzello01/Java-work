import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Window extends JFrame {

	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	JButton start= new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton reset = new JButton("Reset");
	JButton randomize = new JButton("Randomize");

	JPanel nord = new JPanel();
	JPanel sud = new JPanel();
	JPanel lbl = new JPanel();
	JPanel cell = new JPanel();

	JLabel address = new JLabel("Server Address");
	JLabel port = new JLabel("port");
	JLabel r1 = new JLabel("ruota 1");
	JLabel r2 = new JLabel("ruota 2");
	JLabel r3 = new JLabel("ruota 3");
	JLabel r4 = new JLabel("ruota 4");
	JLabel r5 = new JLabel("ruota 5");

	JTextArea addr = new JTextArea("localhost");
	JTextArea prt = new JTextArea("4400");

	LotteryComboBox[][] mat = new LotteryComboBox[5][5];

	LayoutManager flow = new FlowLayout();
	LayoutManager grid = new GridLayout(5,5,3,3);
	LayoutManager grid1 = new GridLayout(5,1, 3, 3);

	Window(){
		super("Simone Fazzello 2071633");

		nord.add(address);
		nord.add(addr);
		nord.add(port);
		nord.add(prt);
		nord.add(connect);
		nord.add(disconnect);

		for (int i = 0; i < 5; i++){
			mat[i] =  new LotteryComboBox[5];
			for (int j = 0; j < 5; j++){
				mat[i][j] = new LotteryComboBox();
				cell.add(mat[i][j]);
			}
		}

		cell.setLayout(grid);
		cell.setBorder(new TitledBorder("Lotteria"));

		lbl.add(r1);
		lbl.add(r2);
		lbl.add(r3);
		lbl.add(r4);
		lbl.add(r5);

		lbl.setLayout(grid1);

		sud.add(start);
		sud.add(stop);
		sud.add(reset);
		sud.add(randomize);

		nord.setLayout(flow);
		sud.setLayout(flow);

		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);
		reset.setEnabled(false);
		randomize.setEnabled(false);

		connect.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));
		disconnect.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));
		start.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));
		stop.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));
		randomize.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));
		reset.addActionListener(new Act(connect, disconnect, start, stop, randomize, reset, mat, addr, prt));

		this.add(nord, BorderLayout.NORTH);
		this.add(cell, BorderLayout.CENTER);
		this.add(lbl, BorderLayout.WEST);
		this.add(sud, BorderLayout.SOUTH);

		this.setSize(600,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
