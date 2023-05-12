package cosc202.andie;

import java.awt.image.*;
import java.awt.Rectangle;

/**
 * <p>
 * ImageOperation to crop a rectangular area from an image.
 * </p>
 * 
 * <p>
 * The Crop class allows users to select a rectangular area of an image
 * and returns the cropped version of that area.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.BufferedImage#getSubimage(int, int, int, int)
 * @author Steven Mills
 * @version 1.0
 */
public class Crop implements ImageOperation, java.io.Serializable {
    private Rectangle area; // rectangle selected for cropping
    private ImagePanel panel; // the image panel

    /**
     * The rectangular area to crop from the image.
     * 
     * 
     * 
     * 
     * 
     * /**
     * <p>
     * Construct a Crop operation with the given ImagePanel.
     * </p>
     * 
     * <p>
     * The Crop operation allows users to select a rectangular area of an image
     * and returns the cropped version of that area.
     * </p>
     * 
     * @param panel The ImagePanel containing the image to crop.
     */
    Crop(ImagePanel panel) {
        this.panel = panel;
    }

    /**
     * <p>
     * Construct a Crop operation with default settings.
     * </p>
     * 
     * <p>
     * By default, the Crop operation does not crop any area.
     * </p>
     */
    Crop() {

    }

    /**
     * <p>
     * Apply the Crop operation to an image.
     * </p>
     * 
     * <p>
     * The Crop operation allows users to select a rectangular area of an image
     * and returns the cropped version of that area.
     * </p>
     * 
     * @param input The image to apply the Crop operation to.
     * @return The cropped version of the image.
     */
    public BufferedImage apply(BufferedImage input) {

        // Get the rectangular area to crop from the ImagePanel
        area = panel.getSelection();

        // If an area was selected, crop that area from the image and return it
        if (area != null) {
            BufferedImage newImg = input.getSubimage(area.x, area.y, area.width, area.height);
            return newImg;
        }
        // Otherwise, return the original image
        else {
            return input;
        }

    }

    /**
     * Applies the kernel to the given BufferedImage image version 2.
     *
     * @param image the original image
     * @return result
     */
    public static BufferedImage applyKernelV2(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        return result;
    }

}
