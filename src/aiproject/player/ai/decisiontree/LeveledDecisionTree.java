package aiproject.player.ai.decisiontree;

public class LeveledDecisionTree extends BasicDecisionTree {
	private int aiLevel;

	public LeveledDecisionTree(int width, int height, int yLimit,
			boolean player1) {
		super(width, height, yLimit, player1);
		aiLevel = 4;
	}

	@Override
	public void run() {

	}

	public void setAILevel(int aiLevel) {
		this.aiLevel = aiLevel;
	}

}
