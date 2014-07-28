package pong.game_objects;

import java.awt.Graphics2D;

import pong.constants.Const;

public class Paddle extends PongObject {

	public Paddle(final int x, final int y) {
		this.x = x;
		this.y = y;
		doubleX = x;
		doubleY = y;
		width = Const.PADDLE_WIDTH.intValue();
		height = Const.PADDLE_HEIGHT.intValue();
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawRect(x, y, width, height);

	}

	@Override
	public void update() {

	}

}
