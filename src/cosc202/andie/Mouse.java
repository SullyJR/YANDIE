package cosc202.andie;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.event.MouseListener;
 import java.awt.event.MouseEvent;
 
 import javax.swing.*;
 
public class Mouse extends JPanel implements MouseListener{

    private Point anchor;
    private Rectangle selection;
    
    public void mousePressed(MouseEvent e) {
        anchor = e.getPoint();
        selection = null;
        repaint();
    }
    
    public void mouseReleased(MouseEvent e) {
        if (selection != null && selection.width > 0 && selection.height > 0) {
            // Do something with the selected region
            System.out.println("Selected region: " + selection);
          } else {
            // Cancel the selection
            selection = null;
          }
    }
    
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(anchor.x, e.getX());
        int y = Math.min(anchor.y, e.getY());
        int width = Math.abs(e.getX() - anchor.x);
        int height = Math.abs(e.getY() - anchor.y);
        selection = new Rectangle(x, y, width, height);
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
