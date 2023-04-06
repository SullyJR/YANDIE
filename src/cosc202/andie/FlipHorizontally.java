package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;

/**
 * Image operation to flip the image horizontally
 */
public class FlipHorizontally implements ImageOperation, java.io.Serializable{
    
    /**
    * Creates a new instance of FlipHorizontally.
    */
    public FlipHorizontally(){
    }

    /**
     * Applies the FlipHorizontally operation to the specified image.
     * 
     * @param input the image to apply the operation to
     * @return the horizontally flipped image
     */
    public BufferedImage apply(BufferedImage input) {
        
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, input.getWidth(), input.getHeight(), input.getWidth(), 0, 0, input.getHeight(), null);
        g2d.dispose();
        
        return output;
    }

}
