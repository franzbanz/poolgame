package game;

import ch.aplu.jgamegrid.GGMouse;
import ch.aplu.jgamegrid.GGMouseListener;
import ch.aplu.jgamegrid.Location;

public class HumanPlayer implements Participant, GGMouseListener {

	CueBall cueBall;
	TurnHandler turnHandler;

	public HumanPlayer(CueBall cueBall, TurnHandler turnHandler) {
		this.cueBall = cueBall;
		this.turnHandler = turnHandler;
	}

	@Override
	public void startTurn() {
//		TODO: not needed, might remove later...
	}

	public boolean mouseEvent(GGMouse mouse) {
		if (turnHandler.getActiveParticipant() != this || !turnHandler.readyToShoot()) {
			return true;
		}
		Location mouseLoc = cueBall.gameGrid.getMouseLocation();

		cueBall.strike(-(cueBall.getX() - mouseLoc.x) / InitialConditions.getSensitivity(),
				-(cueBall.getY() - mouseLoc.y) / InitialConditions.getSensitivity());
		turnHandler.endTurn();
		return true;
	}
}
