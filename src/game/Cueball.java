package game;

public class Cueball extends MovableObject {

	public Cueball() {
		super(InitialConditions.getSprite(2), InitialConditions.getBallSize());
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