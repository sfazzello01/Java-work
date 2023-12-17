import java.awt.*;

public class Alg {
	public static int[][] matrix(int n) {
		return new int[n][n];
	}

	public static void randMat(int[][] mat, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int rd = (int)(Math.random()*5);
				if (rd == 0) {
					mat[i][j] = 1;
				} else {
					mat[i][j] = 0;
				}
			}
		}
	}

	public static void printMat(int[][] mat, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] == 0)
					System.out.print("  ");
				else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
	}

	public static int life(int[][] mat, int i, int j, int n) {
		int spell = 0;
		if (i > 0) {
			if (mat[i - 1][j] == 1)
				spell++;
		}
		if (j > 0) {
			if (mat[i][j - 1] == 1)
				spell++;
		}
		if (i < n - 1) {
			if (mat[i + 1][j] == 1)
				spell++;
		}
		if (j < n - 1) {
			if (mat[i][j + 1] == 1)
				spell++;
		}
		if (i > 0 && j > 0) {
			if (mat[i - 1][j - 1] == 1)
				spell++;
		}
		if (i > 0 && j < n - 1) {
			if (mat[i - 1][j + 1] == 1)
				spell++;
		}
		if (i < n - 1 && j > 0) {
			if (mat[i + 1][j - 1] == 1)
				spell++;
		}
		if (i < n - 1 && j < n - 1) {
			if (mat[i + 1][j + 1] == 1)
				spell++;
		}
		return spell;
	}

	public static void matReader(int[][] mat, int n) {
		int m;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				m = mat[i][j];
				int lf = life(mat, i, j, n);
				if (lf == 3)
				{
					if (m == 0)
					{
						mat[i][j] = 1;
						Window.mat1[i][j].setBackground(Color.BLACK);
					}
				}
				else if (lf < 2)
				{
					if (m == 1) {
						mat[i][j] = 0;
						Window.mat1[i][j].setBackground(Color.WHITE);
					}
				}
				else if (lf > 3)
				{
					if (m == 1) {
						mat[i][j] = 0;
						Window.mat1[i][j].setBackground(Color.WHITE);
					}
				}
			}
		}
	}

}

