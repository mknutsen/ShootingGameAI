package mknutsen.aiproject.game;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.Player;
import mknutsen.aiproject.player.ai.decisiontree.LeveledDecisionTree;
import mknutsen.graphicslibrary.GraphicsComponent;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Game component is the component that facillitates gameplay
 *
 * @author mknutsen
 */
public class GameComponent extends GraphicsComponent {

    private static final long serialVersionUID = 1L;

    private Player player1, player2;

    private ArrayList<Bullet> bullets;

    private boolean engaged;

    /**
     * sets up game and activates any listeners.
     *
     * @param p1
     *         player 1 (left) for this game
     * @param p2
     *         player 2 (right) for this game
     */
    public GameComponent(Player p1, Player p2) {
        System.out.println("i exist");
        engaged = false;
        bullets = new ArrayList<Bullet>();
        player1 = p1;
        player2 = p2;
        player1.setX(Config.P_ONE_X);
        player2.setX(Config.P_TWO_X);
        player1.setGoalY(Config.DEFAULT_Y);
        player2.setGoalY(Config.DEFAULT_Y);
        player2.setGame(this);
        player1.setGame(this);
        if (player1 instanceof MouseListener) {
            addMouseListener((MouseListener) player1);
        }
        if (player1 instanceof MouseMotionListener) {
            addMouseMotionListener((MouseMotionListener) player1);
        }
        if (player1 instanceof KeyListener) {
            addKeyListener((KeyListener) player1);
        }
        if (player2 instanceof MouseListener) {
            addMouseListener((MouseListener) player2);
        }
        if (player2 instanceof MouseMotionListener) {
            addMouseMotionListener((MouseMotionListener) player2);
        }
        if (player2 instanceof KeyListener) {
            addKeyListener((KeyListener) player2);
        }
    }

    @Override
    public void paint(Graphics gr) {
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, 900, 900);
        gr.setColor(Color.BLACK);
        gr.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gr.drawString(Integer.toString(player1.getHealth()), 80, 20);
        gr.drawString(Integer.toString(player2.getHealth()), 700, 20);

        player1.draw(gr);
        player2.draw(gr);
        int i = 0;
        while (i < bullets.size()) {
            if (!bullets.get(i).draw(gr)) {
                bullets.remove(i);
            } else if (player1.isOverlappingMaster(bullets.get(i))) {
                player1.hit();
                bullets.remove(i);
            } else if (player2.isOverlappingMaster(bullets.get(i))) {
                player2.hit();
                bullets.remove(i);
            } else {
                i++;
            }
        }
        if (!engaged) {
            Font temp = gr.getFont();
            Font newFont = new Font("Times New Roman", 0, 150);
            gr.setFont(newFont);
            gr.drawString("ENGAGE", 100, 450);
            gr.setFont(temp);
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void end() {
        triggerCallback(player1.getHealth() > player2.getHealth() ? 0 : 1);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void engaged() {
        if (!engaged) {
            engaged = true;
            if (player2 instanceof Runnable) {
                new Thread((Runnable) player2).start();
            }
            if (player1 instanceof Runnable) {
                new Thread((Runnable) player1).start();
            }
        }
    }

    @Override
    public void takeParameters(Object[] obj) {
        if (obj != null && obj.length != 0) {
            if (player1 instanceof LeveledDecisionTree) {
                ((LeveledDecisionTree) player1).setAILevel((int) obj[0]);
            }

            if (player2 instanceof LeveledDecisionTree) {
                ((LeveledDecisionTree) player2).setAILevel((int) obj[0]);
            }
        }
    }
}
