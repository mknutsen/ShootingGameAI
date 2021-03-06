package mknutsen.aiproject.game;

import mknutsen.aiproject.Config;
import mknutsen.graphicslibrary.Button;
import mknutsen.graphicslibrary.GraphicsComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Component that displays instructions
 *
 * @author mknutsen
 */
public class InstructionsComponent extends GraphicsComponent implements MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;

    private Button done;

    /**
     * makes a done button, adds listeners
     */
    public InstructionsComponent() {
        done = new Button(760, 760, "OK");
        addMouseListener(this);
        addKeyListener(this);
    }

    static void displayBrokenString(Graphics gr, int x, int y, String str) {
        for (String line : str.split("\n")) {
            gr.drawString(line, x, y += gr.getFontMetrics().getHeight());
        }
    }

    @Override
    public void takeParameters(Object[] obj) {

    }

    @Override
    public void paint(Graphics gr) {
        int x = 0, y = 0;
        gr.setFont(new Font("Times New Roman", Font.BOLD, 30));
        // frozen
        x = 70;
        y = 100;
        gr.setColor(Color.BLUE);
        gr.fillRect(x, y, Config.DEFAULT_PLAYER_WIDTH, Config.DEFAULT_PLAYER_HEIGHT);
        gr.setColor(Color.BLACK);
        displayBrokenString(gr, x + 50, y, "You can't move,\nyou're frozen!");

        // shield cool
        x = 470;
        y = 100;
        gr.setColor(Color.GRAY);
        gr.fillRect(x - 10, y, 10, Config.DEFAULT_PLAYER_HEIGHT);
        gr.fillRect(x + Config.DEFAULT_PLAYER_WIDTH, y, 10, Config.DEFAULT_PLAYER_HEIGHT);
        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, Config.DEFAULT_PLAYER_WIDTH, Config.DEFAULT_PLAYER_HEIGHT);
        displayBrokenString(gr, x + 50, y, "You can't shield until\nthe grey goes away!");

        // shield
        x = 70;
        y = 400;
        gr.setColor(Color.YELLOW);
        gr.fillRect(x - 10, y, 10, Config.DEFAULT_PLAYER_HEIGHT);
        gr.fillRect(x + Config.DEFAULT_PLAYER_WIDTH, y, 10, Config.DEFAULT_PLAYER_HEIGHT);
        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, Config.DEFAULT_PLAYER_WIDTH, Config.DEFAULT_PLAYER_HEIGHT);
        displayBrokenString(gr, x + 50, y, "You can't get hit,\nyou're in shield!");

        // laser cool
        x = 470;
        y = 400;
        gr.setColor(Color.RED);
        gr.fillRect(x, y - 10, Config.DEFAULT_PLAYER_WIDTH, 10);
        gr.fillRect(x, y + Config.DEFAULT_PLAYER_HEIGHT, Config.DEFAULT_PLAYER_WIDTH, 10);
        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, Config.DEFAULT_PLAYER_WIDTH, Config.DEFAULT_PLAYER_HEIGHT);
        displayBrokenString(gr, x + 50, y, "You can't shoot until\nthe red goes away!");
        // controls
        gr.drawString("Controls:", 50, 670);
        gr.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        displayBrokenString(gr, 50, 675, "Movement:\n Up and Down\n   or\n Mouse");
        displayBrokenString(gr, 70 + 50 + 100, 675, "Laser:\n Z key\n   or\n Left Mouse");
        displayBrokenString(gr, 70 + 50 + 100 + 150, 675, "Shield:\n X key\n  or\n Right Mouse");
        done.draw(gr);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (done.isInside(e)) {
            triggerCallback();
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
}
