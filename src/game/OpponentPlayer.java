package game;

import java.util.ArrayList;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;

public class OpponentPlayer implements Participant {

	GameGrid gameGrid;
	CueBall cueBall;
	ArrayList<Ball> balls;
	TurnHandler turnHandler;
	Actor goal;
	double distance = 1000;

	public OpponentPlayer(CueBall cueBall, ArrayList<Ball> balls, TurnHandler turnHandler) {
		this.cueBall = cueBall;
		this.balls = balls;
		this.turnHandler = turnHandler;
	}

	@Override
	public void startTurn() {

		for (Ball ball : balls) {
			double newDistance = Math.sqrt((ball.getTmpX() - cueBall.getTmpX()) * (ball.getTmpX() - cueBall.getTmpX())
					+ (ball.getTmpY() - cueBall.getTmpY()) * (ball.getTmpY() - cueBall.getTmpY()));
			if (newDistance < distance && !ball.getSunkBall()) {
				goal = ball;
				distance = newDistance;
			}
		}

		cueBall.strike(-(cueBall.getTmpX() - goal.getX()) / InitialConditions.getSensitivity(),
				-(cueBall.getTmpY() - goal.getY()) / InitialConditions.getSensitivity());
		turnHandler.endTurn();
	}
}
