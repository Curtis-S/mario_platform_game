/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.levels;

import GameP.GameMain;
import GameP.gameListeners.LifeLost;
import GameP.characters.Boss;
import GameP.characters.Hero;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * The abstract game level Class that provides the basses for all levels to be built.
 */
public abstract class GameLevelR extends World {
    private Hero hero;
    private Boss boss;
    
    public Hero getHero(){
    return hero;
    }

    public Boss getBoss() {
        return boss;
    }

    
    /**
     * Populate the world of this level Child classes should use this method with additional bodies. .
     * @param game is the game that will be populated.
     */
    public void populate(GameMain game) {
        hero = new Hero(this);
        hero.setPosition(startPosition());
        boss = new Boss(this,game,hero);
        boss.setPosition(bossPosition());
        boss.addCollisionListener(new LifeLost());
       
    }
    
 /** The initial position of the hero. */ 
    public abstract Vec2 startPosition();
    
 /** The position of the boss. */
    public abstract Vec2 bossPosition();
    
}

