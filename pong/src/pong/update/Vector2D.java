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
		final double length = Math.sqrt((p.x * p.x) + (p.y * p.y));
		final double angle = Math.sinh(p.y / length);

		return new Vector2D(angle, length);
	}

	public double angle;
	public double length;

	public Vector2D(final double angle, final double length) {
		this.angle = angle;
		this.length = length;
	}

	public Vector2D add(final Vector2D v) {
		final PointDouble p1 = PointDouble.parseFrom(this);
		final PointDouble p2 = PointDouble.parseFrom(v);
		final PointDouble addedPoint = p1.add(p2);

		return parseFrom(addedPoint);
	}

	@Override
	public String toString() {
		return "Length: " + length + ", Angle: " + angle + "°.";
	}

}
