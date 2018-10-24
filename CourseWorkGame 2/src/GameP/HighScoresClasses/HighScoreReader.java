/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.HighScoresClasses;

/**
 *
 * @author curtisscott
 */
import GameP.GameMain;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * High score reader class.
 */
public class HighScoreReader {

    private String fileName;
    private GameMain game;

    /**
     * Initialise a new HighScoreReader
     *
     * @param fileName the name of the high-score file
     * @param game the name of the GameMain being played
     */
    public HighScoreReader(String fileName, GameMain game) {
        this.fileName = fileName;
        this.game = game;

    }

    /**
     * Read the high-score data from the high-score file and print it to the
     * terminal window.
     */
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                Integer score = Integer.parseInt(tokens[1]);
                game.getHighScores().put(name, score);
                game.getTopPlayers().add(name);
                System.out.println("Name: " + name + ", Score: " + game.getHighScores().get(name));
                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
