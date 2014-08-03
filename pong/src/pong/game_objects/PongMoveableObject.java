package pong.game_objects;

import pong.update.PointDouble;
import pong.update.Vector2D;

public abstract class PongMoveableObject extends PongObject {
	
	public Vector2D acceleration;
	public double maxSpeed = 1.0;
	public double friction = 0.1;
	
	
	public void accelerate(Vector2D v) {
		acceleration = acceleration.add(v);
		if (acceleration.length > maxSpeed) {
			acceleration.length = maxSpeed;
		}
	}
	
	public void move() {
		pos = pos.add(PointDouble.parseFrom(acceleration));
	}
	
	public void slow(double friction) {
		if (acceleration.length > friction) {
			acceleration.length -= friction;
		} else {
			acceleration.length = 0;
		}
	}
}
