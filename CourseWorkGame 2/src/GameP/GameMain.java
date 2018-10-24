/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP;

import GameP.HighScoresClasses.HighScoreReader;
import GameP.HighScoresClasses.HighScoreWriter;
import GameP.levels.Progress;
import GameP.levels.ScoreView;
import GameP.levels.Level3;
import GameP.levels.Level2;
import GameP.levels.Level1;
import GameP.levels.GameLevelR;
import GameP.levels.ControlPanel;
import GameP.gameListeners.Controller2;
import GameP.gameListeners.Controller;
import GameP.characters.Hero;
import GameP.fsm.FSM;
import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * This is the Main Game Class with the main method
 *
 */
public class GameMain {

    private GameLevelR world;
    // private MenuState world2 = new MenuState();
    private UserView view;
    private Controller controller;
    private Controller2 controller2;
    final JFrame gameFrame = new JFrame("Heros Challenge");
    final JFrame menuFrame = new JFrame("Heros Challenge");
    final JFrame gameOverFrame = new JFrame("Heros Challenge HighScores");
    private int level;
    private Progress progress;
    private int prog = 1;
    private FSM<GameMain> fsm;
    private String playerName;
    private int playerScore;
    private int time = 1;
    private LinkedHashMap highScores;
    private ArrayList topPlayers;
    private SoundClip gameMusic, bossMusic, gameOverMusic, pause;
    private boolean gameFinished = false;

