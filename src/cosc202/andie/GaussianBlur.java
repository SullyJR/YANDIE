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
   * Construct a GaussianBlur with the default size
   *
   */
  GaussianBlur() {
    this(1);
  }

  public BufferedImage apply(BufferedImage input) {

    // Initializing data fields for equation
    int size = (radius * 2 + 1);
    float array[] = new float[size * size];
    float sigma = radius / 3f;

    // To get the index for the array
    int counter = 0;

    // used to normalise the kernel after computing it
    float kernelSum = 0f;

    // Getting values for kernel using gaussian equation
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        float value = (float) calculateGaussian(x - radius, y - radius, sigma);
        array[counter] = value;
        kernelSum += value;
        counter++;
      }
    }

    // Go over the values in the array and
    // normalise them with the array sum so that
    // the photo doesn't get brighter or darker
    // when blurred
    for (int i = 0; i < array.length; i++) {
      array[i] = array[i] / kernelSum;
    }

    Kernel kernel = new Kernel(size, size, array);
    ConvolveOp convOp = new ConvolveOp(kernel);
    BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), false, null);
    convOp.filter(input, output);
    return output;

  }

  /**
   * Calculates the value for the Gaussian Kernal Using the
   * 2-d gaussian equation
   * 
   * @param x     horiztonal distance from centre of kernal
   * @param y     vertical distance from centre of kernal
   * @param sigma the variation of the blur
   * @return
   */
  public double calculateGaussian(int x, int y, float sigma) {
    return (1 / (2 * Math.PI * Math.pow(sigma, 2))
        * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2))));
  }
}