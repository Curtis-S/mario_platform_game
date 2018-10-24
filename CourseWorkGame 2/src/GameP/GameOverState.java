/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP;

import GameP.fsm.FSMState;

/**
 *
 * The game state child class of the finite state machine of that GameMain type.
 */
public class GameOverState extends FSMState<GameMain> {

    public GameOverState() {
    }

    /**
     * Override method that gets executed when game criteria is met .
     */
    @Override
    protected void update() {

        GameMain game = getContext();
        gotoState(new MenuState());
    }

    /**
     * Override method that gets executed when GameMain enters this state.
     */
    @Override
    protected void enter() {
        GameMain game = getContext();
        game.gameOverState();
    }

    /**
     * Override method that gets executed when GameMain leaves this state.
     */
    @Override
    protected void exit() {
        GameMain game = getContext();
        game.getGameOverMusic().stop();
        game.gameOverFrame.dispose();
    }

}
