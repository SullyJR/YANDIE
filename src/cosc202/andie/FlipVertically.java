package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;

/**
 * Image operation to flip the image vertically
 */
public class FlipVertically implements ImageOperation, java.io.Serializable{
    
    /**
    * Creates a new instance of FlipVertically.
    */
    public FlipVertically(){
    }

    /**
     * Applies the FlipVertically operation to the specified image.
     * 
     * @param input the image to apply the operation to
     * @return the vertically flipped image
     */
    public BufferedImage apply(BufferedImage input) {
        
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, input.getWidth(), input.getHeight(), 0, input.getHeight(), input.getWidth(), 0, null);
        g2d.dispose();
        
        return output;
    }

}
