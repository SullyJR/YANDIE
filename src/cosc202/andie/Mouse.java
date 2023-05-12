package cosc202.andie;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Construct a Mouse Class which implements a MouseListener,
 * and identifies and keeps track of the cursors location and its state.
 */
public class Mouse extends JPanel implements MouseListener {

    private Point anchor; // the location of the point
    private Rectangle selection; // the rectange creates from the anchor points

    /**
     * When the mouse is pressed, it draws the rectangle for selection actions
     * 
     * @param e mouse events
     */
    public void mousePressed(MouseEvent e) {
        anchor = e.getPoint();
        selection = null;
        repaint();
    }

    /**
     * When the mouse is released, it finished drawing the rectangle for selection
     * actions
     * 
     * @param e mouse events
     */
    public void mouseReleased(MouseEvent e) {
        if (selection != null && selection.width > 0 && selection.height > 0) {
            // Do something with the selected region
            System.out.println("Selected region: " + selection);
        } else {
            // Cancel the selection
            selection = null;
        }
    }

    /**
     * When the mouse is dragges, the rectangle selection is updated and
     * continuously altered
     * till it is released.
     * 
     * @param e mouse events
     */
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(anchor.x, e.getX());
        int y = Math.min(anchor.y, e.getY());
        int width = Math.abs(e.getX() - anchor.x);
        int height = Math.abs(e.getY() - anchor.y);
        selection = new Rectangle(x, y, width, height);
        repaint();
    }

    /**
     * Terminal print command for entered
     * 
     * @param e mouse events
     */
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered" + e);
    }

    /**
     * Terminal print command for exited
     * 
     * @param e mouse events
     */
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited" + e);
    }

    /**
     * Terminal print command for clicked
     * 
     * @param e mouse events
     */
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")" + e);
    }
}
