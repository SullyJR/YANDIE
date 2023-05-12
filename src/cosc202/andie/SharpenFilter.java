/**
*
* The SharpenFilter class implements the ImageOperation interface to provide a
* filter that sharpens the given image by applying a convolution operation
* with a 3x3 kernel.
*/
package cosc202.andie;

import java.awt.image.*;

public class SharpenFilter implements ImageOperation, java.io.Serializable {
    /**
     * Constructs a new SharpenFilter object.
     */
    SharpenFilter() {
        // Any construction code goes here
    }

    /**
     * Applies the sharpening filter to the given BufferedImage object.
     *
     * @param input the input image to be sharpened
     * @return the sharpened output image
     */
    public BufferedImage apply(BufferedImage input) {
        float[][] kernel = {
                {0, -1 / 2.0f, 0},
                {-1 / 2.0f, 3, -1 / 2.0f},
                {0, -1 / 2.0f, 0}
        };
        int radius = 1;
        // // Make a 3x3 filter from the array
        // Kernel kernel = new Kernel(3, 3, array);
        // // Apply this as a convolution - same code as in MeanFilter
        // ConvolveOp convOp = new ConvolveOp(kernel);

        // BufferedImage output = new BufferedImage(input.getColorModel(),
        //         input.copyData(null),
        //         input.isAlphaPremultiplied(), null);
        // convOp.filter(input, output);
        // And we're done
        return applyKernelV2(input, kernel);
    }

    public static BufferedImage applyKernelV2(BufferedImage image, float[][] kernel) {
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