package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * Image Operation to apply a Median Filter
 * 
 * A Median Filter Blurs an image by moving through the image pixel by pixel
 * replacing each value wioth the median value of neighbouring pixels
 * 
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {
  
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median Filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
      this.radius = radius;    
  }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median Filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    MedianFilter() {
      this(1);
  }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Median filter is implemented via taking all pixels value
     * in a local neighbourhood and sorts them. The new pixel value is then
     * the median from the sorted list
     * 
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) { 
      int size = (radius * 2 + 1) * (radius * 2 + 1);
      float[] boxBlurKernel = new float[size];
      // CODE below is to perform a simple boxblur before applying median filter 
      for(int i = 0; i < boxBlurKernel.length; i++) {
        boxBlurKernel[i] = 1.0f;
      }
      
      Kernel kernel = new Kernel(radius * 2 + 1, radius * 2 + 1, boxBlurKernel);
      ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
      BufferedImage boxBlurredImg = convolveOp.filter(input, null);
      // LINE BELOW IS SPECIFICALLY FOR REFERENCING PIXEL DATA OF BOXBLURREDIMAGE
      // USED FOR ACCESSING AND MODIFYING PIXEL DATA \/
      WritableRaster raster = boxBlurredImg.getRaster();
      int inputWidth = input.getWidth();
      int inputHeight = input.getHeight();
      BufferedImage outputImg = new BufferedImage(inputWidth, inputHeight, BufferedImage.TYPE_INT_ARGB);
      // fun part begins **despairage**
      for(int y = 0; y < inputHeight; y++) {
        for(int x = 0; x < inputWidth; x++) {
          // Extraction of local neighbourhod of current pixel BEGINS
          int startX = x - radius;
          int startY = y - radius;
          int[] redValues = new int[size];
          int[] greenValues = new int[size];
          int[] blueValues = new int[size];
          int[] alphaValues = new int[size];
          int count = 0;
            for (int ny = startY; ny <= startY + radius * 2; ny++) {
              for (int nx = startX; nx <= startX + radius * 2; nx++) {
                // SIMILAR TO METHOD FROM CONVERT TO GRAY                        
                int[] pixel = raster.getPixel(nx, ny, new int[4]);
                redValues[count] = pixel[0];
                greenValues[count] = pixel[1];
                blueValues[count] = pixel[2];
                alphaValues[count] = pixel[3];
                count++;
              }
            }
              int medianRed = getMedian(redValues);
              int medianGreen = getMedian(greenValues);
              int medianBlue = getMedian(blueValues);
              int medianAlpha = getMedian(alphaValues);
              int medianRGB = (medianAlpha << 24) | (medianRed << 16) | (medianGreen << 8) | medianBlue;
              outputImg.setRGB(x, y, medianRGB);
        }
      }
        return outputImg;

      

    }
    /**
     * Private method to calculate median of inputArrays
     * 
     */
    private static int getMedian(int[] inputV) {
      int length = inputV.length;
      int[] sortedArray = new int[length];
      System.arraycopy(inputV, 0, sortedArray, 0, length);
      Arrays.sort(sortedArray);
      // IF ELSE STATEMENT
      // if length is even median is average of values at the middle 2 indices 
      // if length is odd median is value of the middle index
      if(length % 2 == 0) {
        return (sortedArray[length / 2] + sortedArray[length / 2 - 1]) / 2; 
      } else {
        return sortedArray[length / 2];
      }
    }
    
  
}
