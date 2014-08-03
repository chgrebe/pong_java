package pong.game_objects;

import java.awt.Graphics2D;
import java.util.Random;

import pong.constants.Const;
import pong.update.PointDouble;
import pong.update.Vector2D;

public class Ball extends PongMoveableObject {
	private final double maxAngleVariation = 15;
	private final Random rand = new Random();

	public Ball() {
		width = Const.BALL_DIAMETER.intValue();
		height = Const.BALL_DIAMETER.intValue();

		final double x = Const.GUI_WIDTH.intValue() / 2 - (width / 2);
		final double y = Const.GUI_HEIGHT.intValue() / 2 - (height / 2);
		pos = new PointDouble(x, y);

		acceleration = getStartAcceleration();
		maxSpeed = 50;
		friction = 0;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.fillOval((int) pos.x, (int) pos.y, width, height);

	}

	public Vector2D getStartAcceleration() {
		final double[] roughDirections = { 60, 120, 240, 300 };
		double angle = getRandomAngle(roughDirections, maxAngleVariation);
		return new Vector2D(angle, Const.BALL_INITIAL_SPEED.doubleValue());
	}

	@Override
	public void update() {
//		accelerate(acceleration);
		move();
	}

	private double getRandomAngle(final double[] roughDirections, final double maxVariation) {
		final int chosenDirection = rand.nextInt(roughDirections.length - 1);
		double angleVariation = rand.nextDouble() * maxVariation;
		if (rand.nextInt(1) != 0) {
			angleVariation *= -1;
		}
		return roughDirections[chosenDirection] + angleVariation;
	}

}
