package GameP.characters.bossStates;

import GameP.characters.AngryBoss;
import GameP.fsm.FSMState;
import org.jbox2d.common.Vec2;

/**
 * A State of the mini boss.
 */
public class BossAttackState extends FSMState<AngryBoss> {

    public BossAttackState() {
    }

    /**
     * Override method that gets executed when game criteria is met .
     */
    @Override
    protected void update() {
        AngryBoss boss = getContext();
        if (boss.getLives() == 3) {
            gotoState(new BossAttackState());
        } else if (boss.getLives() < 3) {
            gotoState(new BossEnragedState());
        }
    }

    /**
     * Override method that gets executed when AngryBoss enters this state.
     */
    @Override
    protected void enter() {
        AngryBoss boss = getContext();
        if (boss.isLeft()) {
            boss.setLinearVelocity(new Vec2(-10, 0));
            System.out.println("going left");
        } else {
            boss.setLinearVelocity(new Vec2(10, 0));
            System.out.println("going right");
        }
    }

    /**
     * Override method that gets executed when AngryBoss leaves this State .
     */
    @Override
    protected void exit() {

    }

}
