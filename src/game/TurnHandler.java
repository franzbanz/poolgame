package game;

import java.util.ArrayList;

import ch.aplu.jgamegrid.Actor;

public class TurnHandler extends Actor {

	enum State {
		READY_TO_SHOOT, BALLS_MOVING
	}

	enum Ending {
		WIN, LOSS
	}

	static int programCounter = 0; // players turn at 0, opponents at 1
	static int numberOfSolids = 7;
	static int numberOfStripes = 7;
	static boolean hasSolids;
	static int playerPoints = 0;
	static int opponentPoints = 0;
	static State state;
	double velocitySum = 0.0;
	Participant[] participants;
	CueBall cueBall;
	ArrayList<Ball> balls;

	public void init(Participant[] participants, CueBall cueBall, ArrayList<Ball> balls) {
		this.participants = participants;
		this.cueBall = cueBall;
		this.balls = balls;
	}

	@Override
	public void act() {
		if (state == State.BALLS_MOVING && allStopped()) {
			nextTurn();
		}
	}

	public void startGame() {
		state = State.READY_TO_SHOOT;
		participants[programCounter].startTurn();
	}

	public void endTurn() {
		state = State.BALLS_MOVING;
	}

	public boolean readyToShoot() {
		return state == State.READY_TO_SHOOT;
	}

	public boolean allStopped() {
		if (!(cueBall.getVelocity() == 0)) {
			return false;
		}
		for (Ball ball : balls) {
			if (!(ball.getVelocity() == 0)) {
				return false;
			}
		}
		return true;
	}

	public Participant getActiveParticipant() {
		return participants[programCounter];
	}

	public void nextTurn() {
		programCounter = 1 - programCounter;
		state = State.READY_TO_SHOOT;
		participants[programCounter].startTurn();
	}

	public static void countPoints(MovableObject a) {
		if (programCounter == 0) { // is it the players turn?
			switch (a.ballType) {
			case EIGHT:
				if (numberOfSolids == 0 && hasSolids) {
					System.out.println("YOU HAVE WON");
					endGame(Ending.WIN);
				} else {
					System.out.println("YOU HAVE LOST");
					endGame(Ending.LOSS);
				}
				break;
			case STRIPE:
				if (isFirstMove()) {
					System.out.println("YOU HAVE THE STRIPED BALLS");
					hasSolids = false;
					numberOfStripes -= 1;
					playerPoints += 1;
				} else if (hasSolids) {
					System.out.println("YOU HAVE SUNKEN A STRIPED BALL BUT YOU HAVE THE SOLIDS");
					numberOfStripes -= 1;
					opponentPoints += 1;
				} else if (!hasSolids) {
					System.out.println("YOU HAVE SUNKEN A STRIPED BALL");
					numberOfStripes -= 1;
					playerPoints += 1;
				}
				break;
			case SOLID:
				if (isFirstMove()) {
					System.out.println("YOU HAVE THE SOLID BALLS");
					hasSolids = true;
					numberOfSolids -= 1;
					playerPoints += 1;
				} else if (hasSolids) {
					System.out.println("YOU HAVE SUNKEN A SOLID BALL");
					numberOfSolids -= 1;
					playerPoints += 1;
				} else if (!hasSolids) {
					System.out.println("YOU HAVE SUNKEN A SOLID BALL BUT YOU HAVE THE STRIPES");
					numberOfSolids -= 1;
					opponentPoints += 1;
				}
				break;
			case CUE:
				System.out.println("CUE");
			}
		}

		if (programCounter == 1) { // is it the opponents turn?
			switch (a.ballType) {
			case EIGHT:
				if (numberOfSolids == 0 && hasSolids) {
					System.out.println("OPPONENT HAS WON");
					endGame(Ending.LOSS);
				} else {
					System.out.println("OPPONENT HAS LOST");
					endGame(Ending.WIN);
				}
				break;
			case STRIPE:
				if (isFirstMove()) {
					System.out.println("OPPONENT HAS THE STRIPED BALLS");
					hasSolids = true;
					numberOfStripes -= 1;
					opponentPoints += 1;
				} else if (hasSolids) {
					System.out.println("OPPONENT HAS SUNKEN A STRIPED BALL");
					numberOfStripes -= 1;
					opponentPoints += 1;
				} else if (!hasSolids) {
					System.out.println("OPPONENT HAS SUNKEN A STRIPED BALL BUT HAS THE SOLIDS");
					numberOfStripes -= 1;
					playerPoints += 1;
				}
				break;
			case SOLID:
				if (isFirstMove()) {
					System.out.println("OPPONENT HAS THE SOLID BALLS");
					hasSolids = false;
					numberOfSolids -= 1;
					opponentPoints += 1;
				} else if (hasSolids) {
					System.out.println("OPPONENT HAS SUNKEN A SOLID BALL BUT HAS STRIPES");
					numberOfSolids -= 1;
					playerPoints += 1;
				} else if (!hasSolids) {
					System.out.println("OPPONENT HAS SUNKEN A SOLID BALL");
					numberOfSolids -= 1;
					opponentPoints += 1;
				}
				break;
			case CUE:
				System.out.println("CUE");
			}
		}
	}

	public static void endGame(Ending ending) {
		switch (ending) {
		case WIN:
			return; // TODO
		case LOSS:
			return; // TODO
		}
	}

	public static boolean isFirstMove() {
		if (numberOfSolids + numberOfStripes == 14) {
			return true;
		}
		return false;
	}

	public static int getProgramCounter() {
		return programCounter;
	}

	public static State getState() {
		return state;
	}
}
