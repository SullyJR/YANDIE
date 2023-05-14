package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crop implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * An ImagePanel to call back the original ImagePanel from andie
     * to apply selections methods
     * </p>
     */
    private ImagePanel imagePanel;

    /**
     * <p>
     * Construct Crop constructor using the given ImagePanel
     * </p>
     * 
     * @param imagePanel and Imagepanel to call selection methods
     */
    Crop(ImagePanel imagePanel) {
      this.imagePanel = imagePanel;
    }

    /**
     * <p>
     * Applies the Crop feature to the input image and returns the cropped image
     * </p>
     * @param input Input Image to Crop.
     * @return output The Cropped image.
     */
    public BufferedImage apply(BufferedImage input) {
        Rectangle area = imagePanel.getSelection();
        BufferedImage output = input.getSubimage(area.x, area.y, area.width, area.height);
        return output;
    }
}
