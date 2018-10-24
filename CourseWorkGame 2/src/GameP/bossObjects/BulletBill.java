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
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * The bullet of the game.
 */
public class BulletBill extends DynamicBody implements Collideable {

    private World world;
    private Hero hero;
    private static Shape bulletShape = new BoxShape(1.2f, 0.8f);
    private static final BodyImage image2 = new BodyImage("data/testbullet2.png", 1.5f);
    private static final BodyImage image = new BodyImage("data/testbullet1.png", 1.5f);
    private Boss boss;
    private AngryBoss boss2;
//constructor 

    /**
     * initializes and creates a bullet from a shape and adds an image.
     *
     * @param v is the world the bullet belongs to.
     * @param h is the hero that will be interacting with the bullet.
     */
    public BulletBill(World v, Hero h) {
        super(v);

        this.hero = h;
        this.world = v;

        SolidFixture headFixture = new SolidFixture(this, bulletShape, 1);
        addImage(image);
        headFixture.setFriction(0);
        headFixture.setRestitution(0);

    }

    /**
     * Launches the bullet to the left .
     *
     * @param x the x positon of the bullet(float).
     * @param y the y positon of the bullet (float).
     */
    public void launchLeft(float x, float y) {
        BulletBill b = new BulletBill(world, hero);
        b.setPosition(new Vec2(x, y));
        b.setGravityScale(0);
        b.setLinearVelocity(new Vec2(-6, 0));
        b.addCollisionListener(new LifeLost());
    }

    /**
     * Launches the bullet to the right .
     *
     * @param x the x positon of the bullet(float).
     * @param y the y positon of the bullet (float).
     */
    public void launchRight(float x, float y) {
        BulletBill b = new BulletBill(world, hero);
        b.removeAllImages();
        b.addImage(image2);
        b.setPosition(new Vec2(x, y));
        b.setGravityScale(0);
        b.setLinearVelocity(new Vec2(6, 0));
        b.addCollisionListener(new LifeLost());

    }

    /**
     * Override method that executes when hero or wall touches the bullet .
     *
     * @param b is the body of the object touching the portal.
     */
    @Override
    public void collisionResponse(Body b) {
        if (b == hero && this.getPosition().x > b.getPosition().x) {
            hero.loseLives();
            hero.setLinearVelocity(new Vec2(-5, 4));
            destroy();
        } else if (b == hero && this.getPosition().x < b.getPosition().x) {
            hero.loseLives();
            hero.setLinearVelocity(new Vec2(5, 4));
            destroy();

        } else {
            hero.setLinearVelocity(new Vec2(0, 4));
        }
        destroy();
    }

}
