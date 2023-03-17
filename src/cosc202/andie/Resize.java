package cosc202.andie;

import java.awt.image.*;

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
     * Construct Resize constructor with the default size which is 1.0
     * 
     */
    Resize() {
        this(1.0);
    }

    /** applying the resize method to picture */
    public BufferedImage apply(BufferedImage input) {
        
        int resizeH = (int) percentage * input.getHeight();
        int resizeW = (int) percentage * input.getWidth();
        return (BufferedImage) input.getScaledInstance(resizeW, resizeH, input.SCALE_DEFAULT);
    }

}
