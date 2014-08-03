package pong;

public class Main {

	public static void main(final String[] args) {
		final Pong pong = Pong.get();
		new Thread(pong).start();
	}

}
