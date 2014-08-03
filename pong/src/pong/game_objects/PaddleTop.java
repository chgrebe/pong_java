package pong.game_objects;

import java.awt.Graphics2D;

import pong.Pong;
import pong.constants.Const;
import pong.update.Vector2D;

public class PaddleTop extends Paddle {

	public PaddleTop(double x, double y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawRect((int) pos.x, (int) pos.y, width, height);
	}

	@Override
	public void update() {
		if (Pong.p2LeftPressed) {
			accelerate(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (Pong.p2RightPressed) {
			accelerate(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (acceleration.length > 0) {
			slow(friction);
		}
		move();
	}

}
