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
import pong.mouse_control.MouseControl;

public class Pong implements Runnable {

	private static double accumulatorUpdate = 0.0;
	private static BufferStrategy bufferStrategy;
	private static Canvas canvas;
	private static double currentRender;
	private static double updateCurrent;
	private static double deltaRender;
	private static double updateDelta;
	private static JFrame frame;
	private static double lastRender = System.currentTimeMillis();
	private static double lastUpdate = System.currentTimeMillis();
	private static boolean notFinished = true;
	private static JPanel panel;
	private static Pong me = new Pong();

	public static Pong get() {
		return me;
	}

	// TESTING
	private float x, y = 0;

	private Pong() {
		frame = new JFrame("Basic Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue()));
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

		canvas.requestFocus();
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
				System.out.printf("Time since last update: %f%n", Double.valueOf(updateDelta));
				update(desiredGameDelta);
				accumulatorUpdate -= desiredGameDelta;
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			currentRender = System.currentTimeMillis();
			deltaRender = currentRender - lastRender;

			if (deltaRender >= desiredGUIDelta) {
				System.out.println("Time since last render: " + deltaRender);
				render();
				lastRender = currentRender;
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
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

	/**
	 * Rewrite this method for your game
	 */
	protected void render(final Graphics2D g) {
		g.fillRect((int) x, (int) y, 200, 200);
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void update(final double dt) {

		x += dt * 0.1;
		y += dt * 0.1;

		if (x > Const.GUI_WIDTH.intValue() - 200) {
			x = 0;
			y = 0;
		}
	}

}
