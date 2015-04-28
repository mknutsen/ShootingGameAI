package mknutsen.aiproject.player.ai.decisiontree;

/**
 * Outlines the different possible states for a player to be in
 * 
 * @author Max Knutsen  - mknutse1@umbc.edu
 *
 */
public enum State {
	// either immoble for some reason or just doing nothing
	STUNNED(),
	// moving aggressively towards
	ATTACKING(),
	// moving wimpishly away
	RUNNING(),
	// has fired at least one bullet
	SHOOTING(),
	// is currently shielded
	SHIELDING();
}
