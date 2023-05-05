package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.*;
import java.awt.Graphics2D;

public class SelectRectangle implements ImageOperation, java.io.Serializable{
    
    /** Private data fields */
    
    private Rectangle selectedArea;
    private int brightness = 50;
    private MousePanel mouse; 

    /**
     * Default constructor for SelectRectangle
     */
    
    SelectRectangle() {
    }

    /**
     * Repalcement for default constructor
     * Wow
     */
    SelectRectangle(MousePanel mouse) {
      this.mouse = mouse;
    }

    public BufferedImage apply(BufferedImage input) {

        
        BufferedImage output = new BufferedImage(input.getColorModel(),input.copyData(null),input.isAlphaPremultiplied(), null);
        
        // Changing initial brightness of image
        for (int x = 0; x < output.getWidth(); x++) {
            for (int y = 0; y < output.getHeight(); y++) {
              int pixel = output.getRGB(x, y);
              int alpha = (pixel >> 24) & 0xff;
              int red = (pixel >> 16) & 0xff;
              int green = (pixel >> 8) & 0xff;
              int blue = pixel & 0xff;
      
              // Change the brightness of the pixel by the specified amount
              red += brightness;
              green += brightness;
              blue += brightness;  
      
              // Ensure that the pixel values are within the valid range of 0-255
              red = Math.max(0, Math.min(255, red));
              green = Math.max(0, Math.min(255, green));
              blue = Math.max(0, Math.min(255, blue));
      
              // Combine the new RGB values into a single pixel and set it on the output image
              int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
              output.setRGB(x, y, newPixel);
            }
          }

          
          return output;
    }
}
