package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a Oval/Circle based on User's selection
 * </p>
 * 
 * <p>
 * The selected Circle is drawn over the original buffered image
 * Then returns the result of those combined
 * </p>
 * 
 */
public class FillCir implements ImageOperation, java.io.Serializable {

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
   * An Ellipse2D.Double data field to store the location of Circle
   * 
   */
  private Ellipse2D circle;

  /**
   * <p>
   * Construct a FillCir constructor using the given ImagePanel and also choosen
   * Color
   * </p>
   * 
   * @param imagePanel   the image panel
   * @param choosedColor the chosen colour from the user
   */
  FillCir(ImagePanel imagePanel, Color choosedColor) {
    this.imagePanel = imagePanel;
    this.choosedColor = choosedColor;
    this.circle = null;
  }

  /**
   * 
   * @param input the image
   * @return outputs the image with filters
   */
  public BufferedImage apply(BufferedImage input) {
    BufferedImage output = input;
    if (circle == null) {
      circle = imagePanel.getCircle();
    }

    Graphics2D g = output.createGraphics();
    g.drawImage(input, 0, 0, null);
    g.setColor(choosedColor);
    g.fill(circle);
    g.dispose();
    return output;
  }

}
