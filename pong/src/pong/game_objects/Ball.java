package pong.game_objects;

import java.awt.Graphics2D;

import pong.update.Acceleration;

public class Ball extends PongObject {
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		acceleration = new Acceleration(x, y);
	}

	@Override
	public void render(final Graphics2D g) {
		g.drawOval(x, y, width, height);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
