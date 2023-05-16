package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to convert an image from colour to greyscale.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still technically colour images,
 * in that they have red, green, and blue values, but each pixel has equal
 * values for red, green, and blue giving a shade of grey.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ConvertToGrey implements ImageOperation, java.io.Serializable {

    private Rectangle area;
    private ImagePanel panel;

    /**
     * <p>
     * Create a new CovertToGrey operation.
     * </p>
     */


    ConvertToGrey(ImagePanel panel) {
        this.panel = panel;
    }

    /**
     * <p>
     * Apply greyscale conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion from red, green, and blue values to greyscale uses a
     * weighted average that reflects the human visual system's sensitivity
     * to different wavelengths -- we are most sensitive to green light and
     * least to blue.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {
        area = panel.getSelection();
        if (area != null) {
            // get the selected area
            BufferedImage selectedImg = input.getSubimage(area.x, area.y, area.width, area.height);

            // create a new BufferedImage object to hold the filtered result
            BufferedImage newImg = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

            // Change the value of the RGB values
            for (int y = 0; y < selectedImg.getHeight(); ++y) {
                for (int x = 0; x < selectedImg.getWidth(); ++x) {
                    int argb = selectedImg.getRGB(x, y);
                    int a = (argb & 0xFF000000) >> 24;
                    int r = (argb & 0x00FF0000) >> 16;
                    int g = (argb & 0x0000FF00) >> 8;
                    int b = (argb & 0x000000FF);

                    int grey = (int) Math.round(0.3 * r + 0.6 * g + 0.1 * b);

                    argb = (a << 24) | (grey << 16) | (grey << 8) | grey;
                    selectedImg.setRGB(x, y, argb);
                }
            }

            // draw the filtered result onto the new image at the correct location
            Graphics2D g2d = newImg.createGraphics();
            g2d.drawImage(input, 0, 0, null);
            g2d.drawImage(selectedImg, area.x, area.y, null);
            g2d.dispose();
            System.out.println("done");
            return newImg;
        
        } else {
            // Change the value of the RGB values
            for (int y = 0; y < input.getHeight(); ++y) {
                for (int x = 0; x < input.getWidth(); ++x) {
                    int argb = input.getRGB(x, y);
                    int a = (argb & 0xFF000000) >> 24;
                    int r = (argb & 0x00FF0000) >> 16;
                    int g = (argb & 0x0000FF00) >> 8;
                    int b = (argb & 0x000000FF);

                    int grey = (int) Math.round(0.3 * r + 0.6 * g + 0.1 * b);

                    argb = (a << 24) | (grey << 16) | (grey << 8) | grey;
                    input.setRGB(x, y, argb);
                }
            }
            return input;
        }

    }

}
