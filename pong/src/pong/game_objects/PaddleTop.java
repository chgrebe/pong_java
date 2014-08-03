package pong.game_objects;

import pong.Pong;
import pong.constants.Const;
import pong.update.Vector2D;

public class PaddleTop extends Paddle {

	public PaddleTop(final double x, final double y) {
		super(x, y);
	}

	@Override
	public void update() {
		if (Pong.get().pressedKeys.get("p2Left").booleanValue()) {
			accelerate(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (Pong.get().pressedKeys.get("p2Right").booleanValue()) {
			accelerate(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (acceleration.length > 0) {
			slow(friction);
		}
		move();
		checkGameBorders();
	}

}
