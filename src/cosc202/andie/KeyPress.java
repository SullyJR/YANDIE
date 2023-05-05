package cosc202.andie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * The KeyPress class implements KeyListener and lets users use keyboard
 * shortcuts to start functions automatically to the image
 */
public class KeyPress implements KeyListener{

     ImagePanel ip = new ImagePanel();

    ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(60, 0));

    boolean boolA = false;
    boolean boolL = false;
    boolean boolE = false;
    boolean boolX = false;

    @Override
    public void keyPressed(KeyEvent e) {

      
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("testing");
            boolA = true;
            boolL = false;
            boolE = false;
            boolX = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_L && boolA == true) {

            boolL = true;
            boolA = false;
            boolE = false;
            boolX = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E && boolL == true) {

            boolE = true;
            boolA = false;
            boolL = false;
            boolX = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_X && boolE == true) {

            boolX = true;
            boolA = false;
            boolL = false;
            boolE = false;

        }

        if (boolX == true) {
            JOptionPane.showMessageDialog(null, new JLabel("You have found Alex! :)"), "Congratulations!",
                    JOptionPane.QUESTION_MESSAGE, ip.iconArray[19]);
            boolX = false;
            // System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

}
