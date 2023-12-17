import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Act implements ActionListener {


	Act(){

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 50; i++){
			for(int j = 0; j < 50; j++){
				if (e.getSource().equals(Window.mat1[i][j])){
					if (Window.mat[i][j] == 1)
						Window.mat[i][j] = 0;
					Window.mat[i][j] = 1;
				}
			}
		}
	}
}
