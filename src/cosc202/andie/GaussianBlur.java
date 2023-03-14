package cosc202.andie;

import java.awt.image.*;
import java.util.*;
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
   
  /** Construct a GaussianBlur with the default size
   * 
   * By Default Gaussian has radius 1
   */
  GaussianBlur() {
    this(1);
  }

  public BufferedImage apply(BufferedImage input) {
    
    // Initializing data fields for equation
    int size = (radius * 2 + 1) * (radius * 2 + 1);
    float array[] = new float[size];
    float sigma = radius / 3f;
    
    // Getting values for kernel using gaussian equation
    int counter = 0;
    for(int i = 0; i < radius * 2 + 1; i++){
      for(int j = 0; j < radius * 2 + 1; j++){
        array[counter] = (float) calculateGaussian(j - radius, i - radius, sigma);
        counter++;
      }
    }

    Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
    ConvolveOp convOp = new ConvolveOp(kernel);
    BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), false, null);
    convOp.filter(input, output);
    return output;
  }

  /**
   * Calculates the value for the Gaussian Kernal Using the
   * 2-d gaussian equation
   * 
   * @param x horiztonal distance from centre of kernal
   * @param y vertical distance from centre of kernal
   * @param sigma the variation of the blur
   * @return
   */
  public double calculateGaussian(int x, int y, float sigma){
    return (1 / (2 * Math.PI * Math.pow(sigma, 2)) * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2))));
  }
}