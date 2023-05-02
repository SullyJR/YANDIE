package cosc202.andie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageSelectionRectangle implements ImageOperation, java.io.Serializable {

    /* private datafields, not sure what yet */
    BufferedImage image;
    private Rectangle selection;
    private Point anchor;
    private Rectangle highlightedSelection;
     
    ImageSelectionRectangle() {
        this.selection = null;
        this.anchor = null;
    }

    public BufferedImage apply(BufferedImage input) {
        image = input;
        if (highlightedSelection != null) { // Use the highlighted selection if available
            int x = highlightedSelection.x;
            int y = highlightedSelection.y;
            int width = highlightedSelection.width;
            int height = highlightedSelection.height;
            return input.getSubimage(x, y, width, height);
        } else if (selection != null) { // Use the current selection if available
            int x = selection.x;
            int y = selection.y;
            int width = selection.width;
            int height = selection.height;
            return input.getSubimage(x, y, width, height);
        } else {
            return image;
        }
    }


    // Add a nested class that extends MouseAdapter
    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            anchor = e.getPoint();
            selection = new Rectangle(anchor);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int width = e.getX() - anchor.x;
            int height = e.getY() - anchor.y;
            selection.setSize(width, height);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            highlightedSelection = selection; // Store the selected area in the new field
            selection = null; // Reset the selection field
            repaint(); // Repaint the panel to highlight the selected area
        }

        private BufferedImage repaint() {
            // Make a copy of the original image
            BufferedImage highlightedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(), image.getType());
            Graphics g = highlightedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
        
            // Draw a translucent rectangle over the selected area
            if (highlightedSelection != null) {
                g = highlightedImage.createGraphics();
                g.setColor(new Color(255, 255, 255, 128)); // Translucent white
                g.fillRect(
                        highlightedSelection.x,
                        highlightedSelection.y,
                        highlightedSelection.width,
                        highlightedSelection.height);
                g.dispose();
            }
        
            return highlightedImage;
        }
        

    }

}    
