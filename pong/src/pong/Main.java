package pong;

public class Main {

	public static void main(String[] args) {
		Pong sim = Pong.get();
		new Thread(sim).start();
	}

}
