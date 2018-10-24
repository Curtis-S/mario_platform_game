package GameP.levels;

import GameP.GameMain;
import java.awt.Graphics2D;
import city.cs.engine.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * extended view class that displays the game for the user .
 */
public class ScoreView extends UserView {

    private GameMain game;
    private final Image background = new ImageIcon("data/level1.png").getImage();
    private final Image background2 = new ImageIcon("data/bosslevel.jpg").getImage();
    private final Image background3 = new ImageIcon("data/bosslevel2.jpg").getImage();
    private Timer t;
    private int delay = 1000; //milliseconds
    private int time = 1;

    /**
     * initializes the view that displays the game levels.
     *
     * @param world is the world that this class is apart of.
     * @param game is the game that this class is apart of.
     * @param width is the width of the view .
     * @param height is the height of the view.
     */
    public ScoreView(World world, GameMain game, int width, int height) {
        super(world, width, height);
        this.game = game;
        t = new Timer(delay, taskPerformer);
        t.start();

    }

    /**
     * returns the amount of time that as passed.
     *
     * @return time digits
     */
    public int getTim() {
        return time;
    }

//uptate timer 
    ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            game.timeUp();
        }
    };

    /**
     * Override method that sets the background of the levels.
     *
     * @param g is graphics 2d.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        switch (game.getLevel()) {
            case 1:
                g.drawImage(background, 0, 0, this);
                break;
            case 2:
                g.drawImage(background2, 0, 0, this);
                break;
            case 3:
                g.drawImage(background3, 0, 0, this);
                break;
            default:
                break;
        }
    }

    /**
     * Override method that displays the player lives, time passed and the
     * player name.
     *
     * @param g is graphics 2d.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString("lives: " + game.returnLives(), 330, 30
        );
        g.drawString("Time: " + game.getTim(), 5, 30);

        g.drawString("Player Name: " + game.getPlayerName() + "     Score: " + game.getPlayerScore(), 80, 30);

    }
}
