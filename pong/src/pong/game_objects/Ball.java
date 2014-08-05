package pong.game_objects;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import pong.Pong;
import pong.constants.Const;
import pong.update.PointDouble;
import pong.update.Vector2D;

public class Ball extends PongMoveableObject {
	private final double maxAngleVariation = 15;
	private final int maxHeight = Const.GUI_HEIGHT.intValue()
			- (Const.PADDLE_HEIGHT.intValue() + Const.PADDLE_VERTICAL_DISTANCE.intValue());
	private final int minHeight = Const.PADDLE_HEIGHT.intValue() + Const.PADDLE_VERTICAL_DISTANCE.intValue();
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
//		final double[] roughDirections = { 60, 120, 240, 300 };
		final double[] roughDirections = { 120,120 };
		final double angle = getRandomAngle(roughDirections, maxAngleVariation);
		return new Vector2D(angle, Const.BALL_INITIAL_SPEED.doubleValue());
	}

	@Override
	public void update() {
		updatePosition();
		move();
	}

	private void bounceLeft() {
		if (acceleration.angle <= 180) {
			acceleration.angle = 180 - acceleration.angle;
		} else if (acceleration.angle > 180) {
			acceleration.angle = 540 - acceleration.angle;
		}
		pos.x -= 0;
	}

	private void bounceRight() {
		if (acceleration.angle <= 180) {
			System.out.println(acceleration);
			acceleration.angle = 180 - acceleration.angle;
			System.out.println(acceleration);
			System.out.println("1______________________");
		} else if (acceleration.angle > 180) {
			System.out.println(acceleration);
			acceleration.angle = 540 - acceleration.angle;
			System.out.println(acceleration);
			System.out.println("2______________________");
		}
		pos.x += 0;
	}

	private boolean collisionDetected() {
		Shape selfShape;
		Shape paddleShape;
		Area selfArea;
		Area paddleArea, compArea;

		for (final PongObject p : Pong.get().paddleObjects) {
			selfShape = new Ellipse2D.Double(pos.x, pos.y, width, height);
			selfArea = new Area(selfShape);
			paddleShape = new Rectangle2D.Double(p.pos.x, p.pos.y, p.width, p.height);
			paddleArea = new Area(paddleShape);
			compArea = selfArea;
			System.out.println(compArea.getBounds());
			System.out.println(paddleArea.getBounds());
			compArea.intersect(paddleArea);
			System.out.println(compArea.getBounds());
			System.out.println("_______________________________________");
			if (!compArea.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	private double getRandomAngle(final double[] roughDirections, final double maxVariation) {
		final int chosenDirection = rand.nextInt(roughDirections.length - 1);
		double angleVariation = rand.nextDouble() * maxVariation;
		if (rand.nextInt(1) != 0) {
			angleVariation *= -1;
		}
		return roughDirections[chosenDirection] + angleVariation;
	}

	private void updatePosition() {
		System.out.println("Acceleration before updatePosition()" + acceleration);

		// player bot scores
		if (pos.y + (height / 2) < minHeight) {
			System.out.println("minHeight");
			// player top scores
		} else if (pos.y + (height / 2) > maxHeight) {
			System.out.println("maxHeight");
			// hit the left border
		} else if (pos.x <= 0) {
			System.out.println("bounceRight");
			// pos.add(PointDouble.parseFrom(new Vector2D(-acceleration.angle,
			// 20)));
			bounceRight();
			// hit the right border
		} else if (pos.x >= Const.GUI_WIDTH.intValue() - width) {
			System.out.println("bounceLeft");
			// pos.add(PointDouble.parseFrom(new Vector2D(-acceleration.angle,
			// 20)));
			bounceLeft();
			// collided with a paddle
		} else if (collisionDetected()) {
			acceleration.length += Const.BALL_SPEED_INCREASE_ON_COLLISION.doubleValue();
			
			// pos.add(PointDouble.parseFrom(new Vector2D(-acceleration.angle,
			// 20)));

			System.out.println("___________________________________________");
			System.out.println("Collision detected!");
			System.out.println("___________________________________________");
			// top paddle
			if (pos.y < Const.GUI_HEIGHT.doubleValue() / 2) {
				if (acceleration.angle > 180 && acceleration.angle <= 270) {
					acceleration.angle = 360 - acceleration.angle;
				} else if (acceleration.angle > 270 && acceleration.angle <= 360) {
					acceleration.angle = 360 - acceleration.angle;
				}
				// bottom paddle
			} else {
				if (acceleration.angle > 0 && acceleration.angle <= 90) {
					acceleration.angle = 360 - acceleration.angle;
				} else if (acceleration.angle > 90 && acceleration.angle <= 180) {
					acceleration.angle = 360 - acceleration.angle;
				}
			}

			// just continue on the normal route (do nothing)
		} else {
		}
	}

}
