/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.characters;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * The hero of the game, extends Walker.
 */
public class Hero extends Walker {

    private static final Shape hero = new BoxShape(1, 1);
    private static final BodyImage image
            = new BodyImage("data/ma1.png", 2.25f);
    private static int lives = 5;
    private int livescopy = lives - 1;
    private LifeFaces[] l;
    World world;

    //constuctor 
    /**
     * initializes the Hero and creates the body,adds the image and sets the
     * amount of lives .
     *
     * @param world is the world th hero will belong to.
     */
    public Hero(World world) {
        super(world, hero);
        addImage(image);
        //faces for the lives 
        l = new LifeFaces[lives];
        for (int i = 0; i < lives; i++) {

            l[i] = new LifeFaces(new StaticBody(world));

        }
        // set postions for the faces 
        for (int i = 0; i < getLives(); i++) {

            l[i].getBody().setPosition(new Vec2(7 + i, 14.9f));

        }
    }

    /**
     * updates the heros life when the hero gets attacked by another object .
     */
    public void loseLives() {

        lives--;
// when he loses a life to destroy a life icon also that is displayed on the top of the window
        for (int i = 0; i < livescopy + 1; i++) {
            if (lives == livescopy - i) {
                l[i].getBody().destroy();
                break;
            }

        }
        if (lives == 0) {
            System.out.println(" Game over");
            System.exit(0);
        } else {
            System.out.println("Lost a life, you have " + lives + " remaining");

        }

    }

    /**
     * Resets the heros lives.
     */
    public void resetLives() {
        lives = 5;

    }

    /**
     * gets the current lives remaining .
     *
     * @return the amount of lives remaining.
     */
    public int getLives() {
        return lives;
    }

}
