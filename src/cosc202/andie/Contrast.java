package cosc202.andie;

import java.awt.image.BufferedImage;

/**
* Construct a Contrast Class which implements ImageOperation and creates and 
* applies the contrast filter
*/
public class Contrast implements ImageOperation {
  
  /** Amount to change the contrast by */
  private double contrast;

  /**
   * Construct a Contrast Filter with the given contrast adjustment.
   * 
   * @param contrast The amount to change the contrast by.
   */
  public Contrast(double contrast) {
    this.contrast = contrast;
  }

  /**
   * Apply the contrast filter to the given input image.
   * 
   * @param input The input image to apply the filter to.
   * @return The output image after the filter has been applied.
   */
  public BufferedImage apply(BufferedImage input) {
    BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
   
    // Find the average luminance of the input image
    double luminanceSum = 0;
    for (int x = 0; x < input.getWidth(); x++) {
      for (int y = 0; y < input.getHeight(); y++) {
        int pixel = input.getRGB(x, y);
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        double luminance = 0.299 * red + 0.587 * green + 0.114 * blue;
        luminanceSum += luminance;
      }
    }
    double averageLuminance = luminanceSum / (input.getWidth() * input.getHeight());

    // Change the contrast of the pixel by the specified amount
    double factor = (259 * (contrast + 255)) / (255 * (259 - contrast));
    for (int x = 0; x < input.getWidth(); x++) {
      for (int y = 0; y < input.getHeight(); y++) {
        int pixel = input.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        double luminance = 0.299 * red + 0.587 * green + 0.114 * blue;
        double newLuminance = factor * (luminance - averageLuminance) + averageLuminance;

        red = (int) (newLuminance + (red - luminance));
        green = (int) (newLuminance + (green - luminance));
        blue = (int) (newLuminance + (blue - luminance));

        // Makes sure that the pixel values are within the valid range of 0-255
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
