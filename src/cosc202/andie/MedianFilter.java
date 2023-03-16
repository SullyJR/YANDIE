package cosc202.andie;

import java.awt.image.*;
import java.util.Arrays;

public class MedianFilter implements ImageOperation, java.io.Serializable  {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;
    
    /**
     * Construct Median filter with the given size.
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * @param radius
     */
    MedianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * Construct a Median filter with the default size.
     */
    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply MedianFilterTest to an image.
     * </p>
     * 
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting MedianFilter image.
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = radius; y < input.getHeight() - radius; ++y) {
            for (int x = radius; x < input.getWidth() - radius; ++x) {
                
                int[] argb = getSurroundingPixels(input, x, y, radius);
                
                int[] a = new int[argb.length];
                int[] r = new int[argb.length];
                int[] g = new int[argb.length];
                int[] b = new int[argb.length];
                
                for(int i = 0; i < argb.length; i++){
                    a[i] = (argb[i] & 0xFF000000) >> 24;
                    r[i] = (argb[i] & 0x00FF0000) >> 16;
                    g[i] = (argb[i] & 0x0000FF00) >> 8;
                    b[i] = (argb[i] & 0x000000FF);
                }
                
                //Sort the arrays
                Arrays.sort(a);
                Arrays.sort(r);
                Arrays.sort(g);
                Arrays.sort(b);
                
                //Get the middle value i.e the median of the pixel values
                int medianA = a[a.length/2];
                int medianR = r[r.length/2];
                int medianG = g[g.length/2];
                int medianB = b[b.length/2];
                
                //Set the median value current pixel
                int median = (medianA << 24) | (medianR << 16) | (medianG << 8) | medianB;
                input.setRGB(x, y, median);

            }
        }
        return input;
    }

    /**
     * A method to get the surrounding pixels of a given pixel with a given radius.
     */
    public int[] getSurroundingPixels(BufferedImage input, int x, int y, int radius) {
        int[] surroundingPixels = new int[(2*radius+1) * (2*radius+1)];
        int count = 0;
        for (int i = y - radius; i <= y+radius; ++i) {
            for (int j = x-radius; j <= x+radius; ++j) {
                surroundingPixels[count] = input.getRGB(j, i);
                count++;
            }
        }
        return surroundingPixels;
    }


}
