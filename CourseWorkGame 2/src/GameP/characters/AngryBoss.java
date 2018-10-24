/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.characters;

import GameP.bossObjects.Barrel;
import GameP.gameListeners.Collideable;
import GameP.gameListeners.LifeLost;
import GameP.bossObjects.Portal;
import GameP.characters.bossStates.BossAttackState;
import GameP.fsm.FSM;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import org.jbox2d.common.Vec2;

/**
 * the Mini boss of the last level.
 */
public class AngryBoss extends DynamicBody implements Collideable, StepListener {

    private static final Shape bossShape = new BoxShape(1.5f, 1.5f);
    private static final BodyImage image
            = new BodyImage("data/bossk.gif", 3.25f);
    private final BodyImage image2
            = new BodyImage("data/bossdefeat.gif", 3.25f);
    public static final float RANGE = 5;
    private boolean BossDefeated = false;
    private World world;
    private Hero hero;
    private Barrel barrel;
    private int lives = 3;
    private Timer tmr;
    private int time = 0;
    private double bossTemp = 6;
    private FSM<AngryBoss> fsm;
    private boolean crazy = true;
//contrustor 

    /**
     * initializes and creates the boss from a shape and adds an image.
     *
     * @param w is the world the boss belongs to .
     * @param hero is the hero that will be interacting with this boss.
     */
    public AngryBoss(World w, Hero hero) {
        super(w);

        SolidFixture BossFixture = new SolidFixture(this, bossShape, 700);
        BossFixture.setFriction(0);
        addImage(image);
        this.world = w;
        this.hero = hero;

        fsm = new FSM<AngryBoss>(this, new BossAttackState());
        tmr = new Timer(1000, taskPerformer);
        tmr.start();
        getWorld().addStepListener(this);

    }

    //uptate timer 
    ActionListener taskPerformer = new ActionListener() {
        /**
         * Override method that updates fsm depending on how much time has
         * passed.
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            time++;
            if (time % bossTemp == 0 && lives > 0) {

                fsm.update();

                time = 0;
            }

        }
    };

//getters 
    /**
     * returns the remaining lives of the boss .
     *
     * @return the remaining lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns the barrel to be used by the boss.
     *
     * @return the barrel instance
     */
    public Barrel getBarrel() {
        return barrel;
    }

    /**
     * sets the barrel instance to this boss.
     *
     * @param barrel is the barrel to be added.
     */
    public void setBarrel(Barrel barrel) {
        this.barrel = barrel;
    }

    /**
     * to to change the behavior of this instance based of being true or false .
     *
     * @return value of crazy.
     */
    public boolean isCrazy() {
        return crazy;
    }

    /**
     * sets the value of crazy to true or false.
     *
     * @param crazy is the status of crazy (true or false).
     */
    public void setCrazy(boolean crazy) {
        this.crazy = crazy;
    }

    /**
     * reduces lives and updates fsm when boss is hit .
     */
    public void bossHit() {

        lives--;
        bossTemp = 0.5;
        if (fsm != null) {
            fsm.update();
        }
        if (lives == 0) {
            fsm.stop();
            fsm = null;
            this.removeAllImages();
            this.addImage(image2);
        }

        if (lives < 0) {
            this.destroy();
            BossDefeated();
        }

    }

    /**
     * spawns a portal then attaches a listener to it when this boss is defeated.
     * .
     */
    public void BossDefeated() {

        Portal portal = new Portal(world, hero);
        portal.setPosition(new Vec2(11, -14f));
        portal.setTeleportDist(25);
        portal.addCollisionListener(new LifeLost());

    }

    /**
     * checks what position the hero is to the boss.
     *
     * @return true or false.
     */
    public boolean isLeft() {

        return hero.getPosition().x < this.getPosition().x;
    }

    /**
     * Override method that executes when hero touches the boss .
     *
     * @param b is the body of the object touching the boss.
     */
    @Override
    public void collisionResponse(Body b) {
        if (b == hero) {
            bossHit();

            if (b == hero && this.getPosition().x > b.getPosition().x) {
                hero.setLinearVelocity(new Vec2(-30, 20));
            } else if (b == hero && this.getPosition().x < b.getPosition().x) {
                hero.setLinearVelocity(new Vec2(30, 20));

            }

        }

    }

    @Override
    public void preStep(StepEvent e) {

    }

    @Override
    public void postStep(StepEvent e) {

    }

}
