package mknutsen.aiproject.player.ai.procedures.shoot;

import java.util.Random;

import mknutsen.aiproject.player.ai.AIPlayer;

public class ShootRandomly extends ShootingArchitype {

	public ShootRandomly(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int upperBound = 100, magicNumber = 50;
		while (isRoutineOn()) {
			if (magicNumber == rand.nextInt(upperBound))
				getPlayer().spawnBullet();
			chill();
		}
	}

	@Override
	public boolean turnRoutineOff() {
		return false;
	}
}
