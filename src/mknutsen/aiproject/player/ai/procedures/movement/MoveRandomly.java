package mknutsen.aiproject.player.ai.procedures.movement;

import java.util.Random;

import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Moves to a random location after a random amount of time
 * 
 * @author mknutsen
 *
 */
public class MoveRandomly extends MovementArchitype {

	/**
	 * Sets up the movement procedure
	 * 
	 * @param player
	 *            player to move
	 */
	public MoveRandomly(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int upperBound = 100, magicNumber = 50;
		while (isRoutineOn()) {
			if (magicNumber == rand.nextInt(upperBound))
				getPlayer().setGoalY(rand.nextInt(getPlayer().getWindowHeight()));
			chill();
		}
	}

	@Override
	public boolean turnRoutineOff() {
		return false;
	}
}
