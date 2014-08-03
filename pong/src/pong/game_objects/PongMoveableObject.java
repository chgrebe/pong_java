package pong.game_objects;

import pong.update.Vector2D;

public abstract class PongMoveableObject extends PongObject {

	protected abstract void accelerate(Vector2D v);

}
