package game;

import java.awt.Point;
import java.util.ArrayList;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGBackground;
import game.TurnHandler.State;

public class Graphics extends Actor {

	GGBackground background;
	CueBall cueBall;
	ArrayList<Ball> balls;

	public Graphics(GGBackground background, CueBall cueBall, ArrayList<Ball> balls) {
		this.background = background;
		this.cueBall = cueBall;
		this.balls = balls;

	}

	public void act() {
		drawAimLine(background, cueBall, balls);
	}

	public static void drawAimLine(GGBackground background, CueBall cueBall, ArrayList<Ball> balls) {
		background.clear();
		if (TurnHandler.getProgramCounter() == 0 && TurnHandler.getState() != State.BALLS_MOVING) {
			try {
				background.drawLine(cueBall.getPixelLocation(),
						new Point(cueBall.gameGrid.getMousePosition().x, cueBall.gameGrid.getMousePosition().y));
			} catch (Exception e) {
				return;
			}
		}

	}
}
