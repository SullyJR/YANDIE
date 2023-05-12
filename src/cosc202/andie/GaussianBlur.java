/**
* The GaussianBlur class implements the ImageOperation interface to provide a
* filter that applies a Gaussian blur to the given image.
*/

package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * Image operation to apply GaussianBlur filter.
 * </p>
 * 
 * <p>
 * Description for GaussianBlur
 * </p>
 * 
 * 
 * 
 * 
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {

  /** Size of filter to apply. */
  private int radius;

  /**
   * Construct a Gaussian Blur Filter with the given size.
   * 
   * Desc
   */
  GaussianBlur(int radius) {
    this.radius = radius;
  }

  /**
   * Constructs a new GaussianBlur object with the given radius.
   * 
   * @param radius the radius of the Gaussian filter to be applied
   * @throws IllegalArgumentException if radius is negative
   */
  GaussianBlur() {
    this(1);
  }

  /**
   * Applies the Gaussian blur filter to the given BufferedImage object.
   * 
   * @param input the input image to be blurred
   * @return the blurred output image
   */

  public BufferedImage apply(BufferedImage input) {

    // Initializing data fields for equation
    int size = (radius * 2 + 1);
    float kernel[][] = new float[size][size];
    float array[] = new float[size*size];
    float sigma = radius / 3f;

    // To get the index for the array

    // used to normalise the kernel after computing it
    float kernelSum = 0f;
    int count = 0;

    // Getting values for kernel using gaussian equation
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        float value = (float) calculateGaussian(x - radius, y - radius, sigma);
        kernel[y][x] = value;
        array[count] = value;
        count++;
        kernelSum += value;
      }
    }

    // Go over the values in the array and
    // normalise them with the array sum so that
    // the photo doesn't get brighter or darker
    // when blurred
    int count2 = 0;
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        kernel[i][j] = kernel[i][j] / kernelSum;
        array[count2] = array[count2] / kernelSum;
        count2++;
      }
    }

    // Kernel kernelOld = new Kernel(size, size, array);
    // ConvolveOp convOp = new ConvolveOp(kernelOld);
    // BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), false, null);
    // convOp.filter(input, output);
    // return output;
    return applyKernel(input, kernel);

  }

  /**
   * Calculates the value for the Gaussian Kernel Using the
   * 2-d gaussian equation
   * 
   * @param x     horiztonal distance from centre of kernal
   * @param y     vertical distance from centre of kernal
   * @param sigma the variation of the blur
   * @return the computation returns the Gaussian kernel value
   */
  public double calculateGaussian(int x, int y, float sigma) {
    return (1 / (2 * Math.PI * Math.pow(sigma, 2))
        * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2))));
  }

  /**
   * Applies kernel to the image 
   * 
   * @param image the image that is being affected by the filter
   * @param kernel the kernel used to alter the image
   * @return results of the computation
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