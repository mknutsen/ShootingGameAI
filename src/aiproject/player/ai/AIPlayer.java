package aiproject.player.ai;

import java.util.ArrayList;
import java.util.List;

import aiproject.game.Bullet;
import aiproject.game.GameComponent;
import aiproject.player.Player;

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
