/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.levels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * A progress bar.
 */
// for game complete status
public class Progress extends JPanel {

    JProgressBar pbar;
    JLabel lab;

    /**
     * initializes a new progress class and creates a progress bar and sets the
     * minimum and maximum values for it.
     */
    public Progress() {
        lab = new JLabel();
        lab.setText("GAME progress");
        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        // add to JPanel
        add(pbar);
        add(lab);
    }

    /**
     * updates progress bar to the new value that is set (0-100).
     *
     * @param newValue is the new value of the progress bar (1-100).
     */
    public void updateBar(int newValue) {
        pbar.setValue(newValue);
        lab.setText("Game Progress: " + newValue + "%");
    }

}
