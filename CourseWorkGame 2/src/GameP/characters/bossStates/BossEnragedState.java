/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.characters.bossStates;

import GameP.characters.AngryBoss;
import GameP.fsm.FSMState;
import org.jbox2d.common.Vec2;

/**
 * A State of the mini boss.
 */
public class BossEnragedState extends FSMState<AngryBoss> {

    /**
     * Override method that gets executed when game criteria is met .
     */
    @Override
    protected void update() {
        AngryBoss boss = getContext();
        if (boss.getLives() <= 2) {
            gotoState(new BossEnragedState());
        }
    }

    /**
     * Override method that gets executed when AngryBoss enters this state.
     */
    @Override
    protected void enter() {
        AngryBoss boss = getContext();
        boss.getBarrel().bouncyBarrel2();
        if (boss.isCrazy()) {
            boss.setLinearVelocity(new Vec2(-15, 5));
           // System.out.println("going left");

            boss.setCrazy(false);
        } else if (!boss.isCrazy()) {
            boss.setLinearVelocity(new Vec2(15, 5));
           // System.out.println("going right");
            boss.setCrazy(true);
        }
    }

    /**
     * Override method that gets executed when AngryBoss leaves this State .
     */
    @Override
    protected void exit() {

    }
}
