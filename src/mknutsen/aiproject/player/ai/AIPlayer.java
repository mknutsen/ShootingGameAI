package mknutsen.aiproject.player.ai;

import java.util.ArrayList;
import java.util.List;

import mknutsen.aiproject.game.Bullet;
import mknutsen.aiproject.game.GameComponent;
import mknutsen.aiproject.player.Player;

/**
 * Ai Player gives the computer all the information they need to function
 * however the AI is supposed to function
 * 
 * @author mknutsen
 *
 */
public abstract class AIPlayer extends Player implements Runnable {
	private Player opponent;

	public AIPlayer(int width, int height, int yLimit, boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void setGame(GameComponent game) {
		super.setGame(game);
		opponent = isPlayer1() ? game.getPlayer2() : game.getPlayer1();
	}

	public Player getOpponent() {
		return opponent;
	}

	/**
	 * sorts through the list of bullets and picks out the ones that belong to
	 * the enemy
	 * 
	 * @param bullets
	 *            the list of all the bullets in the game
	 * @return a list of enemy bullets
	 */
	public List<Bullet> findEnemyBullets(List<Bullet> bullets) {
		ArrayList<Bullet> newList = new ArrayList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			if ((bullets.get(i).getDir() < 0 && isPlayer1())
					|| (!isPlayer1() && bullets.get(i).getDir() > 0)) {
				newList.add(bullets.get(i));
			}
		}

		return newList;
	}
}
