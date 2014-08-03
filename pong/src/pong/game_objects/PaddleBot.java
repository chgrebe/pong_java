package pong.game_objects;

import java.awt.Graphics2D;

import pong.Pong;
import pong.constants.Const;
import pong.update.Vector2D;

public class PaddleBot extends Paddle {

	public PaddleBot(double x, double y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawRect((int) pos.x, (int) pos.y, width, height);
	}

	@Override
	public void update() {
		if (Pong.p1LeftPressed) {
			accelerate(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (Pong.p1RightPressed) {
			accelerate(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (acceleration.length > 0) {
			slow(friction);
		}
		move();
	}

}
