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
			moveLeft();
		} else if (Pong.p1RightPressed) {
			moveRight();
		}
	}

	private void moveLeft() {
		System.out.println(acceleration);
		acceleration = acceleration.add(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		if (acceleration.length > Const.PADDLE_MAX_SPEED.doubleValue()) {
			acceleration.length = Const.PADDLE_MAX_SPEED.doubleValue();
		}
		// Point newPosition = acceleration.getTargetFromPoint((int) x, (int)
		// y);
		// if (newPosition.x > 0) {
		// x = newPosition.x;
		// // y = newPosition.y;
		// } else {
		// x = 0;
		// // y = newPosition.y;
		// }

	}

	private void moveRight() {
		acceleration = acceleration.add(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		if (acceleration.length > Const.PADDLE_MAX_SPEED.doubleValue()) {
			acceleration.length = Const.PADDLE_MAX_SPEED.doubleValue();
		}
		// Point newPosition = acceleration.getTargetFromPoint((int) x, (int)
		// y);
		// x = newPosition.x;
		// y = newPosition.y;
	}
}
