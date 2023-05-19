package cosc202.andie;

import java.awt.image.*;

/**
 * Construct a Emboss Class which implements ImageOperation and
 * java.io.Serializable,
 * and creates and applies the emboss filter
 */
public class Emboss implements ImageOperation, java.io.Serializable {

  /**
   * Constructs an Emboss filter.
   */
  Emboss() {
    // Any construction code goes here
  }

  /**
   * Applies an emboss filter to the given image.
   * 
   * @param input the image to apply the filter to
   * @return the blurred image
   */
  public BufferedImage apply(BufferedImage input) {

    //constructing kernel for horizontal sobel
    float[][] h = { { 1 / -2, 0, 1 / 2 },
        { -1, 0, 1 },
        { 1 / -2, 0, 1 / 2 } };

    //constructing kernel for vertical sobel
    float[][] v = { { 1 / -2, -1, -1 / 2 },
        { 0, 0, 0 },
        { 1 / 2, 1, 1 / 2 } };

    //various kernels for different emboss directions
    float[][] ar1 = { { 0, 0, 0 }, { 1, 0, -1 }, { 0, 0, 0 } };
    float[][] ar2 = { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, -1 } };
    float[][] ar3 = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, -1, 0 } };
    float[][] ar4 = { { 0, 0, 0 }, { -1, 0, 1 }, { 0, 0, 0 } };
    float[][] ar5 = { { 0, 0, 0 }, { -1, 0, 1 }, { 0, 0, 0 } };
    float[][] ar6 = { { -1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
    float[][] ar7 = { { 0, -1, 0 }, { 0, 0, 0 }, { 0, 1, 0 } };
    float[][] ar8 = { { 0, 0, -1 }, { 0, 0, 0 }, { 1, 0, 0 } };

    BufferedImage output = applyKernel(input, ar3);

    for (int x = 0; x < output.getWidth(); x++) {
      for (int y = 0; y < output.getHeight(); y++) {
        int pixel = output.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        // Change the brightness of the pixel by the specified amount
        red += 128;
        green += 128;
        blue += 128;

        // Ensure that the pixel values are within the valid range of 0-255
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        // Combine the new RGB values into a single pixel and set it on the output image
        int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
        output.setRGB(x, y, newPixel);
      }
    }

    // And we're done
    return output;

  }

  /**
   * <p>
   * Apply the kernel to the image
   * </p>
   * 
   * @param image  The image to apply the emboss filter to.
   * @param kernel the kernel used to apply the filter
   * @return The resulting (blurred)) image.
   */
  public static BufferedImage applyKernel(BufferedImage image, float[][] kernel) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage result = new BufferedImage(width, height, image.getType());
    int kernelWidth = kernel.length;
    int kernelHeight = kernel[0].length;
    int kernelXOffset = (kernelWidth - 1) / 2;
    int kernelYOffset = (kernelHeight - 1) / 2;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        float r = 0;
        float g = 0;
        float b = 0;
        float a = 0;

        for (int i = 0; i < kernelWidth; i++) {
          for (int j = 0; j < kernelHeight; j++) {
            int pixelPosX = x + i - kernelXOffset;
            int pixelPosY = y + j - kernelYOffset;
            if (pixelPosX < 0) {
              pixelPosX = 0;
            } else if (pixelPosX >= width) {
              pixelPosX = width - 1;
            }
            if (pixelPosY < 0) {
              pixelPosY = 0;
            } else if (pixelPosY >= height) {
              pixelPosY = height - 1;
            }
            int rgb = image.getRGB(pixelPosX, pixelPosY);
            a += ((rgb >> 24) & 0xFF) * kernel[i][j];
            r += ((rgb >> 16) & 0xFF) * kernel[i][j];
            g += ((rgb >> 8) & 0xFF) * kernel[i][j];
            b += (rgb & 0xFF) * kernel[i][j];
          }
        }
        int aInt = (int) Math.max(0, Math.min(255, a));
        int rInt = (int) Math.max(0, Math.min(255, r));
        int gInt = (int) Math.max(0, Math.min(255, g));
        int bInt = (int) Math.max(0, Math.min(255, b));
        result.setRGB(x, y, (aInt << 24) | (rInt << 16) | (gInt << 8) | bInt);
      }
    }

    return result;
  }
}
