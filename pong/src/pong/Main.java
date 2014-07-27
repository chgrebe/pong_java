package pong;

public class Main {

	public static void main(String[] args) {
		Sim sim = Sim.get();
		new Thread(sim).start();
	}

}
