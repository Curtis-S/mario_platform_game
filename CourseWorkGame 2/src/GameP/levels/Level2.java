/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.levels;

import GameP.GameMain;
import GameP.gameListeners.LifeLost;
import GameP.gameListeners.ObjectLauncher;
import GameP.bossObjects.Portal;
import GameP.bossObjects.BulletBill;
import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * The level 2 of the game extends GameLevelR class .
 */
public class Level2 extends GameLevelR {

    /**
     * Override method populates the world and creates level 2.
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
        Shape platformShape = new BoxShape(11.5f, 0.5f);
        Body platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(0, 9.5f));

        // level platforms that are tilted 
        Body platforms[] = new StaticBody[3];
        for (int i = 0; i < platforms.length; i++) {
            platforms[i] = new StaticBody(this, new BoxShape(11.5f, 0.5f));
        }
        for (int i = 0; i < platforms.length; i++) {
            platforms[i].setPosition(new Vec2(0, -9.5f + (i * 6)));
        }

        //bullets 
        BulletBill bullet = new BulletBill(this, getHero());
        bullet.setPosition(new Vec2(8, -1));
        bullet.setGravityScale(0);
        bullet.setLinearVelocity(new Vec2(-10, 0));
        bullet.addCollisionListener(new LifeLost());

        Sensor sen[] = new Sensor[4];
        for (int i = 0; i < sen.length; i++) {
            sen[i] = new Sensor(new StaticBody(this), new BoxShape(1, 1));
            sen[i].getBody().setPosition(new Vec2(0, -14 + (i * 6)));
            sen[i].addSensorListener(new ObjectLauncher(getHero(), bullet));
        }

//        sen.getBody().addImage(new BodyImage("data/portal.gif",1.5f));
        //Portals
        Portal portals[] = new Portal[8];
        for (int i = 0; i < portals.length; i++) {
            portals[i] = new Portal(this, getHero());
        }
        for (int i = 0; i < 4; i++) {
            portals[i].setPosition(new Vec2(-10.5f, -13.9f + (i * 6)));
        }

        for (int i = 0; i < 4; i++) {
            portals[i + 4].setPosition(new Vec2(10.5f, -13.9f + (i * 6)));
        }
        for (int i = 0; i < 8; i++) {
            portals[i].addCollisionListener(new LifeLost());
        }

    }

    /**
     * Override method returns start position of hero (Vec2).
     *
     * @return hero position.
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -12);
    }

    /**
     * Override method returns position of enemy boss(Vec2).
     *
     * @return boss position.
     */
    @Override
    public Vec2 bossPosition() {
        return new Vec2(-10, 12);
    }

}
