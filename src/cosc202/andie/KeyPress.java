package cosc202.andie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import cosc202.andie.EditActions.UndoAction;

public class KeyPress implements KeyListener {
    private boolean controlPressed = false;
    ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(60, 0));
    int a = 0;
    int l = 0;
    int E = 0;
    int x = 0;

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_A){
            if(a==0){
                a=1;
            }
        } 
        if (e.getKeyCode() == KeyEvent.VK_L){
            if(l==0){
                l=1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_E){
            if(E==0){
                E=1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_X){
            if(x==0){
                x=1;
            }
        }
        if (e.getKeyCode() != KeyEvent.VK_X & e.getKeyCode() != KeyEvent.VK_E & e.getKeyCode() != KeyEvent.VK_L & e.getKeyCode() != KeyEvent.VK_A){
           a=0;
           l=0;
           E=0;
           x=0;
        }
        if ((a+l+E+x) == 4){
            System.exit(1);
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

   
