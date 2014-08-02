package pong.update;

public class PointDouble {

	public static PointDouble add(final PointDouble p1, final PointDouble p2) {
		final double x = p1.x + p2.x;
		final double y = p1.y + p2.y;

		return new PointDouble(x, y);
	}

	public static PointDouble parseFrom(final Vector2D v) {
		final double x = Math.cos(Math.toRadians(v.angle)) * v.length;
		final double y = Math.sin(Math.toRadians(v.angle)) * v.length;

		return new PointDouble(x, y);
	}

	public double x;
	public double y;

	public PointDouble(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public PointDouble add(final PointDouble p) {
		final double newX = x + p.x;
		final double newY = y + p.y;

		return new PointDouble(newX, newY);
	}

	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y;
	}

}
