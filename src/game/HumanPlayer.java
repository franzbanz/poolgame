package game;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGMouse;
import ch.aplu.jgamegrid.GGMouseListener;
import ch.aplu.jgamegrid.Location;

public class HumanPlayer implements Participant, GGMouseListener {

	public boolean mouseEvent(GGMouse mouse) {
		Location mouseLoc = gameGrid.getMouseLocation();

		speedX = -(getTmpX() - mouseLoc.x) / InitialConditions.getSensitivity();
		speedY = -(getTmpY() - mouseLoc.y) / InitialConditions.getSensitivity();

		return true;
	}

	public void opponentsTurn() {

		while (InitialConditions.getIsPlayersTurn() == false) {
			if (velocity == 0) {

				int searchDistance = 500;

				Actor goal = getNeighbours(searchDistance, Ball.class).get(1);

				speedX = -(getTmpX() - goal.getX()) / InitialConditions.getSensitivity();
				speedY = -(getTmpY() - goal.getX()) / InitialConditions.getSensitivity();
				InitialConditions.setIsPlayersTurn(true);
			}

		}
	}

}
