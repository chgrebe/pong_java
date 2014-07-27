package pong;

public class Main {

	public static void main(String[] args) {
		Pong pong = Pong.get();
		new Thread(pong).start();
	}

}
