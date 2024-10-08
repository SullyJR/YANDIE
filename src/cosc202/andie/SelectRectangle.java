package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.*;

/**
 * A SelectRectangle Class which implements ImageOperation and
 * java.io.Serializable,
 * and creates and holds values for the rectangle selection feature
 */
public class SelectRectangle implements ImageOperation, java.io.Serializable {

  /** Private data fields */

  /** The selected area of rectangle */
  private Rectangle selectedArea;
  /** The initial brightness */
  private int brightness = -50;
  /** The image panel */
  private ImagePanel imagePanel;

  /**
   * Default constructor for SelectRectangle
   */

  SelectRectangle() {
  }

  /** Removes the variables for the selected rectangle */
  public void killrectangle() {
    selectedArea = null;
    selectedArea.y = 0;
    selectedArea.x = 0;
    selectedArea.width = 0;
    selectedArea.height = 0;
  }

  /**
   * Replacement for default constructor
   * Wow
   */
  SelectRectangle(ImagePanel imagePanel) {
    this.imagePanel = imagePanel;

  }

  public BufferedImage apply(BufferedImage input) {

    BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(),
        null);

    // Changing initial brightness of image
    for (int x = 0; x < output.getWidth(); x++) {
      for (int y = 0; y < output.getHeight(); y++) {
        int pixel = output.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        // Change the brightness of the pixel by the specified amount
        red += brightness;
        green += brightness;
        blue += brightness;

        // Ensure that the pixel values are within the valid range of 0-255
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        // Combine the new RGB values into a single pixel and set it on the output image
        int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
        output.setRGB(x, y, newPixel);
      }
    }

    selectedArea = imagePanel.getSelection();

    // Increase the brightness of the selected area
    for (int y = selectedArea.y; y < selectedArea.y + selectedArea.height; y++) {
      for (int x = selectedArea.x; x < selectedArea.x + selectedArea.width; x++) {
        int rgb = output.getRGB(x, y);
        int alpha = (rgb >> 24) & 0xff;
        int red = (rgb >> 16) & 0xff;
        int green = (rgb >> 8) & 0xff;
        int blue = rgb & 0xff;
        red = Math.min(255, red - brightness);
        green = Math.min(255, green - brightness);
        blue = Math.min(255, blue - brightness);
        output.setRGB(x, y, (alpha << 24) | (red << 16) | (green << 8) | blue);
      }
    }
    return output;
  }
}
