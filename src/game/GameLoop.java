package game;

import java.awt.Point;
import java.util.ArrayList;

import ch.aplu.jgamegrid.GGMouse;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

public class GameLoop {

	public static void main(String[] args) {
//		weird X11 fix to stop lag
		System.setProperty("sun.java2d.opengl", "true");

//		initialize gamegrid
		GameGrid gameGrid = new GameGrid(InitialConditions.getWidth(), InitialConditions.getHeight(), 1, null, false);
		gameGrid.setBgImagePath("sprites/table.jpg");
		gameGrid.setTitle("Abgabe");
		gameGrid.setSimulationPeriod(15);

//		initialize cueball
		Cueball cueball = new Cueball();
		cueball.setCollisionCircle(new Point(0, 0), InitialConditions.getBallSize() / 2);
		gameGrid.addActor(cueball, InitialConditions.getCenter());

//		initialize balls
		int i = 0;
		ArrayList<Ball> balls = new ArrayList<Ball>();
		for (Location loc : InitialConditions.getBallLocations()) {
			Ball ball = spawnBall(gameGrid, player, loc, InitialConditions.getBallLocations().size(),
					InitialConditions.getBallSize(), InitialConditions.getSprite(i + 3));
			balls.add(ball);
			i++;
		}
		registerBallCollisions(balls);

//		initialize walls
		new Wall(InitialConditions.getUpperLeft(), InitialConditions.getLowerRight());

//		initialize holes
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (Location loc : InitialConditions.getHoleLocations()) {
			Hole hole = spawnHole(gameGrid, player, loc, InitialConditions.getHoleLocations().size(),
					InitialConditions.getHoleSize());
			holes.add(hole);
		}
		registerHoleCollisions(holes, balls);

//		initialize players and turns
		TurnHandler turnHandler = new TurnHandler();
		gameGrid.addActor(turnHandler, InitialConditions.getCenter());
		HumanPlayer human = new HumanPlayer();
		OpponentPlayer opponent = new OpponentPlayer();
		gameGrid.addMouseListener(human, GGMouse.lRelease);

		gameGrid.show();
		gameGrid.doRun();

		turnHandler.init(new Participant[] { human, opponent }, cueBall, balls);
		turnHandler.startGame();

	}

	public static Ball spawnBall(GameGrid gameGrid, Cueball player, Location loc, int numBalls, int ballSize,
			String sprite) {

		Ball ball = new Ball(sprite);
		gameGrid.addActor(ball, loc);
		player.addCollisionActor(ball);
		ball.setCollisionCircle(new Point(0, 0), InitialConditions.getBallSize() / 2);
		return ball;
	}

	public static void registerBallCollisions(ArrayList<Ball> balls) {
		for (int i = 0; i < balls.size(); i++) {
			for (int j = i + 1; j < balls.size(); j++) {
				balls.get(i).addCollisionActor(balls.get(j));
			}
		}
	}

	public static Hole spawnHole(GameGrid gameGrid, Cueball player, Location loc, int numHoles, int holeSize) {

		Hole hole = new Hole();
		gameGrid.addActor(hole, loc);
		player.addCollisionActor(hole);
		hole.setCollisionCircle(new Point(0, 0), InitialConditions.getHoleSize() / 2);
		return hole;
	}

	public static void registerHoleCollisions(ArrayList<Hole> holes, ArrayList<Ball> balls) {
		for (int i = 0; i < holes.size(); i++) {
			for (int j = 0; j < balls.size(); j++) {
				balls.get(j).addCollisionActor(holes.get(i));
			}
		}
	}

}