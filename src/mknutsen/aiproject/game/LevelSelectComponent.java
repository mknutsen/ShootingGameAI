package mknutsen.aiproject.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import mknutsen.graphicslibrary.Button;
import mknutsen.graphicslibrary.GraphicsComponent;

/**
 * Component that allows the user to select a level between 1 - 10. Then returns
 * this number using the callback.
 * 
 * @author Max - Knutsen
 *
 */
public class LevelSelectComponent extends GraphicsComponent implements
		MouseListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Button> buttons;
	private int totalLevels;

	public LevelSelectComponent() {
		addMouseListener(this);
		buttons = new ArrayList<Button>();
		totalLevels = 10;
		int sectionLength = (830) / (totalLevels);
		for (int i = 0; i < totalLevels; i++) {
			buttons.add(new Button(sectionLength / 2 - Button.WIDTH / 2
					+ sectionLength * i, 100, "" + (i + 1)));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.drawString("Select Difficulty:", 50, 50);
		for (Button b : buttons) {
			b.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (Button b : buttons) {
			if (b.isInside(e)) {
				int levelNumber = (Integer.parseInt(b.getText()) * 100)
						/ (totalLevels);
				triggerCallback(levelNumber);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void takeParameters(Object[] obj) {

	}
}
