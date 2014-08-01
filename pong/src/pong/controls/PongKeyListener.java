package pong.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pong.Pong;

public class PongKeyListener implements KeyListener, ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			Pong.p1LeftPressed = true;
			break;

		case KeyEvent.VK_RIGHT:
			Pong.p1RightPressed = true;
			break;

		case KeyEvent.VK_A:
			Pong.p2LeftPressed = true;
			break;

		case KeyEvent.VK_D:
			Pong.p2RightPressed = true;
			break;

		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println(e);

		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			Pong.p1LeftPressed = false;
			break;

		case KeyEvent.VK_RIGHT:
			Pong.p1RightPressed = false;
			break;

		case KeyEvent.VK_A:
			Pong.p2LeftPressed = false;
			break;

		case KeyEvent.VK_D:
			Pong.p2RightPressed = false;
			break;

		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
