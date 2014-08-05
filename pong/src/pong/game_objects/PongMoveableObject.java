package pong.game_objects;

import pong.constants.Const;
import pong.update.PointDouble;
import pong.update.Vector2D;

public abstract class PongMoveableObject extends PongObject {

	public Vector2D acceleration;
	public double friction = 0.1;
	public double maxSpeed = 1.0;

	public void accelerate(final Vector2D v) {
		acceleration = acceleration.add(v);
		if (acceleration.length > maxSpeed) {
			acceleration.length = maxSpeed;
		}
	}

	public void move() {
		pos = pos.add(PointDouble.parseFrom(acceleration));
		if (pos.x < 0) {
			pos.x = 0;
		} else if (pos.x > Const.GUI_WIDTH.intValue() - width) {
			pos.x = Const.GUI_WIDTH.intValue() - width;
		}
	}

	public void slow(final double friction) {
		if (friction <= 0) {
			return;
		}
		if (acceleration.length > friction) {
			acceleration.length -= friction;
		} else {
			acceleration.length = 0;
		}
	}
}
