/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP;

import GameP.fsm.FSMState;

/**
 *
 * The game state child class of the finite state machine of the GameMain type.
 */
public class GameState extends FSMState<GameMain> {

    public GameState() {
    }

    /**
     * Override method that gets executed when game criteria is met .
     */
    @Override
    protected void update() {
        GameMain game = getContext();
        gotoState(new GameOverState());

    }

    /**
     * Override method that gets executed when GameMain enters this state.
     */
    @Override
    protected void enter() {
        GameMain game = getContext();
        game.gameState();
    }

    /**
     * Override method that gets executed when GameMain leaves this state.
     */
    @Override
    protected void exit() {
        GameMain game = getContext();
        game.getBossMusic().stop();
        game.getGameMusic().stop();
        game.gameFrame.dispose();
        
    }

}
