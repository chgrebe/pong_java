package pong.game_objects;

import java.awt.Graphics2D;

import pong.update.PointDouble;

public abstract class PongObject {

	public int height;
	public PointDouble pos;
	public int width;

	public abstract void draw(final Graphics2D g);

	public abstract void update();

}
