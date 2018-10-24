/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.HighScoresClasses;


import java.io.FileWriter;
import java.io.IOException;

/**
 * High score writer class .
 */
public class HighScoreWriter {

    private String fileName;
   

    /**
     * initializes and creates high score reader .
     *
     * @param fileName is the name the save file will be called.
     */
    public HighScoreWriter(String fileName) {
        this.fileName = fileName;

    }

    /**
     * Writes the high score of the game to a text file .
     *
     * @param name is the name of the player.
     * @param score is the score of the player.
     * @throws java.io.IOException
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score + "\n");
            System.out.println("Highscoresaved");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
