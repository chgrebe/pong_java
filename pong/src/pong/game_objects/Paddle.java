package pong.game_objects;

import java.awt.Graphics2D;

import pong.constants.Const;
import pong.update.PointDouble;
import pong.update.Vector2D;

public abstract class Paddle extends PongMoveableObject {

	public Paddle(final double x, final double y) {
		pos = new PointDouble(x, y);
		acceleration = new Vector2D(0, 0);
		width = Const.PADDLE_WIDTH.intValue();
		height = Const.PADDLE_HEIGHT.intValue();
		friction = Const.PADDLE_FRICTION.doubleValue();
		maxSpeed = Const.PADDLE_MAX_SPEED.doubleValue();
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawRect((int) pos.x, (int) pos.y, width, height);
	}

}
