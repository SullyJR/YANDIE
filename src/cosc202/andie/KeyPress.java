package cosc202.andie;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class KeyPress implements KeyListener {
    private MacroRecorder macro;
    private ImagePanel ip;

    ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(60, 0));

    boolean boolA = false;
    boolean boolL = false;
    boolean boolE = false;
    boolean boolX = false;

    public KeyPress() {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            boolA = true;
            boolL = false;
            boolE = false;
            boolX = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_L && boolA) {
            boolL = true;
            boolA = false;
            boolE = false;
            boolX = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E && boolL) {
            boolE = true;
            boolA = false;
            boolL = false;
            boolX = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_X && boolE) {
            boolX = true;
            boolA = false;
            boolL = false;
            boolE = false;
        }

        if (boolX) {
            boolX = false;
            JOptionPane.showMessageDialog(null, new JLabel("You have found Alex! :)"), "Congratulations!",
                    JOptionPane.QUESTION_MESSAGE);
            
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
