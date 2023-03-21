package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;

/**
 * Image operation to flip the image horizontally
 */
public class FlipHorizontally implements ImageOperation, java.io.Serializable{
    
    public FlipHorizontally(){
    }

    /**
     * Flips the image horizontally using a Graphics2D object
     */
    public BufferedImage apply(BufferedImage input) {
        
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, input.getWidth(), input.getHeight(), input.getWidth(), 0, 0, input.getHeight(), null);
        g2d.dispose();
        
        return output;
    }

}
