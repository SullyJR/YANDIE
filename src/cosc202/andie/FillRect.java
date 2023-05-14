package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle based on User's selection.
 * </p>
 * 
 * <p>
 * The selected rectangle is drawn over the original buffered image
 * Then returns the result of those combined
 * </p>
 * 
 */
public class FillRect implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * An ImagePanel to call back the original ImagePanel from andie
     * to apply selections methods
     * </p>
     */
    private ImagePanel imagePanel;

    /**
     * <p>
     * Construct a FillRect constructor using the given ImagePanel
     * </p>
     * 
     * @param imagePanel and Imagepanel to call selection methods
     */
    FillRect(ImagePanel imagePanel) {
      this.imagePanel = imagePanel;
    }

    /**
     * <p>
     * Applies the Fill Rectangle feature to the input image and returns
     * the original image alongside the rectangle on the image
     * </p>
     * @param input Input Image to Apply FillRect
     * @return output The Edited image
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Rectangle area = imagePanel.getSelection();
        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);

        g.setColor(Color.RED);
        g.fillRect(area.x, area.y, area.width, area.height);
        g.dispose();
        return output;
    }
}
