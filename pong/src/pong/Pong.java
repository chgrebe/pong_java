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

	private static Pong me = null;

	public static Pong get() {
		if (me == null) {
			synchronized (Pong.class) {
				if (me == null) {
					me = new Pong();
				}
			}
		}
		return me;
	}

	public Canvas canvas;
	public JFrame frame;

	// TESTING
	private float x, y = 0;

	BufferStrategy bufferStrategy;

	long desiredGameDeltaLoop = (1000 * 1000 * 1000) / Const.GAME_DESIRED_FPS.intValue();

	boolean running = true;

	private Pong() {
		frame = new JFrame("Basic Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(new Dimension(Const.GUI_WIDTH.intValue(), Const.GUI_HEIGHT.intValue()));
		frame.setResizable(false);
		frame.setVisible(true);

		final JPanel panel = (JPanel) frame.getContentPane();
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

		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		while (running) {
			beginLoopTime = System.nanoTime();

			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
//			int deltaTime = (int) (currentUpdateTime - lastUpdateTime);
//			TODO: hier weitermachen
//			update(ticksToUpdate);
			System.out.print((currentUpdateTime - lastUpdateTime) + ", ");

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;
			System.out.println(deltaLoop + ", " + desiredGameDeltaLoop);

			if (deltaLoop > desiredGameDeltaLoop) {
				// Do nothing. We are already late.
			} else {
				try {
					System.out.println((desiredGameDeltaLoop - deltaLoop) / (1000 * 1000));
					Thread.sleep((desiredGameDeltaLoop - deltaLoop) / (1000 * 1000));
				} catch (final InterruptedException e) {
					// Do nothing
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
	protected void update(final double ticksToUpdate) {
		
		if (ticksToUpdate > 1) {
			System.out.println("_______________________________________________________________________________");
		}
		// simulated lag
		try {
			Thread.sleep(0);
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(ticksToUpdate);
		x += ticksToUpdate;
		if (y > Const.GUI_HEIGHT.intValue() - 200) {
			// x = 0;
			y = 0;
		}
	}

}
