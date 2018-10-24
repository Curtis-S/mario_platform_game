/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.gameListeners;

import GameP.characters.Hero;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * Implements CollisionListener, LifeLost handles all interactions of Instances
 * of the Collideable interface .
 */
// this is the collisonerLisner class that  monitors all barrels and fires lauched 
public class LifeLost implements CollisionListener {

    private Hero hero;

    //consructor 
    public LifeLost() {

    }
// method for collison event

    /**
     * Checks if reporting body is an instance of Collideable and if so then the
     * collision response method for that instance is then executed.
     *
     * @param e is collision event.
     */
    @Override
    public void collide(CollisionEvent e) {

        // if hero is touched by lauched obsitcal then hero loses a life and oject is also detroyed
        if (e.getReportingBody() instanceof Collideable) {

//i commented because of the amount of ojects the system out print was getting to clogged with info
            // however uncommet if you want to see the oject lifes
            //System.out.println("the obeject as "+Objectlives+"lifes remaning");
            Collideable c = (Collideable) e.getReportingBody();
            c.collisionResponse(e.getOtherBody());
        }

    }

}
