package aiproject.game;

import java.awt.Graphics;

class Button extends GraphicObject {
	private String text;
	static final int WIDTH = 50, HEIGHT = 50;

	public Button(int x, int y, String text) {
		this(x, y, WIDTH, HEIGHT, text);
	}

	public Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, false);
		this.text = text;
	}

	@Override
	public boolean draw(Graphics gr) {
		gr.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
		gr.drawString(text, getX(), getY() + gr.getFontMetrics().getHeight());
		return true;
	}

	public String getText() {
		return text;
	}

}