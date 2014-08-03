package pong.game_objects;

import java.awt.Graphics2D;

import pong.constants.Const;
import pong.update.PointDouble;

public class Ball extends PongObject {

	public Ball() {
		width = Const.BALL_DIAMETER.intValue();
		height = Const.BALL_DIAMETER.intValue();

		double x = Const.GUI_WIDTH.intValue() / 2 - (width / 2);
		double y = Const.GUI_HEIGHT.intValue() / 2 - (height / 2);
		pos = new PointDouble(x, y);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.fillOval((int) pos.x, (int) pos.y, width, height);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
