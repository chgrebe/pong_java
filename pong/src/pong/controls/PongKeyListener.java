package pong.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pong.Pong;

public class PongKeyListener implements KeyListener, ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			Pong.get().pressedKeys.put("p1Left", Boolean.valueOf(true));
			break;

		case KeyEvent.VK_RIGHT:
			Pong.get().pressedKeys.put("p1Right", Boolean.valueOf(true));
			break;

		case KeyEvent.VK_A:
			Pong.get().pressedKeys.put("p2Left", Boolean.valueOf(true));
			break;

		case KeyEvent.VK_D:
			Pong.get().pressedKeys.put("p2Right", Boolean.valueOf(true));
			break;

		case KeyEvent.VK_SPACE:
			Pong.get().pauseGame();
			break;

		case KeyEvent.VK_ADD:
			Pong.get().increaseGameSpeed();
			break;

		case KeyEvent.VK_SUBTRACT:
			Pong.get().decreaseGameSpeed();
			break;

		default:
			break;
		}

	}

	@Override
	public void keyReleased(final KeyEvent e) {
		// System.out.println(e);

		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			Pong.get().pressedKeys.put("p1Left", Boolean.valueOf(false));
			break;

		case KeyEvent.VK_RIGHT:
			Pong.get().pressedKeys.put("p1Right", Boolean.valueOf(false));
			break;

		case KeyEvent.VK_A:
			Pong.get().pressedKeys.put("p2Left", Boolean.valueOf(false));
			break;

		case KeyEvent.VK_D:
			Pong.get().pressedKeys.put("p2Right", Boolean.valueOf(false));
			break;

		default:
			break;
		}

	}

	@Override
	public void keyTyped(final KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
