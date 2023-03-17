package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;

/**
 * Image Operation to resize an image accordingly
 * 
 * 
 */

public class Resize implements ImageOperation, java.io.Serializable {

    /** Percentage field to apply */
    private double percentage;

    /**
     * Construct Resize constructor with the given percentage
     */
    Resize(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Construct Resize constructor with the default size which is 1
     * 
     */
    Resize() {
        this(1.0);
    }

    /** Applying the resize method to picture */
    public BufferedImage apply(BufferedImage input) {

        int resizeH = (int) (percentage * input.getHeight());
        int resizeW = (int) (percentage * input.getWidth());

        // Create a new BufferedImage with the new dimensions
        BufferedImage output = new BufferedImage(resizeW, resizeH, input.getType());

        // Scale the input image to the new dimensions
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, resizeW, resizeH, null);
        g2d.dispose();

        return output;
    }

}
