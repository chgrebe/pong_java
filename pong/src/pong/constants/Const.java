package pong.constants;

public class Const {

	public static final Integer BALL_DIAMETER = Integer.valueOf(40);
	public static final Double BALL_INITIAL_SPEED = Double.valueOf(2);

	public static final Integer GAME_DESIRED_FPS = Integer.valueOf(100);
	public static final Double GAME_SPEED_MAX = Double.valueOf(1.0);
	public static final Double GAME_SPEED_MIN = Double.valueOf(0.1);
	public static final Double GAME_SPEED_STEP_SIZE = Double.valueOf(0.1);

	public static final Integer GUI_DESIRED_FPS = Integer.valueOf(120);
	public static final Integer GUI_HEIGHT = Integer.valueOf(768);
	public static final Integer GUI_WIDTH = Integer.valueOf(1024);

	public static final Double PADDLE_ACCELERATION = Double.valueOf(0.15);
	public static final Double PADDLE_FRICTION = Double.valueOf(0.04);
	public static final Integer PADDLE_HEIGHT = Integer.valueOf(20);
	public static final Double PADDLE_MAX_SPEED = Double.valueOf(5);
	public static final Integer PADDLE_VERTICAL_DISTANCE = Integer.valueOf(20);
	public static final Integer PADDLE_WIDTH = Integer.valueOf(100);
	public static final Double BALL_SPEED_INCREASE_ON_COLLISION = Double.valueOf(0.3);

}
