package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;

/**
 * Image Operation to rotate the images 
 * 
 * 
 */

public class Rotate implements ImageOperation, java.io.Serializable {
    
    /** Degrees field to apply to rotae*/
    private double degree;

    /**
     * Construct Rotate constructor with the given degree
     */
    Rotate(double degree) {
        this.degree = degree;
    }

    /**
     * Construct Rotate Constructor with the default degree which is 0
     * 
     */
    Rotate() {
        this(0);
    }

    /**
    * Applies the rotation to the input image and returns the rotated image.
    * @param input the input image to rotate.
    * @return the rotated image.
    */
    public BufferedImage apply(BufferedImage input) {
                        
        int witdh = input.getWidth();
        int height = input.getHeight();

        // Calculate the dimensions of the rotated image
        double radians = Math.toRadians(degree);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(witdh * cos + height * sin);
        int newHeight = (int) Math.round(witdh * sin + height * cos);

        // Create the output image with the new dimensions
        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g2 = output.createGraphics();

        //Rotation part happens
        g2.rotate(radians, newWidth/2, newHeight/2);
        
        // Calculate the translation required to center the image in the output
        int xOffset = (newWidth - witdh) / 2;
        int yOffset = (newHeight - height) / 2;

        // Apply the translation
        g2.translate(xOffset, yOffset);
        
        // Drawing the image
        g2.drawImage(input, null, 0, 0);

        return output;

    }
}
