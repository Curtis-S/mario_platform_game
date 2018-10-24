/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.bossObjects;

import GameP.GameMain;
import GameP.gameListeners.Collideable;
import GameP.characters.Boss;
import GameP.characters.Hero;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;

/**
 * The progress to a next level star.
 */
public class StarFinish extends DynamicBody implements Collideable {

    private World world;
    private Hero hero;
    private static final Shape starShape = new CircleShape(0.5f);

    private static final BodyImage image = new BodyImage("data/star.gif", 1.5f);
    private Boss boss;
    private GameMain game;

    /**
     * initializes and creates the star from a shape and adds an image.
     *
     * @param w is the world the star belongs to .
     * @param g is the game the star is apart of.
     * @param h is the hero that will be interacting with the star.
     */
    public StarFinish(World w, GameMain g, Hero h) {
        super(w, starShape);
        addImage(image);
        this.game = g;
        this.hero = h;
    }

    /**
     * Override method that launches when hero touches the star .
     *
     * @param b is the body of the object touching the star.
     */
    @Override
    public void collisionResponse(Body b) {
        if (b == hero) {
            System.out.println("Going to next level...");
            game.goNextLevel();
        }
    }

}
