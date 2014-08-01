package pong.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pong.Pong;
import pong.constants.Const;

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

		case KeyEvent.VK_SPACE:
			Pong.notPaused = Pong.notPaused ? false : true;
			break;
			
		case KeyEvent.VK_ADD:
			if(Pong.gameSpeed + Const.GAME_SPEED_STEP_SIZE.doubleValue() < Const.GAME_SPEED_MAX.doubleValue()) {
				Pong.gameSpeed += Const.GAME_SPEED_STEP_SIZE.doubleValue();
			} else {
				Pong.gameSpeed = Const.GAME_SPEED_MAX.doubleValue();
			}
			System.out.printf("Gamespeed increased to %f.%n", Double.valueOf(Pong.gameSpeed));
			break;
		
		case KeyEvent.VK_SUBTRACT:
			if(Pong.gameSpeed - Const.GAME_SPEED_STEP_SIZE.doubleValue() > Const.GAME_SPEED_MIN.doubleValue()) {
				Pong.gameSpeed -= Const.GAME_SPEED_STEP_SIZE.doubleValue();
			} else {
				Pong.gameSpeed = Const.GAME_SPEED_MIN.doubleValue();
			}
			System.out.printf("Gamespeed decreased to %f.%n", Double.valueOf(Pong.gameSpeed));
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
