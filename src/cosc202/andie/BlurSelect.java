package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
import java.util.Arrays;

/**
 * A BlurSelect class which blurs only the area selected by the user.
 * Implements ImageOperation and java.io.Serializable
 */
public class BlurSelect implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * An ImagePanel to call back the original ImagePanel from andie
     * to apply selections methods
     * </p>
     */
    private ImagePanel imagePanel;

    /**
     * Selected area of the rectangle
     */
    private Rectangle area;

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a BlurSelect with the given radius.
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
    BlurSelect(int radius, ImagePanel imagePanel) {
        this.radius = radius;
        this.imagePanel = imagePanel;
        this.area = null;
    }

    /**
     * Applies the blur select to the image
     * 
     * @param image the image being used
     * @return the buffered image
     */
    public BufferedImage apply(BufferedImage input) {
        if (area == null) {
            area = imagePanel.getSelection();
        }
        // get the selected area
        BufferedImage selectedImg = input.getSubimage(area.x, area.y, area.width, area.height);

        // apply filter to the selected area
        float[][] kernelValues = new float[2 * radius + 1][2 * radius + 1];
        int size = (2 * radius + 1) * (2 * radius + 1);
        for (float[] array : kernelValues) {
            Arrays.fill(array, 1.0f / size);
        }
        BufferedImage filteredImg = applyKernel(selectedImg, kernelValues);

        // create a new BufferedImage object to hold the filtered result
        BufferedImage newImg = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        // draw the filtered result onto the new image at the correct location
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(input, 0, 0, null);
        g2d.drawImage(filteredImg, area.x, area.y, null);
        g2d.dispose();

        return newImg;

    }

    /**
     * <p>
     * Apply the kernel to the image
     * </p>
     * 
     * @param image  The image to apply the Mean filter to.
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
