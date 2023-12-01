import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class random{
	random(){}
	public static int getInt(){
		return  (int)(Math.random()*90 + 1);
	}
}

class cartella extends JPanel{
	JLabel lb;
	String str;
	cartella(String str){
		this.str = str;
		lb = new JLabel(str);
		this.add(lb);
		this.setBackground(Color.WHITE);
	}
}

public class Window extends JFrame {
	JButton connect = new JButton("Connect");
	JButton disconnect  = new JButton("Disconnect");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");

	JLabel address = new JLabel("Server Address");
	JLabel port = new JLabel("Port");

	JTextArea addr = new JTextArea("localhost");
	JTextArea prt = new JTextArea("4400");
	JTextArea console = new JTextArea(20,20);

	JScrollPane scroll;

	TitledBorder border = new TitledBorder("Console");

	LayoutManager flow = new FlowLayout();
	LayoutManager grid = new GridLayout(3,5);

	JPanel nord = new JPanel();
	JPanel grd = new JPanel();
	JPanel centro = new JPanel();
	JPanel sud = new JPanel();

	ArrayList<Integer> ordinata = new ArrayList<Integer>();

	HashMap<Integer, cartella > cartelle = new HashMap<Integer, cartella>();

	Window(){
		nord.add(address);
		nord.add(addr);
		nord.add(port);
		nord.add(prt);
		nord.add(connect);
		nord.add(disconnect);

		while (ordinata.size() < 15){
			int rn = random.getInt();
			if (!ordinata.contains(rn)){
				ordinata.add(rn);
			}
		}

		Collections.sort(ordinata);

		for (int  i = 0; i <15; i++){
			cartelle.put(ordinata.get(i), new cartella(Integer.toString(ordinata.get(i))));
		}

		for (cartella i : cartelle.values()){
			grd.add(i);
		}

		scroll = new JScrollPane(console);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(border);
		centro.add(scroll);

		sud.add(start);
		sud.add(stop);

		nord.setLayout(flow);
		grd.setLayout(grid);
		centro.setLayout(flow);
		sud.setLayout(flow);

		connect.addActionListener(new Act(addr, prt, cartelle, connect, disconnect, start, stop, console));
		disconnect.addActionListener(new Act(addr, prt, cartelle, connect, disconnect, start, stop, console));
		start.addActionListener(new Act(addr, prt, cartelle, connect, disconnect, start, stop, console));
		stop.addActionListener(new Act(addr, prt, cartelle, connect, disconnect, start, stop, console));

		this.add(nord, BorderLayout.NORTH);
		this.add(grd, BorderLayout.CENTER);
		this.add(centro, BorderLayout.EAST);
		this.add(sud, BorderLayout.SOUTH);

		console.setEditable(false);
		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);

		this.pack();
		this.setSize(700,500);
		this.setVisible(true);
	}
}

