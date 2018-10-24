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
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * The Fire of the game.
 */
public class Fire extends DynamicBody implements Collideable {

    private World world;
    private Hero hero;
    private static Shape headShape = new PolygonShape(-0.929f, 0.339f, 0.931f, 0.342f, 0.931f, -0.372f, -0.929f, -0.372f);

    private static final BodyImage image = new BodyImage("data/fire3.png", 1.5f);
    private Boss boss;
    private AngryBoss boss2;
    private int lives = 15;
//constructor 

    /**
     * initializes and creates a fire from a shape and adds an image.
     *
     * @param v is the world the fire belongs to.
     * @param h is the hero that will be interacting with the star.
     * @param b is the boss that will be interacting with the fire.
     */
    public Fire(World v, Hero h, Boss b) {
        super(v);
        this.boss = b;
        this.hero = h;
        this.world = v;

        SolidFixture headFixture = new SolidFixture(this, headShape, 1);
        addImage(image);
        headFixture.setFriction(0);

    }

    /**
     * initializes and creates a fire from a shape and adds an image.
     *
     * @param v is the world the fire belongs to.
     * @param h is the hero that will be interacting with the star.
     * @param b is the boss that will be interacting with the fire.
     */
    public Fire(World v, Hero h, AngryBoss b) {
        super(v);
        this.boss2 = b;
        this.hero = h;
        this.world = v;

        SolidFixture headFixture = new SolidFixture(this, headShape, 1);
        addImage(image);
        headFixture.setFriction(0);

    }
// method to lauch a fire object when sensor triggered

    /**
     * method to launch the fire when sensor is triggered.
     */
    public void lauchFire() {
        Fire fire = new Fire(world, hero, boss);
        fire.setPosition(new Vec2(boss.getPosition().x + 2, boss.getPosition().y));
        fire.setLinearVelocity(new Vec2(4, 0));
        fire.addCollisionListener(new LifeLost());
    }

    /**
     * method to launch the fire when triggered by AngryBoss.
     */
    public void lauchFire2() {
        Fire fire = new Fire(world, hero, boss2);
        fire.setPosition(new Vec2(boss2.getPosition().x + 2, boss2.getPosition().y));
        fire.setLinearVelocity(new Vec2(4, 0));
        fire.addCollisionListener(new LifeLost());
    }

    /**
     * Override method that launches when hero touches the fire .
     *
     * @param b is the body of the object touching the portal.
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
