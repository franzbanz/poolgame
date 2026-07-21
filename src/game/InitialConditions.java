package game;

import java.util.ArrayList;

import ch.aplu.jgamegrid.Location;

public class InitialConditions {

	static int height = 973;
	static int width = 547;
	static Location center = new Location(width / 2, height / 2);;
	static Location upperLeft = new Location(56, 56);
	static Location lowerRight = new Location(width - 57, height - 57);
	static Location start = new Location(center.x, center.y + 300);
	static int ballSize = 25;
	static int holeSize = 48;
	static double maxSpeed = 40.0;
	static double drag = 0.02;
	static int spacing = ballSize + 1;
	static int offset = 200;
	static int holeOffset = 19;
	static int rowHeight = (int) Math.round(spacing * Math.sqrt(3) / 2);
	static int sensitivity = 15;
	static boolean isPlayersTurn = true;

	static String[] sprites = { "sprites/table.jpg", "sprites/tableCollision.png", "sprites/0.png", "sprites/1.png",
			"sprites/2.png", "sprites/3.png", "sprites/8.png", "sprites/4.png", "sprites/5.png", "sprites/6.png",
			"sprites/7.png", "sprites/9.png", "sprites/10.png", "sprites/11.png", "sprites/12.png", "sprites/13.png",
			"sprites/14.png", "sprites/15.png" };
	static ArrayList<Location> ballLocations = new ArrayList<Location>();
	static ArrayList<Location> holeLocations = new ArrayList<Location>();
	static ArrayList<MovableObject.BallType> ballTypes = new ArrayList<MovableObject.BallType>();
//	should make an extra class for ball locations, types and sprites, otherwise index error very likely

	static {
		ballLocations.add(new Location(center.x, center.y - offset));

		ballLocations.add(new Location((center.x + spacing / 2), (center.y - rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing / 2), (center.y - rowHeight - offset)));

		ballLocations.add(new Location(center.x, (center.y - 2 * rowHeight - offset)));
		ballLocations.add(new Location((center.x + spacing), (center.y - 2 * rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing), (center.y - 2 * rowHeight - offset)));

		ballLocations.add(new Location((center.x + spacing / 2), (center.y - 3 * rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing / 2), (center.y - 3 * rowHeight - offset)));
		ballLocations.add(new Location((center.x + spacing + spacing / 2), (center.y - 3 * rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing - spacing / 2), (center.y - 3 * rowHeight - offset)));

		ballLocations.add(new Location(center.x, (center.y - 4 * rowHeight - offset)));
		ballLocations.add(new Location((center.x + spacing), (center.y - 4 * rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing), (center.y - 4 * rowHeight - offset)));
		ballLocations.add(new Location((center.x + spacing * 2), (center.y - 4 * rowHeight - offset)));
		ballLocations.add(new Location((center.x - spacing * 2), (center.y - 4 * rowHeight - offset)));

		holeLocations.add(new Location(upperLeft.x - holeOffset, upperLeft.y - holeOffset));
		holeLocations.add(new Location(lowerRight.x + holeOffset, lowerRight.y + holeOffset));
		holeLocations.add(new Location(lowerRight.x + holeOffset, upperLeft.y - holeOffset));
		holeLocations.add(new Location(upperLeft.x - holeOffset, lowerRight.y + holeOffset));
		holeLocations.add(new Location(upperLeft.x - holeOffset, height / 2));
		holeLocations.add(new Location(lowerRight.x + holeOffset, height / 2));

		ballTypes.add(MovableObject.BallType.SOLID);

		ballTypes.add(MovableObject.BallType.SOLID);
		ballTypes.add(MovableObject.BallType.SOLID);

		ballTypes.add(MovableObject.BallType.EIGHT);
		ballTypes.add(MovableObject.BallType.SOLID);
		ballTypes.add(MovableObject.BallType.SOLID);

		ballTypes.add(MovableObject.BallType.SOLID);
		ballTypes.add(MovableObject.BallType.SOLID);
		ballTypes.add(MovableObject.BallType.STRIPE);
		ballTypes.add(MovableObject.BallType.STRIPE);

		ballTypes.add(MovableObject.BallType.STRIPE);
		ballTypes.add(MovableObject.BallType.STRIPE);
		ballTypes.add(MovableObject.BallType.STRIPE);
		ballTypes.add(MovableObject.BallType.STRIPE);
		ballTypes.add(MovableObject.BallType.STRIPE);

	}

	public static String getSprite(int i) {
		return sprites[i];
	}

	public static ArrayList<Location> getBallLocations() {
		return ballLocations;
	}

	public static ArrayList<Location> getHoleLocations() {
		return holeLocations;
	}

	public static int getHeight() {
		return height;
	}

	public static int getWidth() {
		return width;
	}

	public static Location getCenter() {
		return center;
	}

	public static Location getStart() {
		return start;
	}

	public static int getBallSize() {
		return ballSize;
	}

	public static MovableObject.BallType getBallType(int i) {
		return ballTypes.get(i);
	}

	public static int getHoleSize() {
		return holeSize;
	}

	public static int getInitSpacing() {
		return spacing;
	}

	public static Location getLowerRight() {
		return lowerRight;
	}

	public static Location getUpperLeft() {
		return upperLeft;
	}

	public static int getSensitivity() {
		return sensitivity;
	}

	public static double getMaxSpeed() {
		return maxSpeed;
	}

	public static double getDrag() {
		return drag;
	}

	public static boolean getIsPlayersTurn() {
		return isPlayersTurn;
	}

	public static void setIsPlayersTurn(boolean a) {
		isPlayersTurn = a;
	}

}
