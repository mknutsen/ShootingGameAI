package aiproject.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuComponent extends GraphicsComponent implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Button> buttons;

	public MenuComponent() {
		addMouseListener(this);
		buttons = new ArrayList<Button>();
		int totalLevels = 4;
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
				triggerCallback(Integer.parseInt(b.getText()));
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
