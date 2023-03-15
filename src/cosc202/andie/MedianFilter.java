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
      int width = input.getWidth();
      int height = input.getHeight();

      // Initialize an empty output image
      BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      // looping over each pixel in image
      for(int y = 0; y < height; y++) {
        for(int x = 0; x < width; x++) {
          // Extraction of current pixel in local neighbourhood
          int startX = x - radius;
          int startY = y - radius;
          int endX = x + radius;
          int endY = y + radius;
          List<Integer> redValues = new ArrayList<Integer>();
          List<Integer> greenValues = new ArrayList<Integer>();
          List<Integer> blueValues = new ArrayList<Integer>();
          List<Integer> alphaValues = new ArrayList<Integer>();
          for (int ny = startY; ny <= endY; ny++) {
            for (int nx = startX; nx <= endX; nx++) {
              //Similar to convert to grey part
              int rgb = input.getRGB(nx + radius, ny + radius);
              redValues.add((rgb >> 16) & 0xFF);
              greenValues.add((rgb >> 8) & 0xFF);
              blueValues.add(rgb & 0xFF);
              alphaValues.add((rgb >> 24) & 0xFF);
            }
          }

          //Sorting each channel ascendingly
          Collections.sort(redValues);
          Collections.sort(greenValues);
          Collections.sort(blueValues);
          Collections.sort(alphaValues);
        
          //Median pixel value for each channel
          int medianR = getMedian(redValues);
          int medianG = getMedian(greenValues);
          int medianB = getMedian(blueValues);
          int medianA = getMedian(alphaValues);

          // Finally assigning median pixel value back to corresponding pixel in output image
          int medianRGB = (medianA << 24) | (medianR << 16) | (medianG << 8) | medianB;
          outputImg.setRGB(x, y, medianRGB);
        }
      }
      return outputImg;
    }
    /**
     * Private method to calculate median of inputArrays
     * 
     */
    private static int getMedian(List<Integer> inputV) {
      int length = inputV.size();
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
