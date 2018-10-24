/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.gameListeners;

import city.cs.engine.Body;

/**
 * An interface that is meant to be implemented by Objects that will have
 * collision responses in the game.
 */
public interface Collideable {

    /**
     * the method that that executes the collision response on the Body that
     * implements this interface.
     *
     * @param body is a body.
     */
    public void collisionResponse(Body body);
}
