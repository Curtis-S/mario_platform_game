
package GameP;

import GameP.fsm.FSMState;

/**
 *
 * The game state child class of the finite state machine of the GameMain type.
 */
public class MenuState extends FSMState<GameMain> {

    public MenuState() {
    }

    /**
     * Override method that gets executed when game criteria is met .
     */
    @Override
    protected void update() {
        GameMain game = getContext();
        if (game.getPlayerName() == null) {
            gotoState(new GameOverState());
        } else {
            gotoState(new GameState());
        }

    }

    /**
     * Override method that gets executed when GameMain enters this state.
     */
    @Override
    protected void enter() {
        GameMain game = getContext();
        game.menuState();
    }

    /**
     * Override method that gets executed when GameMain leaves this state.
     */
    @Override
    protected void exit() {
        GameMain game = getContext();
        game.menuFrame.dispose();
    }

}
