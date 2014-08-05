package pong.game_objects;

import pong.Pong;
import pong.constants.Const;
import pong.update.Vector2D;

public class PaddleBot extends Paddle {

	public PaddleBot(final double x, final double y) {
		super(x, y);
	}

	@Override
	public void update() {
		if (Pong.get().pressedKeys.get("p1Left").booleanValue()) {
			accelerate(new Vector2D(180, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (Pong.get().pressedKeys.get("p1Right").booleanValue()) {
			accelerate(new Vector2D(0, Const.PADDLE_ACCELERATION.doubleValue()));
		} else if (acceleration.length > 0) {
			slow(friction);
		}
		// System.out.println("Bot Paddle acceleration before move: " +
		// acceleration);
		// System.out.println("Bot Paddle position before move: " + pos);
		move();
		checkGameBorders();
	}

}
