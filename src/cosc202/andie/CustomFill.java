package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to CustomFill a shape based on User's DrawPath.
 * </p>
 * 
 * <p>
 * The shape is then drawn over the original buffered image
 * Then returns the result of those combined
 * </p>
 * 
 */
public class CustomFill implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * An ImagePanel to call back the original ImagePanel from andie
     * to apply selections methods
     * </p>
     */
    private ImagePanel imagePanel;

    /**
     * <p>
     * A color data field to store the user's picked color
     * </p>
     */
    private Color choosedColor;

    /**
     * <p>
     * A path drawn by the user called back from ImagePanels Mouselisteners
     * </p>
     */
    private GeneralPath drawPath;

    /**
     * <p>
     * Construct a CustomFill constructor using the given ImagePanel and also choosen Color
     * </p>
     * 
     * @param imagePanel
     * @param chooseColor
     */
    CustomFill(ImagePanel imagePanel, Color chooseColor) {
        this.imagePanel = imagePanel;
        this.choosedColor = chooseColor;
        this.drawPath = null;
    }

    /**
     * <p>
     * Applies the drawing feature to input image and returns
     * the original image alongside the drawing on the image
     * </p>
     * 
     * @param input Input image to apply drawing
     * @return output The edited image
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = input;
        if(drawPath == null) {
            drawPath = imagePanel.drawingPath();
        }
        Graphics2D g = output.createGraphics();
        g.drawImage(input, null, 0, 0);
        g.setColor(choosedColor);
        g.fill(drawPath);
        g.dispose();
        return output;
    }
}
