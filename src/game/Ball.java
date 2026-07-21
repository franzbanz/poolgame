package game;

public class Ball extends MovableObject {

	public Ball(String sprite, BallType ballType) {
		super(sprite, InitialConditions.getBallSize(), ballType);
	}

	@Override
	public void act() {
		move();
	}

}
