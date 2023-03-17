package cosc202.andie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import cosc202.andie.EditActions.UndoAction;

public class KeyPress implements KeyListener {
    private boolean controlPressed = false;
    ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(60, 0));
    

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_A & e.getKeyCode() == KeyEvent.VK_L & e.getKeyCode() == KeyEvent.VK_E & e.getKeyCode() == KeyEvent.VK_X) {
            list.set(1,1);
        }
        
            
            
            
             
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                controlPressed = false;
            }
        }
    
        @Override
        public void keyTyped(KeyEvent e) {
            // Not used
        }
    
    }

   
