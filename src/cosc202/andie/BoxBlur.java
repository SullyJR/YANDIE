package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
* Construct a Boxblur Class which implements ImageOperation and java.io.Serializable,
* and creates and applies the box blur filter
*/
public class BoxBlur implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of the filter to apply. A raduys of 1 is a 3x3 filter, radius of 2 is a 5x5 filter and so forth
     */
    private int radius;

    /**
     * <p>
     * Construct a box blur filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger radius give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed BoxBlur 
     */
    BoxBlur(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a BoxBlur with the default size.
     * </p
     * >
     * <p>
     * By default, a BoxBlur has radius 1.
     * </p>
     * 
     * @see BoxBlur (int)
     */
    BoxBlur() {
        this(1);
    }

    /**
     * <p>
     * Apply a BoxBlur to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the BoxBlur is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the BoxBlur to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int size = (2 * radius + 1) * (2 * radius + 1);
        int[] data = new int[size];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = 0, g = 0, b = 0;
                int count = 0;

                // Get data for the kernel
                for (int ky = -radius; ky <= radius; ky++) {
                    int iy = y + ky;
                    if (iy < 0 || iy >= height) continue;

                    for (int kx = -radius; kx <= radius; kx++) {
                        int ix = x + kx;
                        if (ix < 0 || ix >= width) continue;

                        int pixel = input.getRGB(ix, iy);
                        data[count++] = pixel;

                        r += (pixel >> 16) & 0xFF;
                        g += (pixel >> 8) & 0xFF;
                        b += pixel & 0xFF;
                    }
                }

                // Calculate the average of the kernel data
                r /= size;
                g /= size;
                b /= size;

                // Set the output pixel value
                int value = (r << 16) | (g << 8) | b;
                output.setRGB(x, y, value);
            }
        }

        return output;
    }
}
