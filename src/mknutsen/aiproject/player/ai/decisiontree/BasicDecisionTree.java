package mknutsen.aiproject.player.ai.decisiontree;

import mknutsen.aiproject.player.ai.AIPlayer;

public abstract class BasicDecisionTree extends AIPlayer {
	private State previousState;

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
	public BasicDecisionTree(int width, int height, int yLimit, boolean player1) {
		super(width, height, yLimit, player1);
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
			return State.STUNNED;
		}
	}

	protected State getPreviousState() {
		return previousState;
	}

	protected void setPreviousState(State previousState) {
		this.previousState = previousState;
	}
}
