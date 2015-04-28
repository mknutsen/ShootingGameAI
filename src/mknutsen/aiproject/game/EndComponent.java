package mknutsen.aiproject.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mknutsen.graphicslibrary.Button;
import mknutsen.graphicslibrary.GraphicsComponent;

/**
 * Screen that is displayed at the end of the game informing the player(s) who
 * won.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public class EndComponent extends GraphicsComponent implements MouseListener,
		KeyListener {

	private static final long serialVersionUID = 1L;
	private int winner;
	private Button quit;

	/**
	 * Default constructor: creates quit button
	 */
	public EndComponent() {
		quit = new Button(840 / 2 - 45, 200, 90, 50, "  Quit");
		addKeyListener(this);
		addMouseListener(this);
	}

	@Override
	public void takeParameters(Object[] obj) {
		winner = (int) obj[0] + 1;
	}

	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.drawString("Winner is... player " + winner, 300, 150);
		quit.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		triggerCallback();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		triggerCallback();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		triggerCallback();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (quit.isInside(e))
			triggerCallback();
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

}
