package mknutsen.aiproject.player.ai.procedures.shield;

import java.util.Random;

import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * shields at random times.
 * 
 * @author mknutsen
 *
 */
public class ShieldRandomly extends ShieldingArchitype {

	/**
	 * Sets up the shield procedure
	 * 
	 * @param player
	 *            player to shield from danger
	 */
	public ShieldRandomly(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int upperBound = 200, magicNumber = 50;
		while (isRoutineOn()) {
			if (magicNumber == rand.nextInt(upperBound))
				getPlayer().shield();
			chill();
		}
	}

	@Override
	public boolean turnRoutineOff() {
		return false;
	}
}
