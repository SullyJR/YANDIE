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
    int size = radius * 2 + 1;
    float matrix[] = new float[size * size];
    float sigma = radius / 1.5f;
    float twoSigmaSquare = 2*sigma*sigma;
    float sigmaRootPI = (float) Math.sqrt(twoSigmaSquare * Math.PI);
    int index = 0;
    // INSERT CODE HERE FOR GAUSSIAN EQUATION
    for(int x = -radius; x < radius; x++){
      for(int y = -radius; y < radius; y++){
        float distance = (x*x)+(y*y);
        matrix[index] = (float) Math.exp(distance / twoSigmaSquare) / sigmaRootPI;
        index++;
      }
    }

    ConvolveOp convOp = ConvolveOp(kernel);
    BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), false, null);
    convOp.filter(input, output);
    return output;
  }

  public double gaussianModel(int x, int y, int sigma){
    return (1 / (2 * Math.PI * Math.pow(sigma, 2)) 
    * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2))));
  }
}