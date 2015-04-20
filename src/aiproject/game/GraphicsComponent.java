package aiproject.game;

import javax.swing.JPanel;

public abstract class GraphicsComponent extends JPanel {
	private CompletionCallback callback;
	private static final long serialVersionUID = 1L;

	public final void setCallback(CompletionCallback callback) {
		this.callback = callback;
	}

	protected final void triggerCallback(Object... returnValue) {
		callback.execute(returnValue);
	}

	public abstract void takeParameters(Object[] obj);
}
