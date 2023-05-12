package cosc202.andie;

import java.awt.image.*;

/**
 * Construct a SoftBlur Class which implements ImageOperation and
 * java.io.Serializable,
 * and creates and applies the soft blur
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

    /**
     * Constructs a SoftBlur filter.
     */
    SoftBlur() {
        // Any construction code goes here
    }

    /**
     * Applies a soft blur filter to the given image.
     * 
     * @param input the image to apply the filter to
     * @return the blurred image
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, 1 / 8.0f, 0,
                1 / 8.0f, 1 / 2.0f, 1 / 8.0f,
                0, 1 / 8.0f, 0 };

        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;

    }
}