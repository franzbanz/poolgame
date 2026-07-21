package game;

public class CueBall extends MovableObject {

	public CueBall() {
		super(InitialConditions.getSprite(2), InitialConditions.getBallSize(), MovableObject.BallType.CUE);
	}

	@Override
	public void act() {
		move();
	}

	public void strike(double speedX, double speedY) {
		setSpeedX(speedX);
		setSpeedY(speedY);
	}

}