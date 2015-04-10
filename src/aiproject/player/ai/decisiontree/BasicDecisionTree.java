package aiproject.player.ai.decisiontree;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;
import aiproject.player.ai.procedures.movement.AvoidBullets;
import aiproject.player.ai.procedures.movement.Avoidance;
import aiproject.player.ai.procedures.movement.Follow;
import aiproject.player.ai.procedures.movement.MovementArchitype;
import aiproject.player.ai.procedures.shield.ShieldContinuous;
import aiproject.player.ai.procedures.shield.ShieldingArchitype;
import aiproject.player.ai.procedures.shoot.ShootContinuous;
import aiproject.player.ai.procedures.shoot.ShootingArchitype;

public class BasicDecisionTree extends AIPlayer {
	private ShieldingArchitype shield;
	private ShootingArchitype shoot;
	private MovementArchitype move;
	private State previousState;

	public BasicDecisionTree(int width, int height, int yLimit, boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void run() {
		shield = new ShieldContinuous(this);
		shield.start();
		while (true) {
			State newState = discoverEnemyState();
			if (previousState == null || newState != previousState) {
				previousState = newState;
				switch (newState) {
				case SHOOTING:
					System.out.println("SHOOTING");
					if (move != null)
						move.turnRoutineOff();
					move = new AvoidBullets(this);
					move.start();

					if (shoot != null) {
						shoot.turnRoutineOff();
					}
					if (Config.BULLET_STUN_TIME < 20) {
						System.out.println("shooting back");
						shoot = new ShootContinuous(this);
						shoot.start();
					}
					break;
				case ATTACKING:
					System.out.println("ATTACKING");
					if (move != null)
						move.turnRoutineOff();
					move = new Avoidance(this);
					move.start();
					break;
				case STUNNED:
					System.out.println("Neutral");

					if (move != null)
						move.turnRoutineOff();
					move = new Follow(this);
					move.start();

					if (shoot != null) {
						shoot.turnRoutineOff();
					}
					shoot = new ShootContinuous(this);
					shoot.start();
					break;
				case RUNNING:
					System.out.println("running");
					if (move != null)
						move.turnRoutineOff();
					move = new Follow(this);
					move.start();

					if (shoot != null) {
						shoot.turnRoutineOff();
					}
					shoot = new ShootContinuous(this);
					shoot.start();
					break;
				case SHIELDING:

					System.out.println("shielding");

					if (move != null)
						move.turnRoutineOff();
					move = new Avoidance(this);
					move.start();
					if (shoot != null) {
						shoot.turnRoutineOff();
					}
					break;
				}
			}
			try {
				Thread.sleep(Config.AI_CHILL_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public State discoverEnemyState() {
		if (getOpponent().isFrozen() || getOpponent().isShieldCool()
				|| previousState == null) {
			return State.STUNNED;
		} else if (findEnemyBullets(getGame().getBullets()).size() > 0) {
			return State.SHOOTING;
		} else if (getOpponent().isShield()) {
			return State.SHIELDING;
		} else if ((getOpponent().getVelocity() > 0 && getY() > getOpponent()

		.getY())
				|| (getOpponent().getVelocity() < 0 && getY() < getOpponent()
						.getY())) {
			return State.ATTACKING;
		} else if ((getOpponent().getVelocity() < 0 && getY() > getOpponent()
				.getY())
				|| (getOpponent().getVelocity() > 0 && getY() < getOpponent()
						.getY())) {
			return State.RUNNING;
		} else {
			return previousState;
		}
	}
}
