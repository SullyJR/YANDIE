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
    // INSERT CODE HERE FOR EQUATION
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