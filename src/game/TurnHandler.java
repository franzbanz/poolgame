package game;

import java.util.ArrayList;

import ch.aplu.jgamegrid.Actor;

public class TurnHandler extends Actor {

	enum State {
		READY_TO_SHOOT, BALLS_MOVING
	}

	int programCounter = 0;
	double velocitySum = 0.0;
	Participant[] participants;
	CueBall cueBall;
	ArrayList<Ball> balls;
	State state;

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
		programCounter = 1 - programCounter; // TODO: only works with two participants
		state = State.READY_TO_SHOOT;
		participants[programCounter].startTurn();
	}
}
