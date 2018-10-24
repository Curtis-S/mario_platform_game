/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.bossObjects;

import GameP.gameListeners.Collideable;
import GameP.gameListeners.LifeLost;
import GameP.characters.Boss;
import GameP.characters.AngryBoss;
import GameP.characters.Hero;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * The barrel of the game.
 */
public class Barrel extends DynamicBody implements Collideable {

    private final float radius = 0.5f;
    private int lives = 15;
    private World view;
    private Hero hero;
    private static final Shape barrelShape = new CircleShape(0.5f);
    private static final BodyImage image
            = new BodyImage("data/tb.png", 1.25f);
    private Boss boss;
    private AngryBoss boss2;

// constructor
    /**
     * initializes and creates a barrel from a shape and adds an image.
     *
     * @param v is the world the barrel belongs to.
     * @param h is the hero that will be interacting with the barrel.
     * @param b is the boss that will be interacting with the barrel.
     */
    public Barrel(World v, Hero h, Boss b) {
        super(v, barrelShape);
        addImage(image);
        this.hero = h;
        this.view = v;
        this.boss = b;
    }

    /**
     * initializes and creates a barrel from a shape and adds an image.
     *
     * @param v is the world the barrel belongs to.
     * @param h is the hero that will be interacting with the barrel.
     * @param a is the boss that will be interacting with barrel.
     */
    public Barrel(World v, Hero h, AngryBoss a) {
        super(v, barrelShape);
        addImage(image);
        this.boss2 = a;
        this.hero = h;
        this.view = v;
    }

    /**
     * Method to launch a bouncing barrel for the Boss .
     */
    public void bouncyBarrel() {
//barrel gets lauched at random angles and at random speeds 
// also each barrel has a collison listner to react to contact to objects or hero

        float x = (float) (Math.random() * 15 + 1);
        float y = (float) (Math.random() * 4 + 1);
        Barrel barrel = new Barrel(view, hero, boss);
        barrel.setPosition(new Vec2(boss.getPosition().x + 2, boss.getPosition().y));
        barrel.setLinearVelocity(new Vec2(x, y));
        barrel.addCollisionListener(new LifeLost());

        SolidFixture f = new SolidFixture(barrel, barrelShape, 25);
        f.setRestitution(1f);

    }

    /**
     * Method to launch a bouncing barrel for AngryBoss .
     */
    public void bouncyBarrel2() {
//barrel gets lauched at random angles and at random speeds 
// also each barrel has a collison listner to react to contact to objects or hero

        float x = (float) (Math.random() * 15 + 1);
        float y = (float) (Math.random() * 18 + 1);
        Barrel barrel = new Barrel(view, hero, boss2);
        barrel.setPosition(new Vec2(boss2.getPosition().x + 5, boss2.getPosition().y + 3));
        barrel.setLinearVelocity(new Vec2(x, y));
        barrel.addCollisionListener(new LifeLost());

        SolidFixture f = new SolidFixture(barrel, barrelShape, 25);
        f.setRestitution(1f);

    }

    /**
     * Override method that executes when bodies touch the barrel .
     *
     * @param b is the body of the object touching the barrel.
     */
    @Override
    public void collisionResponse(Body b) {
        lives--;
        if (b == hero) {
            hero.loseLives();
            hero.setLinearVelocity(new Vec2(0, 3));
            destroy();
        }
        if (lives == 0) {
            destroy();
        }
    }

}
