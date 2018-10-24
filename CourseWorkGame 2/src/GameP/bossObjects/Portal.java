/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.bossObjects;

import GameP.gameListeners.Collideable;
import GameP.characters.Hero;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * The portal of the game.
 */
public class Portal extends StaticBody implements Collideable {

    Hero hero;
    private int teleportDist = 6;
    private static final BodyImage image = new BodyImage("data/portal.gif", 2.25f);

    private static SoundClip sfx;

    /**
     * initializes and creates a portal from a shape and adds an image.
     *
     * @param world is the world the hero belongs to.
     * @param h is the hero that will be interacting with the star.
     */
    public Portal(World world, Hero h) {
        super(world, new BoxShape(0.55f, 1f));
        addImage(image);
        this.hero = h;
        try {
            sfx = new SoundClip("data/warp.wav");
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
        sfx.setVolume(0.5);
    }

    /**
     * sets the distance for the hero to teleport.
     *
     * @param teleportDist is the distance (int).
     */
    public void setTeleportDist(int teleportDist) {
        this.teleportDist = teleportDist;
    }

    /**
     * Override method that launches when hero touches the portal .
     *
     * @param b is the body of the object touching the portal.
     */
    @Override
    public void collisionResponse(Body b) {

        if (b == hero) {

            hero.setPosition(new Vec2(0, hero.getPosition().y + teleportDist));
            sfx.play();
        }

    }

}
