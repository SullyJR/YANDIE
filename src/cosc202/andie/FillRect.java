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
public class FillRect implements ImageOperation, java.io.Serializable {

  /**
   * <p>
   * An ImagePanel to call back the original ImagePanel from andie
   * to apply selections methods
   * </p>
   */
  private ImagePanel imagePanel;

  /**
   * <p>
   * A color data field to store the user's picked color
   * </p>
   */
  private Color choosedColor;

  /**
   * <p>
   * A rectangle data field to store the location of rectangle
   * </p>
   */
  private Rectangle area;

  /**
   * <p>
   * Construct a FillRect constructor using the given ImagePanel and also choosen Color
   * </p>
   * 
   * @param imagePanel An Imagepanel to call selection methods
   * @param choosedColor Color to determine color
   */
  FillRect(ImagePanel imagePanel, Color choosedColor) {
    this.imagePanel = imagePanel;
    this.choosedColor = choosedColor;
    this.area = null;
  }

  /**
   * <p>
   * Applies the Fill Rectangle feature to the input image and returns
   * the original image alongside the rectangle on the image
   * </p>
   * 
   * @param input Input Image to Apply FillRect
   * @return output The Edited image
   */
  public BufferedImage apply(BufferedImage input) {
    BufferedImage output = input;
    if (area == null) {
      area = imagePanel.getSelection();
    }
      
    Graphics2D g = output.createGraphics();
    g.drawImage(input, 0, 0, null);

    g.setColor(choosedColor);
    g.fillRect(area.x, area.y, area.width, area.height);
    g.dispose();
    return output;
  }
}
