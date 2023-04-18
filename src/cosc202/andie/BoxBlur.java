package cosc202.andie;

import java.awt.image.*;
import java.util.*;


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
        int size = (2 * radius + 1) * (2 * radius + 1);
        // Code input
        
        
        return input;
    }
}
