package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crop implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * ImagePanel to call the selection rectangle 
     * Solely for applying crop onto Image
     * </p>
     */
    private ImagePanel imagePanel;

    /**
     * <p>
     * Construct Crop constructor using the given ImagePanel
     * </p>
     * 
     * @param imagePanel an Imagepanel to call Selected Objects
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
      if(area == null) {
        System.out.println("No selected area");
        return input;
      } else {
        BufferedImage output = input.getSubimage(area.x, area.y, area.width, area.height);
        return output;
      }
    }
}
