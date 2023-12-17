
public class Main {
	static public void main(String[] args) throws InterruptedException {
		Window f = new Window();

		Alg.randMat(Window.mat, 50);
		Alg.printMat(Window.mat, 50);

		for (int i = 0; true; i++){
			Thread.sleep(400);

			Alg.matReader(Window.mat, 50);
			Alg.printMat(Window.mat, 50);
		}
	}
}
