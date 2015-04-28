package mknutsen.aiproject.player.ai.decisiontree;

import java.util.Random;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.procedures.movement.AvoidBullets;
import mknutsen.aiproject.player.ai.procedures.movement.Avoidance;
import mknutsen.aiproject.player.ai.procedures.movement.Follow;
import mknutsen.aiproject.player.ai.procedures.movement.MoveRandomly;
import mknutsen.aiproject.player.ai.procedures.movement.MovementArchitype;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldContinuous;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldRandomly;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldingArchitype;
import mknutsen.aiproject.player.ai.procedures.shoot.ShootContinuous;
import mknutsen.aiproject.player.ai.procedures.shoot.ShootRandomly;
import mknutsen.aiproject.player.ai.procedures.shoot.ShootingArchitype;

/**
 * LeveledDecisionTree takes in a number and uses that to base the difficulty of
 * the ai. there are really only 4 settings:
 * <ul>
 * <li>Good at nothing</li>
 * <li>1 Skill</li>
 * <li>2 Skill</li>
 * <li>All 3 Skill</li>
 * </ul>
 * With the skills being shooting, shielding, and movement. The skills are
 * randomly selected.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public class LeveledDecisionTree extends BasicDecisionTree {
	private ShieldingArchitype shield;
	private ShootingArchitype shoot;
	private MovementArchitype move;
	/**
	 * {SHOOTING, SHIELDING, MOVING} 0 means can't do well at it. 1 means can do
	 * well at it. 0 means it will be executed more or less randomly.
	 */
	private int totalAiLevels = 3;
	private int[] aiStuff = { 0, 0, 0 };

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
	public LeveledDecisionTree(int width, int height, int yLimit,
			boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void run() {
		boolean allZero = true;
		if (aiStuff[1] == 1) {
			shield = new ShieldContinuous(this);
			allZero = false;
		} else {
			shield = new ShieldRandomly(this);
		}
		shield.start();
		if (aiStuff[0] == 0) {
			shoot = new ShootRandomly(this);
		} else {
			shoot = new ShootContinuous(this);
			allZero = false;
		}
		shoot.start();
		if (aiStuff[2] == 0) {
			move = new MoveRandomly(this);
		} else {
			move = new Follow(this);
			allZero = false;
		}
		move.start();
		while (!allZero) {
			State newState = discoverEnemyState();
			if (getPreviousState() == null || newState != getPreviousState()) {
				setPreviousState(newState);
				switch (newState) {
				case SHOOTING:
					System.out.println("SHOOTING");
					if (move.turnRoutineOff()) {
						move = new AvoidBullets(this);
						move.start();
					}
					if (shoot.turnRoutineOff() && Config.BULLET_STUN_TIME < 20) {
						System.out.println("shooting back");
						shoot = new ShootContinuous(this);
						shoot.start();
					}
					break;
				case ATTACKING:
					System.out.println("ATTACKING");
					if (move.turnRoutineOff()) {
						move = new Avoidance(this);
						move.start();
					}
					break;
				case STUNNED:
					System.out.println("Neutral");

					if (move.turnRoutineOff()) {
						move = new Follow(this);
						move.start();
					}

					if (shoot.turnRoutineOff()) {
						shoot = new ShootContinuous(this);
						shoot.start();
					}
					break;
				case RUNNING:
					System.out.println("running");
					if (move.turnRoutineOff()) {
						move = new Follow(this);
						move.start();
					}
					if (shoot.turnRoutineOff()) {

						shoot = new ShootContinuous(this);
						shoot.start();
					}
					break;
				case SHIELDING:

					System.out.println("shielding");

					if (move.turnRoutineOff()) {
						move = new Avoidance(this);
						move.start();
					}
					shoot.turnRoutineOff();
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

	/**
	 * Takes in a number between 0 and 100 and then converts it to the number of
	 * skills for this AI to have.
	 * 
	 * @param aiLevel
	 *            integer between 0 and 100 reflecting the percentage of
	 *            goodness the AI should be
	 */
	public void setAILevel(int aiLevel) {
		if (aiLevel == 100) {
			aiLevel = totalAiLevels;
		} else if (aiLevel == 10) {
			aiLevel = 0;
		} else {
			double percent = (double) ((double) aiLevel / 100);
			aiLevel = (int) (percent * totalAiLevels);
		}
		for (int i = 0; i < aiLevel && i < aiStuff.length; i++) {
			aiStuff[i] = 1;
		}
		Random rnd = new Random();
		for (int i = aiStuff.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = aiStuff[index];
			aiStuff[index] = aiStuff[i];
			aiStuff[i] = a;
		}
		for (int x : aiStuff) {
			System.out.println(x);
		}
	}
}
