package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to crop the image based on User's selection.
 * </p>
 * 
 * <p>
 * The original image is cropped according to the user's selection.
 * > Circle
 * </p>
 * 
 */
public class Lasso implements ImageOperation, java.io.Serializable {

  /**
   * <p>
   * An ImagePanel to call back the original ImagePanel from andie
   * to apply selections methods
   * </p>
   */
  private ImagePanel imagePanel;

  /**
   * <p>
   * A path drawn by the user called back from ImagePanels Mouselisteners
   * </p>
   */
  private GeneralPath drawPath;

  /**
   * <p>
   * Construct lasso constructor using the given ImagePanel
   * </p>
   * 
   * @param imagePanel and Imagepanel to call selection methods
   */
  Lasso(ImagePanel imagePanel) {
    this.imagePanel = imagePanel;
    this.drawPath = null;
  }

  /**
   * <p>
   * Applies the crop feature to input image
   * and returns the cropped image
   * </p>
   * 
   * <p>
   * Selection will based on user selection
   * > Circle
   * </p>
   * 
   * @param input Input image to be cropped
   * @return output The cropped image based on user selection
   */
  public BufferedImage apply(BufferedImage input) {
    if (drawPath == null) {
      drawPath = imagePanel.drawingPath();
    }
    // Create a BufferedImage with transparency for the output
    BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);

    // Create a Graphics2D object to draw on the output image
    Graphics2D g = output.createGraphics();

    // Set the rendering hint for better quality (optional)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Create a new Shape with the drawpath
    Area shapeArea = new Area(drawPath);

    // Set the clipping region to the shape area
    g.setClip(shapeArea);

    // Draw the input image onto the output image
    g.drawImage(input, 0, 0, null);

    // Dispose the graphics object
    g.dispose();

    return output;
  }
}
