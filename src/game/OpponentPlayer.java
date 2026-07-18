package game;

import java.util.ArrayList;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;

public class OpponentPlayer implements Participant {

	GameGrid gameGrid;
	CueBall cueBall;
	ArrayList<Ball> balls;
	TurnHandler turnHandler;

	public OpponentPlayer(CueBall cueBall, ArrayList<Ball> balls, TurnHandler turnHandler) {
		this.cueBall = cueBall;
		this.balls = balls;
		this.turnHandler = turnHandler;
	}

	@Override
	public void startTurn() {
		Actor goal = balls.getLast();

		cueBall.strike(-(cueBall.getTmpX() - goal.getX()) / InitialConditions.getSensitivity(),
				-(cueBall.getTmpY() - goal.getY()) / InitialConditions.getSensitivity());
		turnHandler.endTurn();
	}
}
