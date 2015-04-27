package mknutsen.aiproject.player.ai.procedures.shield;

import java.util.Random;

import mknutsen.aiproject.player.ai.AIPlayer;

public class ShieldRandomly extends ShieldingArchitype {

	public ShieldRandomly(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int upperBound = 100, magicNumber = 50;
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
