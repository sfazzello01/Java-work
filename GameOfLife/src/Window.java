import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Window extends JFrame {

	static public int[][] mat = Alg.matrix(50);

	public static int n = 50;

	JPanel centro = new JPanel();
	public static JButton[][] mat1 = new JButton[n][n];



	Window(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				JButton b = new JButton();
				mat1[i][j] = b;
				b.setBackground(Color.WHITE);
				b.setPreferredSize(new Dimension(15,15));
				centro.add(mat1[i][j]);
			}
		}

		centro.setLayout(new GridLayout(n,n,1,1));

		centro.setBorder(new TitledBorder("Game Of Life"));

		this.add(centro, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