    // constructor 
    /**
     * Initializes a new GameMain , new sounds and also a new finite state
     * machine.
     *
     *
     */
    public GameMain() {
//Start game in state  
        try {
            pause = new SoundClip("data/pause.wav");   // Open an audio input stream
            pause.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        try {
            gameMusic = new SoundClip("data/game.mp3");   // Open an audio input stream
            gameMusic.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        try {
            bossMusic = new SoundClip("data/boss.mp3");   // Open an audio input stream

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        try {
            gameOverMusic = new SoundClip("data/gameover.mp3");   // Open an audio input stream

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        fsm = new FSM<GameMain>(this, new MenuState());

    }

    /**
     * Loads the next level when the current level has been completed and if the
     * last level is completed, the player name and score gets saved to a .txt
     * file.
     */
    public void goNextLevel() {
        prog = prog + 33;
        progress.updateBar(prog);
        level++;
        playerScore = playerScore + (1000 - getTim());
        world.stop();
        switch (level) {
            case 4:
                HighScoreWriter hr = new HighScoreWriter("HighScore.txt");
                 {
                    try {
                        hr.writeHighScore(playerName, playerScore);
                        System.out.println("data saved");
                    } catch (IOException ex) {
                        Logger.getLogger(GameMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gameFinished = true;
                changeState();
                break;
            case 2:
                // get a new world
                world = new Level2();
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player

                gameFrame.addKeyListener(controller2);
                controller2.setBody(world.getHero());
                // show the new world in the view
                view.setWorld(world);
                world.start();
                break;
            case 3:
                // get a new world
                world = new Level3();
                // fill it with bodies
                world.populate(this);
                gameMusic.stop();
                bossMusic.setVolume(0.1);
                bossMusic.play();
                // switch the keyboard control to the new player
                gameFrame.addKeyListener(controller);
                controller.setLevel1(false);
                controller.setBody(world.getHero());
                // show the new world in the view
                view.setWorld(world);
                world.start();
                break;
            default:
                break;
        }
    }
//methods for cheat level select 

    /**
     * Loads a specific level Chosen by the cheat menu drop down buttons.
     */
    public void chooseLevel1() {
        prog = 0;
        progress.updateBar(prog);
        level = 1;
        getHero().resetLives();
        world.stop();
        bossMusic.stop();
        gameMusic.play();
        // get a new world
        world = new Level1();
        // fill it with bodies
        world.populate(this);
        // switch the keyboard control to the new player
        gameFrame.addKeyListener(controller);
        controller.setLevel1(true);
        controller.setBody(world.getHero());
        // show the new world in the view
        view.setWorld(world);
        start();
    }

    /**
     * Loads a specific level Chosen by the cheat menu drop down buttons.
     */
    public void chooseLevel2() {
        prog = 33;
        progress.updateBar(prog);
        level = 2;
        getHero().resetLives();
        world.stop();
        bossMusic.stop();
        gameMusic.play();
        // get a new world
        world = new Level2();
        // fill it with bodies
        world.populate(this);
        // switch the keyboard control to the new player

        gameFrame.addKeyListener(controller2);
        controller2.setBody(world.getHero());
        // show the new world in the view
        view.setWorld(world);
        start();

    }

    /**
     * Loads a specific level Chosen by the cheat menu drop down buttons.
     */
    public void chooseLevel3() {
        prog = 67;
        progress.updateBar(prog);
        level = 3;
        getHero().resetLives();
        world.stop();
        gameMusic.stop();
        bossMusic.setVolume(0.1);
        bossMusic.play();

        // get a new world
        world = new Level3();
        // fill it with bodies
        world.populate(this);
        // switch the keyboard control to the new player
        gameFrame.addKeyListener(controller);
        controller.setLevel1(false);
        controller.setBody(world.getHero());
        // show the new world in the view
        view.setWorld(world);
        start();

    }
//setters and getters

    /**
     * the heros lives remaining.
     *
     * @return the heros current lives.
     */
    public int returnLives() {
        return world.getHero().getLives();
    }

    /**
     * The player in the current level.
     *
     * @return the player in the current world.
     */
    public Hero getHero() {
        return world.getHero();
    }

    /**
     * the game music of the game.
     *
     * @return the game music.
     */
    public SoundClip getGameMusic() {
        return gameMusic;
    }

    /**
     * the boss music of the game.
     *
     * @return the boss music.
     */
    public SoundClip getBossMusic() {
        return bossMusic;
    }

    /**
     * the game over music of the game.
     *
     * @return the game over music.
     */
    public SoundClip getGameOverMusic() {
        return gameOverMusic;
    }

    /**
     * the current level of the game.
     *
     * @return the level number (1-3).
     */
    public int getLevel() {
        return level;
    }

    /**
     * the player name of the game.
     *
     * @return the Player (user) name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * assigns a user name to player name.
     *
     * @param playerName is the name that will be set (String).
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * The players score.
     *
     * @return the players current score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * timer digits of the game.
     *
     * @return the int that represents the timer.
     */
    public int getTim() {
        return time;
    }

    /**
     * the hash map that stores the high scores.
     *
     * @return the game music.
     */
    public LinkedHashMap getHighScores() {
        return highScores;
    }

    /**
     * The array list that stores the scores of the players.
     *
     * @return the players names .
     */
    public ArrayList getTopPlayers() {
        return topPlayers;
    }

    /**
     * check if came has been completed.
     *
     * @return true or false .
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * increments the time variable by one.
     */
    public void timeUp() {
        this.time++;
    }

    /**
     * Pauses the current game.
     */
    public void pause() {
        if (level == 3) {
            bossMusic.pause();
            pause.play();
        } else {
            gameMusic.pause();
            pause.play();
        }

        world.stop();
    }

    /**
     * Resumes the current game after it has been paused.
     */
    public void start() {
        gameFrame.requestFocus();
        world.start();
        if (level == 3) {
            bossMusic.resume();

        } else {
            gameMusic.resume();

        }
    }

    /**
     * Calls for the FSM to update.
     */
    public void changeState() {
        fsm.update();
    }

    /**
     * Creates the first level and then starts it.
     */
    public void gameState() {

        world = new Level1();
        world.populate(this);
        level = 1;
        progress = new Progress();
        //make view

        view = new ScoreView(world, this, 500, 650);
        playerScore = 0;
        if ("".equals(playerName)) {
            playerName = "Player 1";
        }

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setLocationByPlatform(true);
        // display the world in the window
        gameFrame.add(view);
        Container buttons = new ControlPanel(this);
        gameFrame.add(buttons, BorderLayout.WEST);
        gameFrame.add(progress, BorderLayout.NORTH);
        // don't let the game window be resized
        gameFrame.setResizable(false);
        // size the game window to fit the world view
        gameFrame.pack();
        // make the window visible

        gameFrame.setVisible(true);

        gameFrame.requestFocus();
        gameMusic.loop();  // Set it to continous playback (looping)
        // key listener attached to Hero to enable movement 
        controller = new Controller(world.getHero());
        controller2 = new Controller2(world.getHero());
        gameFrame.addKeyListener(controller);

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);
        // start world
        world.start();
    }

    /**
     * Creates the game menu that the game starts on.
     */
    public void menuState() {

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container buttons2 = new MenuButtons(this);

        menuFrame.add(buttons2);

        // don't let the game window be resized
        menuFrame.setResizable(false);

        menuFrame.pack();
        // make the window visible

        menuFrame.setVisible(true);

        menuFrame.requestFocus();

    }

    /**
     * Loads the all Players scores from a file and adds HighScores class form
     * to a frame.
     */
    public void gameOverState() {
//create data structures and load highscores
        highScores = new LinkedHashMap();
        topPlayers = new ArrayList();
        HighScoreReader hr = new HighScoreReader("HighScore.txt", this);
        try {
            hr.readScores();
        } catch (IOException ex) {
            Logger.getLogger(GameMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//play music
        bossMusic.stop();
        gameOverMusic.setVolume(0.1);
        gameOverMusic.play();

        Container buttons2 = new HighScores(this);

        gameOverFrame.add(buttons2);

        // don't let the game window be resized
        gameOverFrame.setResizable(false);

        gameOverFrame.pack();
        // make the window visible

        gameOverFrame.setVisible(true);

        gameOverFrame.requestFocus();

    }

    public static void main(String[] args) {
        new GameMain();

    }

}
