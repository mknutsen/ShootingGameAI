package mknutsen.aiproject.player.ai.procedures.movement;

import java.util.Random;

import mknutsen.aiproject.player.ai.AIPlayer;

public class MoveRandomly extends MovementArchitype {

	public MoveRandomly(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int upperBound = 100, magicNumber = 50;
		while (isRoutineOn()) {
			if (magicNumber == rand.nextInt(upperBound))
				getPlayer()
						.setGoal(rand.nextInt(getPlayer().getWindowHeight()));
			chill();
		}
	}

	@Override
	public boolean turnRoutineOff() {
		return false;
	}
}
