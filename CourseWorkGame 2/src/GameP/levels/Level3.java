/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.levels;

import GameP.GameMain;
import GameP.gameListeners.LifeLost;
import GameP.bossObjects.Barrel;
import GameP.characters.AngryBoss;
import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * The level 3 of the game extends GameLevelR class .
 */
public class Level3 extends GameLevelR {

    /**
     * Override method populates the world and creates level 3.
     *
     * @param game is the game the level will populate.
     */
    @Override
    public void populate(GameMain game) {
        super.populate(game);

        //base platform and roof
        Shape groundShape = new BoxShape(11.5f, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -15.5f));
        // roof platform
        Shape roofShape = new BoxShape(12.5f, 0.5f);
        Body roof = new StaticBody(this, roofShape);
        roof.setPosition(new Vec2(0, 16.f));

        //walls
        Shape wallShapeL = new BoxShape(0.5f, 14);
        Shape wallShapeR = new BoxShape(0.5f, 17);
        Body leftWall = new StaticBody(this, wallShapeL);
        leftWall.setPosition(new Vec2(-12f, -4));
        Body rightWall = new StaticBody(this, wallShapeR);
        rightWall.setPosition(new Vec2(12, 0));

        // game platforms 
        //top platform the boss sits on 
        Shape platformShape = new BoxShape(10, 0.5f);
        Body platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(-9.5f, 9.5f));

        // boss minion 
        AngryBoss minion = new AngryBoss(this, getHero());
        minion.addCollisionListener(new LifeLost());
        minion.setPosition(new Vec2(2, -12.5f));

        minion.setLinearVelocity(new Vec2(-9, 0));

        Barrel barrel = new Barrel(this, getHero(), minion);
        minion.setBarrel(barrel);

    }

    /**
     * Override method returns start position of hero (Vec2).
     *
     * @return hero position.
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(-9, -14);
    }

    /**
     * Override method returns position of enemy boss(Vec2).
     *
     * @return boss position.
     */
    @Override
    public Vec2 bossPosition() {
        return new Vec2(-6, 12);
    }

}
