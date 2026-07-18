package game;

import ch.aplu.jgamegrid.Location;

public class Wall extends ImmovableObject {
	static protected Location upperLeft;
	static protected Location lowerRight;

	public Wall(Location upperLeft, Location lowerRight) {
		super("sprites/tableOuter.png");
		Wall.upperLeft = upperLeft;
		Wall.lowerRight = lowerRight;
	}

	public static void handleBoundary(MovableObject a) {

		if (upperWallCheck(a)) {
			a.setTmpY(upperLeft.y + a.getSize() / 2);
			a.setSpeedY(a.getSpeedY() * -1);
		} else if (lowerWallCheck(a)) {
			a.setTmpY(lowerRight.y - a.getSize() / 2);
			a.setSpeedY(a.getSpeedY() * -1);
		} else if (leftUpperWallCheck(a) || leftLowerWallCheck(a)) {
			a.setTmpX(upperLeft.x + a.getSize() / 2);
			a.setSpeedX(a.getSpeedX() * -1);
		} else if (rightUpperWallCheck(a) || rightLowerWallCheck(a)) {
			a.setTmpX(lowerRight.x - a.getSize() / 2);
			a.setSpeedX(a.getSpeedX() * -1);
		}
	}

	public static boolean lowerWallCheck(MovableObject a) {
		if (a.getTmpY() + a.getSize() / 2 >= lowerRight.y
				&& a.getTmpX() <= lowerRight.x - InitialConditions.getHoleSize() / 2
				&& a.getTmpX() >= upperLeft.x + InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}

	public static boolean upperWallCheck(MovableObject a) {
		if (a.getTmpY() - a.getSize() / 2 <= upperLeft.y
				&& a.getTmpX() <= lowerRight.x - InitialConditions.getHoleSize() / 2
				&& a.getTmpX() >= upperLeft.x + InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}

	public static boolean rightUpperWallCheck(MovableObject a) {
		if (a.getTmpX() + a.getSize() / 2 >= lowerRight.x
				&& a.getTmpY() >= upperLeft.y + InitialConditions.getHoleSize() / 2
				&& a.getTmpY() <= InitialConditions.getCenter().y - InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}

	public static boolean rightLowerWallCheck(MovableObject a) {
		if (a.getTmpX() + a.getSize() / 2 >= lowerRight.x
				&& a.getTmpY() >= InitialConditions.getCenter().y + InitialConditions.getHoleSize() / 2
				&& a.getTmpY() <= lowerRight.y - InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}

	public static boolean leftUpperWallCheck(MovableObject a) {
		if (a.getTmpX() - a.getSize() / 2 <= upperLeft.x
				&& a.getTmpY() >= upperLeft.y + InitialConditions.getHoleSize() / 2
				&& a.getTmpY() <= InitialConditions.getCenter().y - InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}

	public static boolean leftLowerWallCheck(MovableObject a) {
		if (a.getTmpX() - a.getSize() / 2 <= upperLeft.x
				&& a.getTmpY() >= InitialConditions.getCenter().y + InitialConditions.getHoleSize() / 2
				&& a.getTmpY() <= lowerRight.y - InitialConditions.getHoleSize() / 2) {
			return true;
		}
		return false;
	}
}