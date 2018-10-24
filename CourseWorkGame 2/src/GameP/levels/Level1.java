package GameP.levels;

import GameP.GameMain;
import GameP.gameListeners.LifeLost;
import GameP.gameListeners.ObjectLauncher;
import GameP.bossObjects.Fire;
import GameP.bossObjects.Barrel;
import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * The level 1 of the game extends GameLevelR class .
 */
public class Level1 extends GameLevelR {

    /**
     * Override method populates the world and creates level 1.
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
        Shape platformShape = new BoxShape(2, 0.5f);
        Body platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(-9.5f, 9.5f));

        // level platforms that are tilted 
        //top
        Shape platformShape1 = new BoxShape(7, 0.5f);
        Body platform1 = new StaticBody(this, platformShape1);
        platform1.setPosition(new Vec2(-1.5f, 8.4f));
        platform1.setAngleDegrees(170);

        //top mid
        Shape platformShape2 = new BoxShape(10, 0.5f);
        Body platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(2.5f, 2.5f));
        platform2.setAngleDegrees(5);
        // bottom mid
        Shape platformShape3 = new BoxShape(10, 0.5f);
        Body platform3 = new StaticBody(this, platformShape3);
        platform3.setPosition(new Vec2(-2.5f, -4.5f));
        platform3.setAngleDegrees(175);

        // bottom
        Shape platformShape4 = new BoxShape(10, 0.5f);
        Body platform4 = new StaticBody(this, platformShape4);
        platform4.setPosition(new Vec2(2.5f, -10.5f));
        platform4.setAngleDegrees(5);

        // sensor to detect and lauch objects 
        Sensor sen[] = new Sensor[4];
        for (int i = 0; i < 4; i++) {
            sen[i] = new Sensor(new StaticBody(this), new BoxShape(1, 3));

        }
        // set postisions of the sensors 
        for (int i = 0; i < sen.length; i++) {
            sen[i].getBody().setPosition(new Vec2(0, -15 + (i * 8)));

        }
        // the fire obstical 
        Fire fire = new Fire(this, getHero(), getBoss());
        fire.addCollisionListener(new LifeLost());

        //the barrel bostical 
        Barrel barrel = new Barrel(this, getHero(), getBoss());

        barrel.setPosition(new Vec2(-7f, 8f));
        // listener added to object 
        barrel.addCollisionListener(new LifeLost());

        barrel.bouncyBarrel();

        //  add listener class for the sensor to lauch the obsicals
        for (int i = 0; i < sen.length; i++) {
            sen[i].addSensorListener(new ObjectLauncher(barrel, getHero(), fire));
        }
    }

    /**
     * Override method returns start position of hero (Vec2).
     *
     * @return hero position.
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(-7, -7);
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
