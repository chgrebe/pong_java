package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pong.constants.Const;
import pong.controls.PongMouseListener;
import pong.controls.PongKeyListener;
import pong.game_objects.Ball;
import pong.game_objects.PaddleTop;
import pong.game_objects.PaddleBot;

public class Pong implements Runnable {

	private static Pong me = new Pong();

	public static Pong get() {
		return me;
	}

	public Ball ball = new Ball();
	public BufferStrategy bufferStrategy;
	public Canvas canvas;
	public JFrame frame;
	public double gameSpeed = 1.0;
	public boolean notFinished = true;
	public boolean notPaused = true;
	public PaddleBot paddleBot;
	public PaddleTop paddleTop;

	public JPanel panel;

	public Map<String, Boolean> pressedKeys;

	// TESTING
	public float x, y = 0;

	private Pong() {
		frame = new JFrame("Basic Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue()));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue());
		canvas.setIgnoreRepaint(true);

		canvas.addMouseListener(new PongMouseListener());
		canvas.addKeyListener(new PongKeyListener());

		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		frame.setBounds(250, 100, Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue());
		frame.pack();
		canvas.requestFocus();

		setObjects();
		setKeys();
	}

	public void decreaseGameSpeed() {
		if (gameSpeed - Const.GAME_SPEED_STEP_SIZE.doubleValue() > Const.GAME_SPEED_MIN.doubleValue()) {
			gameSpeed -= Const.GAME_SPEED_STEP_SIZE.doubleValue();
		} else {
			gameSpeed = Const.GAME_SPEED_MIN.doubleValue();
		}
		System.out.printf("Gamespeed decreased to %f.%n", Double.valueOf(gameSpeed));
	}

	public void increaseGameSpeed() {
		if (gameSpeed + Const.GAME_SPEED_STEP_SIZE.doubleValue() < Const.GAME_SPEED_MAX.doubleValue()) {
			gameSpeed += Const.GAME_SPEED_STEP_SIZE.doubleValue();
		} else {
			gameSpeed = Const.GAME_SPEED_MAX.doubleValue();
		}
		System.out.printf("Gamespeed increased to %f.%n", Double.valueOf(gameSpeed));
	}

	public void pauseGame() {
		notPaused = notPaused ? false : true;
	}

	@Override
	public void run() {
		double accumulatorUpdate = 0.0;
		double currentRender;
		double currentUpdate;
		double deltaRender;
		double deltaUpdate;
		double lastRender;
		double lastUpdate;

		final double desiredGUIDelta = (1000) / Const.GUI_DESIRED_FPS.doubleValue();
		final double desiredGameDelta = (1000) / Const.GAME_DESIRED_FPS.doubleValue();
		System.out.println("Desired Game Delta: " + desiredGameDelta);
		System.out.println("Desired GUI Delta: " + desiredGUIDelta);
		render();
		try {
			Thread.sleep(1000);
		} catch (final Exception e) {

		}
		lastRender = System.currentTimeMillis();
		lastUpdate = System.currentTimeMillis();

		while (notFinished) {
			if (notPaused) {
				currentUpdate = System.currentTimeMillis();
				deltaUpdate = currentUpdate - lastUpdate;
				lastUpdate = currentUpdate;
				accumulatorUpdate += deltaUpdate;

				// System.out.println("accumulatorUpdate before update: " +
				// accumulatorUpdate);
				while (accumulatorUpdate >= desiredGameDelta / gameSpeed) {
					update();
					accumulatorUpdate -= (desiredGameDelta / gameSpeed);
					// System.out.printf("Current accumulatorUpdate while updating: %f%n",
					// Double.valueOf(accumulatorUpdate));
					try {
						Thread.sleep(0);
					} catch (final InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// System.out.println("accumulatorUpdate rest after update: " +
				// accumulatorUpdate);
			} else {
				lastUpdate = System.currentTimeMillis();
			}
			currentRender = System.currentTimeMillis();
			deltaRender = currentRender - lastRender;
			// System.out.println("Time since last render: " + deltaRender);
			if (deltaRender >= desiredGUIDelta) {
				// System.out.println("Rendering.");
				render();
				lastRender = currentRender;
				try {
					Thread.sleep(0);
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					final long nextRenderIn = (long) Math.ceil(desiredGUIDelta - deltaRender);
					long nextUpdateIn = (long) Math.ceil(desiredGameDelta - accumulatorUpdate);
					// System.out.println("Skipped rendering, instead sleeping for "
					// + Math.min(nextRenderIn, nextUpdateIn) +
					// "ms.");
					if (nextUpdateIn < 0) {
						nextUpdateIn = 1;
					}

					Thread.sleep(Math.min(nextRenderIn, nextUpdateIn));
					// Thread.sleep(nextRenderIn);

				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void render() {
		final Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue());
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	private void setKeys() {
		pressedKeys = new HashMap<>();
		pressedKeys.put("p1Left", Boolean.valueOf(false));
		pressedKeys.put("p1Right", Boolean.valueOf(false));
		pressedKeys.put("p2Left", Boolean.valueOf(false));
		pressedKeys.put("p2Right", Boolean.valueOf(false));
	}

	private void setObjects() {
		ball = new Ball();

		final int x = Const.GUI_WIDTH.intValue() / 2 - (Const.PADDLE_WIDTH.intValue() / 2);
		final int topY = Const.PADDLE_VERTICAL_DISTANCE.intValue();
		paddleTop = new PaddleTop(x, topY);

		final int botY = Const.GUI_HEIGHT.intValue() - Const.PADDLE_VERTICAL_DISTANCE.intValue()
				- Const.PADDLE_HEIGHT.intValue();
		paddleBot = new PaddleBot(x, botY);

		// System.out.printf("Paddle init:%nX: %d%ntopY: %d%nbotY: %d%n", x,
		// topY, botY);
		// System.out.println("GUI_HEIGHT: " + Const.GUI_HEIGHT);
		// System.out.println("PADDLE_HEIGHT: " + Const.PADDLE_HEIGHT);
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void render(final Graphics2D g) {
		// System.out.println("here");
		ball.draw(g);
		paddleBot.draw(g);
		paddleTop.draw(g);

		g.fillRect((int) x, (int) y, 200, 200);
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void update() {
		ball.update();
		paddleBot.update();
		paddleTop.update();

		x += 0.6;
		y += 0.6;
		if (x > Const.GUI_WIDTH.intValue() - 200) {
			x = 0;
			y = 0;
		}
	}

}
