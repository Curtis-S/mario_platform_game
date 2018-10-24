/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.gameListeners;

import GameP.characters.Hero;
import city.cs.engine.BodyImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Second game controller that extends KeyAdapter. .
 */
public class Controller2 extends KeyAdapter {

    private final float WalkingSpeed = 6;
    private final float JumpingSpeed = 13;
    private Hero hero;

    /**
     * Accepts an hero instance that the controller will control during the game
     * .
     *
     * @param hero is the hero that will be getting controlled.
     */
    public Controller2(Hero hero) {
        this.hero = hero;
    }
// controls to move charater 
    // "a" for left and "d " for right "SPACE" for jump "Q " for quit  

    /**
     * Override method that specifies what keys cause events in the game.
     *
     * @param e is the key pressed. 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {

            case KeyEvent.VK_Q:
                // Q = quit
                System.exit(0);
                break;

            // walking key events 
            case KeyEvent.VK_A:
                hero.startWalking(-WalkingSpeed); // a = walk left
                hero.removeAllImages();
                hero.addImage(new BodyImage("data/hero13.gif", 2.25f));
                break;
            case KeyEvent.VK_D:
                hero.startWalking(WalkingSpeed); // d = walk right
                hero.removeAllImages();
                hero.addImage(new BodyImage("data/hero12.gif", 2.25f));
                break;
            default:
                break;
        }
    }

    // stop walking when key is relased 
    /**
     * Override method that specifies what keys when released cause events in
     * the game.
     *
     * @param e is the key released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            hero.stopWalking();
            hero.removeAllImages();
            hero.addImage(new BodyImage("data/ma1.png", 2.25f));
        } else if (code == KeyEvent.VK_D) {
            hero.stopWalking();
            hero.removeAllImages();
            hero.addImage(new BodyImage("data/ma1.png", 2.25f));
        }
    }

    /**
     * Sets the controller on a body of the hero instance .
     *
     * @param body is the body to control.
     */
    public void setBody(Hero body) {
        this.hero = body;
    }
}
