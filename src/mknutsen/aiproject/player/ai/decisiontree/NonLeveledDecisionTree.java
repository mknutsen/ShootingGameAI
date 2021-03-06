package mknutsen.aiproject.player.ai.decisiontree;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.procedures.movement.AvoidBullets;
import mknutsen.aiproject.player.ai.procedures.movement.Avoidance;
import mknutsen.aiproject.player.ai.procedures.movement.Follow;
import mknutsen.aiproject.player.ai.procedures.movement.MovementArchitype;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldContinuous;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldingArchitype;
import mknutsen.aiproject.player.ai.procedures.shoot.ShootContinuous;
import mknutsen.aiproject.player.ai.procedures.shoot.ShootingArchitype;

/**
 * Decision tree that always plays at its best. Uses the determined state of the
 * opponent to decide how to react.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public class NonLeveledDecisionTree extends BasicDecisionTree {
	private ShieldingArchitype shield;
	private ShootingArchitype shoot;
	private MovementArchitype move;

	/**
	 * @param width
	 *            width of the player
	 * @param height
	 *            height of the player
	 * @param yLimit
	 *            window height
	 * @param player1
	 *            is it player 1 or not
	 */
	public NonLeveledDecisionTree(int width, int height, int yLimit,
			boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void run() {
		shield = new ShieldContinuous(this);
		shield.start();
		while (true) {
			State newState = discoverEnemyState();
			if (getPreviousState() == null || newState != getPreviousState()) {
				setPreviousState(newState);
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
}
