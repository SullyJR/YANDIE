package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a line based on User's selection.
 * </p>
 * 
 * <p>
 * The selected line is drawn over the original buffered image
 * Then returns the result of those combined
 * <p>
 * 
 */
public class DrawLine implements ImageOperation, java.io.Serializable{
  /**
   * <p>
   * An ImagePanel to call back the original ImagePanel from andie
   * to apply selection methods
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
   * A Line2D data field to store the location of the Line
   * </p> 
   */
  private Line2D selectLine;

  /**
   * <p>
   * Construct a DrawLine constructor using the given ImagePanel and the selected Color
   * </p>
   * 
   * @param imagePanel
   * @param choosedColor
   */
  DrawLine(ImagePanel imagePanel, Color choosedColor) {
    this.imagePanel = imagePanel;
    this.choosedColor = choosedColor;
    this.selectLine = null;
  }

  /**
   * <p>
   * Applies the Draw Line feature to the input and returns
   * the original image alongside the rectangle on the image
   * </p>
   * 
   * @param input Input Image to apply DrawLine
   * @return output The edited image
   */
  public BufferedImage apply(BufferedImage input) {
    BufferedImage output = input;
    if(selectLine == null) {
      selectLine = imagePanel.getLine();
    }
    Graphics2D g = output.createGraphics();
    g.drawImage(input, null, 0, 0);
    g.setColor(choosedColor);
    g.draw(selectLine);
    g.dispose();
    return output;
  }

}
