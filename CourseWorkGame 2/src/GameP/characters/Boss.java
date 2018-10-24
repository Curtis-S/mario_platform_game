/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.characters;

import GameP.gameListeners.Collideable;
import GameP.GameMain;
import GameP.gameListeners.LifeLost;
import GameP.bossObjects.StarFinish;
import GameP.characters.Hero;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * The main Boss of the game, extends static body .
 */
public class Boss extends StaticBody implements Collideable {

    private static final Shape bossShape = new BoxShape(2, 2);
    private static final BodyImage image
            = new BodyImage("data/bossk.gif", 4.25f);
    private static final BodyImage image2
            = new BodyImage("data/bossdefeat.gif", 4.25f);
    private SoundClip gameMusic;
    private boolean bossKill = false;
    private World world;
    private GameMain game;
    private Hero hero;

//contrustor 
    /**
     * initializes and creates the boss from a shape and adds an image.
     *
     * @param w is the world the boss belongs to .
     * @param g is the game the boss is apart of.
     * @param h is the hero that will be interacting with the boss.
     */
    public Boss(World w, GameMain g, Hero h) {
        super(w, bossShape);
        addImage(image);
        this.world = w;
        this.game = g;
        this.hero = h;
        try {
            gameMusic = new SoundClip("data/star.wav");   // Open an audio input stream

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }

    /**
     * returns the second image to be applied to the boss.
     *
     * @return the second image.
     */
    public BodyImage getImage2() {
        return image2;
    }

    /**
     * When the boss gets defeated create a star that provides the entrance to
     * the next level .
     */
    public void BossDefeated() {
        if (bossKill == false) {
            StarFinish star = new StarFinish(world, game, hero);
            star.setPosition(new Vec2(this.getPosition().x + 7, this.getPosition().y + 2));
            star.addCollisionListener(new LifeLost());
            gameMusic.play();
            bossKill = true;
        }

    }

    /**
     * Override method that launches when hero touches the boss .
     *
     * @param b is the body of the object touching the boss.
     */
    @Override
    public void collisionResponse(Body b) {
        if (b == hero) {
            this.removeAllImages();
            this.addImage(this.getImage2());
            BossDefeated();
        }
    }

}
