package cosc202.andie;
import java.awt.image.BufferedImage;


public class Brightness implements ImageOperation, java.io.Serializable {
  
  /** Amount to change the brightness by */
  private int brightness;

  /**
   * Construct a Brightness Filter with the given brightness adjustment.
   * 
   * @param brightness The amount to change the brightness by.
   */
  public Brightness(int brightness) {
    this.brightness = brightness;
  }

  /**
   * Apply the brightness filter to the given input image.
   * 
   * @param input The input image to apply the filter to.
   * @return The output image after the filter has been applied.
   */
  public BufferedImage apply(BufferedImage input) {
    BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
   
    for (int x = 0; x < input.getWidth(); x++) {
      for (int y = 0; y < input.getHeight(); y++) {
        int pixel = input.getRGB(x, y);
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

    return output;
  }
}