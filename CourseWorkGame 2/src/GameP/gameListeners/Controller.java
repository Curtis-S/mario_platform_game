/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.gameListeners;

import GameP.characters.Hero;
import city.cs.engine.BodyImage;
import city.cs.engine.Walker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.jbox2d.common.Vec2;

/**
 * first game controller that extends KeyAdapter. .
 */
public class Controller extends KeyAdapter {

    private final float WalkingSpeed = 6;
    private final float JumpingSpeed = 12;
    private Hero hero;
    private boolean level1 = true;

//constuctor
    /**
     * Accepts an hero instance that the controller will control during the game
     * .
     *
     * @param hero is the hero that will be controlled.
     */
    public Controller(Hero hero) {
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
            // it was deleiberatly decided to make the character jump infinte times for level 1 
            // becuase it would be hard to dodge to obsticals otherwise 
            case KeyEvent.VK_SPACE:
                // SPACE = jump
                       if (level1){
                hero.jump(JumpingSpeed);
                // due to the platforms being sloped i had to add velocity increase instead of jump method 
                //becuase jump method has a hard time functioning on sloped platforms 
               hero.setLinearVelocity(new Vec2(0, 13));
                       
                       }else{
                       hero.jump(JumpingSpeed);
                       
                       }

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
     * Sets the controller on a i body of the hero instance .
     *
     * @param body the body of the hero.
     */
     public void setBody(Hero body) {
        this.hero = body;
    }
    /**
     * Sets  the level1 boolean to true or false and changes the key events depending to respond to the result.
     *
     * @param level1 is the true or false value of if its level one or not.
     */
    public void setLevel1(boolean level1) {
        this.level1 = level1;
    }
     
}
