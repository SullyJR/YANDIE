package cosc202.andie;

import java.awt.*;

import javax.swing.*;
import javax.imageio.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
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
public class ImagePanel extends JPanel{
    
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * An array holding the icons.
     */
    public ImageIcon[] iconArray = {
            new ImageIcon("./src/cosc202/andie/icons/open.png", "Open"),
            new ImageIcon("./src/cosc202/andie/icons/default_image.png", "Default Image"),
            new ImageIcon("./src/cosc202/andie/icons/save.png", "Save"),
            new ImageIcon("./src/cosc202/andie/icons/save_as.png", "Save As"),
            new ImageIcon("./src/cosc202/andie/icons/exit.png", "Exit"),
            new ImageIcon("./src/cosc202/andie/icons/undo.png", "Undo"),
            new ImageIcon("./src/cosc202/andie/icons/redo.png", "Redo"),
            new ImageIcon("./src/cosc202/andie/icons/resize.png", "Resize"),
            new ImageIcon("./src/cosc202/andie/icons/rotate.png", "Rotate"),
            new ImageIcon("./src/cosc202/andie/icons/flip.png", "Flip"),
            new ImageIcon("./src/cosc202/andie/icons/zoom_in.png", "Zoom In"),
            new ImageIcon("./src/cosc202/andie/icons/zoom_out.png", "Zoom Out"),
            new ImageIcon("./src/cosc202/andie/icons/zoom_full.png", "Zoom Full"),
            new ImageIcon("./src/cosc202/andie/icons/blur.png", "Blur"),
            new ImageIcon("./src/cosc202/andie/icons/filter.png", "Filter"),
            new ImageIcon("./src/cosc202/andie/icons/greyscale.png", "Greyscale"),
            new ImageIcon("./src/cosc202/andie/icons/brightness.png", "Brightness"),
            new ImageIcon("./src/cosc202/andie/icons/contrast.png", "Contrast"),
            new ImageIcon("./src/cosc202/andie/icons/language.png", "Language"),
            new ImageIcon("./src/cosc202/andie/icons/alex.png", "Alex") };

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;

    /**
     * Mouse listener stuff
     * 
     * 
     */
    private Point anchor;

    private Point anchorEND;
    private Rectangle selection;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }
    

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }
    }


}
