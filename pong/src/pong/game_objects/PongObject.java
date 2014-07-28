package pong.game_objects;

import java.awt.Graphics2D;

import pong.update.Acceleration;

public abstract class PongObject {

	public Acceleration acceleration = new Acceleration();
	public double doubleX;
	public double doubleY;
	public int height;
	public int width;
	public int x;
	public int y;

	public abstract void draw(final Graphics2D g);

	public abstract void update();

}
