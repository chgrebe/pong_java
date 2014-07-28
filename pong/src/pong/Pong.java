package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import pong.constants.Const;
import pong.game_objects.Ball;
import pong.game_objects.Paddle;
import pong.mouse_control.MouseControl;

public class Pong implements Runnable {

	public static double accumulatorUpdate = 0.0;
	public static Ball ball = new Ball();
	public static BufferStrategy bufferStrategy;
	public static Canvas canvas;
	public static double currentRender;
	public static double deltaRender;
	public static JFrame frame;
	public static double lastRender = System.currentTimeMillis();
	public static double lastUpdate = System.currentTimeMillis();
	public static boolean notFinished = true;
	public static Paddle paddleBot;
	public static Paddle paddleTop;

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
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "P1MoveLeft");
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "P1MoveRight");

		final Action p1MoveLeft = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5769096915351940491L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				x = x - 20 > 0 ? x - 20 : 0;
			}
		};
		final Action p1MoveRight = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5374910769395440871L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				x = x + 20 < Const.GUI_WIDTH.intValue() - 200 ? x + 20 : Const.GUI_WIDTH.intValue() - 200;
			}
		};

		panel.getActionMap().put("P1MoveLeft", p1MoveLeft);
		panel.getActionMap().put("P1MoveRight", p1MoveRight);

		canvas = new Canvas();
		canvas.setBounds(0, 0, Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue());
		canvas.setIgnoreRepaint(true);

		canvas.addMouseListener(new MouseControl());

		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		frame.pack();
		canvas.requestFocus();

		setObjects();
	}

	@Override
	public void run() {

		final double desiredGUIDelta = (1000) / Const.GUI_DESIRED_FPS.doubleValue();
		final double desiredGameDelta = (1000) / Const.GAME_DESIRED_FPS.doubleValue();
		System.out.println("Game Delta: " + desiredGameDelta);
		System.out.println("GUI Delta: " + desiredGUIDelta);

		while (notFinished) {

			updateCurrent = System.currentTimeMillis();
			updateDelta = updateCurrent - lastUpdate;
			lastUpdate = updateCurrent;
			accumulatorUpdate += updateDelta;

			// System.out.println(accumulator);
			while (accumulatorUpdate >= desiredGameDelta) {
				// System.out.printf("Time since last update: %f%n",
				// Double.valueOf(accumulatorUpdate));
				update(desiredGameDelta);
				accumulatorUpdate -= desiredGameDelta;
				try {
					Thread.sleep(0);
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			currentRender = System.currentTimeMillis();
			deltaRender = currentRender - lastRender;

			if (deltaRender >= desiredGUIDelta) {
				// System.out.printf("Time since last render: %f%n",
				// Double.valueOf(deltaRender));
				render();
				lastRender = currentRender;
				try {
					Thread.sleep(0);
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
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
		paddleTop = new Paddle(x, topY);

		final int botY = Const.GUI_HEIGHT.intValue() - Const.PADDLE_VERTICAL_DISTANCE.intValue()
				- Const.PADDLE_HEIGHT.intValue();
		paddleBot = new Paddle(x, botY);

		// System.out.printf("Paddle init:%nX: %d%ntopY: %d%nbotY: %d%n", x,
		// topY, botY);
		// System.out.println("GUI_HEIGHT: " + Const.GUI_HEIGHT);
		// System.out.println("PADDLE_HEIGHT: " + Const.PADDLE_HEIGHT);
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void render(final Graphics2D g) {
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

		if (x > Const.GUI_WIDTH.intValue() - 200) {
			x = 0;
			y = 0;
		}
	}

}
