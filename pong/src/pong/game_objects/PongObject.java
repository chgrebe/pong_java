package pong.game_objects;

import java.awt.Graphics2D;

import pong.update.Vector2D;

public abstract class PongObject {

	public Vector2D acceleration;
	public int height;
	public int width;
	public double x;
	public double y;

	public abstract void draw(final Graphics2D g);

	public abstract void update();

}
