package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to crop the image based on User's selection.
 * </p>
 * 
 * <p>
 * The original image is cropped according to the user's selection.
 * > Rectangle
 * <p>
 * 
 */
public class CropRect implements ImageOperation, java.io.Serializable {

  /**
   * <p>
   * An ImagePanel to call back the original ImagePanel from andie
   * to apply selections methods
   * </p>
   */
  private ImagePanel imagePanel;

  /**
   * <p>
   * A rectangle data field to store the location of rectangle
   * </p>
   */
  private Rectangle area;

  /**
   * <p>
   * Construct CropRect constructor using the given ImagePanel
   * </p>
   * 
   * @param imagePanel and imagePanel to call selection methods
   */
  CropRect(ImagePanel imagePanel) {
    this.imagePanel = imagePanel;
    this.area = null;
  }

  /**
   * <p>
   * Applies the crop feature to input image
   * and returns the cropped image
   * </p>
   * 
   * <p>
   * Selection will based on user selection
   * > Rectangle
   * </p>
   * 
   * @param input Input image to be cropped
   * @return output The cropped image based on user selection
   */
  public BufferedImage apply(BufferedImage input) {
    if (area == null) {
      area = imagePanel.getSelection();
    }
    // Create a BufferedImage with response to the selected Rectangle
    BufferedImage output = input.getSubimage(area.x, area.y, area.width, area.height);
    return output;

  }
}
