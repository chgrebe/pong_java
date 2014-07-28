package pong.game_objects;

import java.awt.Graphics2D;

import pong.constants.Const;

public class Ball extends PongObject {

	public Ball() {
		width = Const.BALL_DIAMETER.intValue();
		height = Const.BALL_DIAMETER.intValue();

		x = Const.GUI_WIDTH.intValue() / 2 - (width / 2);
		doubleX = Const.GUI_WIDTH.intValue() / 2;

		y = Const.GUI_HEIGHT.intValue() / 2 - (height / 2);
		doubleY = Const.GUI_HEIGHT.intValue() / 2;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.fillOval(x, y, width, height);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
