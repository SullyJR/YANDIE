package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
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
   * A rectangle data field to store the location of rectangle
   * </p>
   */
  private Rectangle area;

  /**
   * <p>
   * An Ellipse2D.Double data field to store the location of Circle
   * 
   */
  private Ellipse2D circle;

  /**
   * <p>
   * A path drawn by the user called back from ImagePanels Mouselisteners
   * </p>
   */
  private GeneralPath drawPath;

  /**
   * <p>
   * Construct Crop constructor using the given ImagePanel
   * </p>
   * 
   * @param imagePanel and Imagepanel to call selection methods
   */
  Crop(ImagePanel imagePanel) {
    this.imagePanel = imagePanel;
    this.area = null;
    this.circle = null;
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
   * > Rectangle
   * > Circle
   * > Free-Hand
   * </p>
   * 
   * @param input Input image to be cropped
   * @return input If not of the conditions applied return original image (Should not happen)
   * @return output The cropped image based on user selection
   */
  public BufferedImage apply(BufferedImage input) {
    if (imagePanel.rectToggled()) {
      if (area == null) {
        area = imagePanel.getSelection();
      }
      // Create a BufferedImage with response to the selected Rectangle
      BufferedImage output = input.getSubimage(area.x, area.y, area.width, area.height);
      return output;
    } else if (imagePanel.cirToggled()) {
      if (circle == null) {
        circle = imagePanel.getCircle();
      }
      // Create a BufferedImage with transparency for the output
      BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);

      // Create a Graphics2D object to draw on the output image
      Graphics2D g = output.createGraphics();

      // Set the rendering hint for better quality (optional)
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Create a new Shape with the ellipse/circle
      Area shapeArea = new Area(circle);

      // Set the clipping region to the shape area
      g.setClip(shapeArea);

      // Draw the input image onto the output image
      g.drawImage(input, 0, 0, null);

      // Dispose the graphics object
      g.dispose();
      return output;
    } else if (imagePanel.drawToggled()) {
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

    // Return the original image if no crop is applied
    return input;
  }
}
