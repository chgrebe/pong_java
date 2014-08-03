package pong.game_objects;

import java.awt.Graphics2D;

import pong.Pong;
import pong.constants.Const;
import pong.update.Vector2D;

public class PaddleBot extends PongObject {

	public PaddleBot(final int x, final int y) {
		acceleration = new Vector2D(0, 0);
		this.x = x;
		this.y = y;
		width = Const.PADDLE_WIDTH.intValue();
		height = Const.PADDLE_HEIGHT.intValue();
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawRect((int) x, (int) y, width, height);

	}

	@Override
	public void update() {

		if (Pong.p1LeftPressed) {
			accelerate(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (Pong.p1RightPressed) {
			accelerate(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (acceleration.length > 0) {
			slow();
		}
	}


	private void slow() {
		if (acceleration.length > Const.PADDLE_FRICTION.doubleValue()) {
			acceleration.length -= Const.PADDLE_FRICTION.doubleValue();
		} else {
			acceleration.length = 0;
		}
	}

	private void accelerate(Vector2D v) {
		acceleration = acceleration.add(v);
		if (acceleration.length > Const.PADDLE_MAX_SPEED.doubleValue()) {
			acceleration.length = Const.PADDLE_MAX_SPEED.doubleValue();
		}
	}



}
