package cosc202.andie;

import java.awt.GridLayout;
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.event.MouseListener;
 import java.awt.event.MouseEvent;
 
 import javax.swing.*;
 
public class Mouse extends JPanel implements MouseListener{


    
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed (# of clicks: "
                + e.getClickCount() + ")"+ e);
    }
    
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released (# of clicks: "
                + e.getClickCount() + ")"+ e);
    }
    
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered"+ e);
    }
    
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited"+ e);
    }
    
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")"+ e);
    }
}
