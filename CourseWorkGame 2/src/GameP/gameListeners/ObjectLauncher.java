/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.gameListeners;

import GameP.bossObjects.Fire;
import GameP.bossObjects.BulletBill;
import GameP.bossObjects.Barrel;
import GameP.characters.Hero;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

/**
 * An object launcher ,extends SensorListner and adds on to a sensor body.
 */
public class ObjectLauncher implements SensorListener {

    private Barrel barrel;
    private Hero hero;
    private Fire fire;
    private int sensorLife = 2;
    private BulletBill bullet;

    /**
     * initializes and stores objects to be launched when triggered.
     *
     * @param barrel is the barrel object to be launched.
     * @param hero is the hero that will be interacting with the listener.
     * @param fire is the fire object to be launched.
     */
    public ObjectLauncher(Barrel barrel, Hero hero, Fire fire) {

        this.barrel = barrel;
        this.hero = hero;
        this.fire = fire;
    }

    /**
     * initializes and stores objects to be launched when triggered.
     *
     * @param hero is the hero that will be interacting with the listener.
     * @param bullet is the bullet that will be launched.
     */
    public ObjectLauncher(Hero hero, BulletBill bullet) {

        this.hero = hero;
        this.bullet = bullet;

    }

    /**
     * Override, launches a fire or barrel object(at random) or launches bullets
     * depending on the level.
     *
     * @param e is the sensor event.
     */
    @Override
    public void beginContact(SensorEvent e) {
        //if the player contacts the sensor then if rand int is 1 or less a barrel will get laucnhed but if rand int above 1 then fire will be lauched 
        if (fire != null && barrel != null) {

            int rand = (int) (Math.random() * 2 + 1);
            if (e.getContactBody() == hero) {
                if (rand <= 1) {
                    barrel.bouncyBarrel();
                } else if (rand > 1) {

                    fire.lauchFire();
                }

            }
        } else {

            int rand = (int) (Math.random() * 2 + 1);
            if (e.getContactBody() == hero) {
                if (rand <= 1) {
                    bullet.launchLeft(8, e.getSensor().getBody().getPosition().y);
                } else if (rand > 1) {
                    bullet.launchRight(-8, e.getSensor().getBody().getPosition().y);
                }

            }

        }

    }

    /**
     * Override, destroys this instance after lives(sensorLife) have been
     * reduced to zero .
     *
     * @param e is the sensor event.
     */
    @Override
    public void endContact(SensorEvent e) {
        // if hero ends contact with sensor , sensor loses a life 

        if (e.getContactBody() == hero) {
            sensorLife--;
            // if sensors life equals 0 then desroy sensor to prevent to much obsticals being lauched 
        }
        if (sensorLife == 0) {
            e.getSensor().getBody().destroy();

        }

        if (sensorLife == 0) {
            System.out.println(" Sensor destroyed");
        }

    }

}
