package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Rectangle; 

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;
    /** To be fixed */
    private Rectangle area;
    /** To be fixed */
    private ImagePanel panel;
    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius, ImagePanel panel) {
        this.panel = panel;
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MeanFilter() {

    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        
        area = panel.getSelection();
        
        if(area != null) {
            BufferedImage newImg = input.getSubimage(area.x, area.y, area.width, area.height);
            int size = (2 * radius + 1) * (2 * radius + 1);
            float[][] kernelValues = new float[2 * radius + 1][2 * radius + 1];
            for (float[] array : kernelValues) {
                Arrays.fill(array, 1.0f / size);
            }
            return applyKernel(newImg, kernelValues);    
        } else {
            //new version of code
            int size = (2 * radius + 1) * (2 * radius + 1);
            float[][] kernelValues = new float[2 * radius + 1][2 * radius + 1];
            for (float[] array : kernelValues) {
                Arrays.fill(array, 1.0f / size);
            }
            return applyKernel(input, kernelValues);
        }
        
    }

    /**
     * <p>
     * Apply the kernel to the image
     * </p>
     * 
     * @param image The image to apply the Mean filter to.
     * @param kernel the kernel used to apply the filter
     * @return The resulting (blurred)) image.
     */
    public static BufferedImage applyKernel(BufferedImage image, float[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());
        int kernelWidth = kernel.length;
        int kernelHeight = kernel[0].length;
        int kernelXOffset = (kernelWidth - 1) / 2;
        int kernelYOffset = (kernelHeight - 1) / 2;
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                
                float r = 0;
                float g = 0;
                float b = 0;
                float a = 0;
    
                for (int i = 0; i < kernelWidth; i++) {
                    for (int j = 0; j < kernelHeight; j++) {
                        int pixelPosX = x + i - kernelXOffset;
                        int pixelPosY = y + j - kernelYOffset;
                        if (pixelPosX < 0) {
                            pixelPosX = 0;
                        } else if (pixelPosX >= width) {
                            pixelPosX = width - 1;
                        }
                        if (pixelPosY < 0) {
                            pixelPosY = 0;
                        } else if (pixelPosY >= height) {
                            pixelPosY = height - 1;
                        }
                        int rgb = image.getRGB(pixelPosX, pixelPosY);
                        a += ((rgb >> 24) & 0xFF) * kernel[i][j];
                        r += ((rgb >> 16) & 0xFF) * kernel[i][j];
                        g += ((rgb >> 8) & 0xFF) * kernel[i][j];
                        b += (rgb & 0xFF) * kernel[i][j];
                    }
                }
                int aInt = (int) Math.max(0, Math.min(255, a));
                int rInt = (int) Math.max(0, Math.min(255, r));
                int gInt = (int) Math.max(0, Math.min(255, g));
                int bInt = (int) Math.max(0, Math.min(255, b));
                result.setRGB(x, y, (aInt << 24) | (rInt << 16) | (gInt << 8) | bInt);
            }
        }
    
        return result;
    }

}
