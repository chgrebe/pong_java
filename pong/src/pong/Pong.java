package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import pong.constants.Const;
import pong.controls.MouseControl;
import pong.controls.PongKeyListener;
import pong.game_objects.Ball;
import pong.game_objects.PaddleTop;
import pong.game_objects.PaddleBot;

public class Pong implements Runnable {

	public static double accumulatorUpdate = 0.0;
	public static Ball ball = new Ball();
	public static BufferStrategy bufferStrategy;
	public static Canvas canvas;
	public static double currentRender;
	public static double deltaRender;
	public static JFrame frame;
	public static double gameSpeed = 1.0;
	public static boolean notFinished = true;
	public static boolean notPaused = true;
	public static boolean p1LeftPressed = false;

	public static boolean p1RightPressed = false;
	public static boolean p2LeftPressed = false;
	public static boolean p2RightPressed = false;

	public static PaddleBot paddleBot;
	public static PaddleTop paddleTop;
	public static JPanel panel;
	public static double updateCurrent;
	public static double updateDelta;

	private static Pong me = new Pong();

	public static Pong get() {
		return me;
	}

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
		panel.setSize(new Dimension(Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue()));

		// panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
		// "P1MoveLeft");
		// panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
		// "P1MoveRight");
		//
		// final Action p1MoveLeft = new AbstractAction() {
		// /**
		// *
		// */
		// private static final long serialVersionUID = -5769096915351940491L;
		//
		// @Override
		// public void actionPerformed(final ActionEvent e) {
		// x = x - 20 > 0 ? x - 20 : 0;
		// }
		// };
		// final Action p1MoveRight = new AbstractAction() {
		// /**
		// *
		// */
		// private static final long serialVersionUID = -5374910769395440871L;
		//
		// @Override
		// public void actionPerformed(final ActionEvent e) {
		// x = x + 20 < Const.GUI_WIDTH.intValue() - 200 ? x + 20 :
		// Const.GUI_WIDTH.intValue() - 200;
		// }
		// };
		//
		// panel.getActionMap().put("P1MoveLeft", p1MoveLeft);
		// panel.getActionMap().put("P1MoveRight", p1MoveRight);

		canvas = new Canvas();
		canvas.setBounds(0, 0, Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue());
		canvas.setIgnoreRepaint(true);

		canvas.addMouseListener(new MouseControl());
		canvas.addKeyListener(new PongKeyListener());

		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		frame.pack();
		canvas.requestFocus();

		setObjects();
	}

	@Override
	public void run() {

		double lastRender = System.currentTimeMillis();
		double lastUpdate = System.currentTimeMillis();

		final double desiredGUIDelta = (1000) / Const.GUI_DESIRED_FPS.doubleValue();
		final double desiredGameDelta = (1000) / Const.GAME_DESIRED_FPS.doubleValue();
		System.out.println("Desired Game Delta: " + desiredGameDelta);
		System.out.println("Desired GUI Delta: " + desiredGUIDelta);

		while (notFinished) {
			if (notPaused) {
				updateCurrent = System.currentTimeMillis();
				updateDelta = updateCurrent - lastUpdate;
				lastUpdate = updateCurrent;
				accumulatorUpdate += updateDelta;

				// System.out.println("accumulatorUpdate before update: " +
				// accumulatorUpdate);
				while (accumulatorUpdate >= desiredGameDelta / gameSpeed) {
					update(desiredGameDelta);
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
					// System.out.println("Skipped rendering, instead sleeping for "
					// + ((long) Math.ceil(desiredGUIDelta - deltaRender)) +
					// "ms.");
					Thread.sleep((long) Math.ceil((desiredGUIDelta) - deltaRender));
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
	protected void update(final double dt) {
		ball.update();
		paddleBot.update();
		paddleTop.update();

		x += dt * 0.1;
		y += dt * 0.1;
		// System.out.println("x: " + x);

		if (x > Const.GUI_WIDTH.intValue() - 200) {
			x = 0;
			y = 0;
		}
	}

}
