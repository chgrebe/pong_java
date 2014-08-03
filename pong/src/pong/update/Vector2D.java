package pong.update;

import pong.update.PointDouble;

public class Vector2D {

	public static Vector2D add(final Vector2D v1, final Vector2D v2) {
		final PointDouble p1 = PointDouble.parseFrom(v1);
		final PointDouble p2 = PointDouble.parseFrom(v2);
		final PointDouble addedPoint = PointDouble.add(p1, p2);

		return Vector2D.parseFrom(addedPoint);
	}

	public static Vector2D parseFrom(final PointDouble p) {
		if (p.x == 0 && p.y == 0) {
			return new Vector2D(0, 0);
		}

		final double length = Math.abs(Math.sqrt((p.x * p.x) + (p.y * p.y)));
		double angle = Math.toDegrees(Math.asin(p.y / length));

		// top left in coordinate system
		if (p.x < 0 && p.y >= 0)
			angle = 180 - angle;
		// bottom left in coordinate system
		else if (p.x <= 0 && p.y < 0)
			angle = 180 + Math.abs(angle);
		// bottom right in coordinate system
		else if (p.x > 0 && p.y < 0)
			angle = 360 - Math.abs(angle);

		return new Vector2D(angle, length);
	}

	public double angle;
	public double length;

	public Vector2D(final double angle, final double length) {
		this.angle = angle;
		this.length = length;
	}

	public Vector2D add(final Vector2D v) {
		return Vector2D.add(this, v);
	}

	@Override
	public String toString() {
		return "Length: " + length + ", Angle: " + angle + "°.";
	}

}
