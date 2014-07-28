package pong.update;

public class Acceleration {

	public double x;
	public double y;

	public Acceleration() {
		x = 0;
		y = 0;
	}
	
	public Acceleration(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public void accelerate(final double x, final double y) {
		this.x += x;
		this.y += y;
	}

}
