package pong.game_objects;

import java.awt.Graphics2D;

import pong.Pong;
import pong.constants.Const;

public class PaddleBot extends PongObject {

	public PaddleBot(final int x, final int y) {
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
		
		if (Pong.p1LeftPressed) {
			x -= 5;
		} else if (Pong.p1RightPressed) {
			x += 5;
		}
	}

}
