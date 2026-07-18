package game;

public class Ball extends MovableObject {

		public Ball(String sprite) {
			super(sprite, InitialConditions.getBallSize());
		}

		@Override
		public void act() {
			move();
		}

}
