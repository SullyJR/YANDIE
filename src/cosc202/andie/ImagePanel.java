package cosc202.andie;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import javax.swing.*;

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
public class ImagePanel extends JPanel {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * 
     */
    private MacroRecorder macro;

    /**
     * Anchor represents the starting point when Mouse is Pressed
     */
    private Point anchor;

    /**
     * AnchorEND represents the end point when the mouse is released
     */
    private Point anchorEND;

    /**
     * Selection is the rectangle created when anchor and anchorEND exist
     * and also when toggleRect is true
     * Used in many features
     */
    private Rectangle selection;

    /**
     * toggleSelection will be a Boolean Variable to determine whether
     * the selected Rectangle will be shown or not
     */
    private boolean toggleRect;

    /**
     * selectCircle is the Circle created when anchor and anchorEND exist
     * and also when toggleCir is true
     * Used in many features as well
     */
    private Ellipse2D.Double selectCircle;

    /**
     * toggleCir will be a Boolean Variable to determine whether
     * the selected Circle will be shown or not
     */
    private boolean toggleCir;

    /**
     * drawPath will be a General Path object that store
     * the path of the user's drawing
     */
    private GeneralPath drawPath;

    /**
     * toggleDraw will be a Boolean Variable to determine whether
     * the drawing functionality will activate or not
     */
    private boolean toggleDraw;

    /**
     * selectLine is the Line create when anchor and anchorEND exist
     * and also when toggleLine is true.
     * Only used in drawing lines
     */
    private Line2D selectLine;

    /**
     * toggleLine will be a Boolean Variable to determine whether
     * the line drawing functionality will activate or not
     */
    private boolean toggleLine;

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
            new ImageIcon("./src/cosc202/andie/icons/crop.png", "Crop"),
            new ImageIcon("./src/cosc202/andie/icons/select.png", "Select"),
            new ImageIcon("./src/cosc202/andie/icons/fill.png", "Paint"),
            new ImageIcon("./src/cosc202/andie/icons/draw.png", "Draw"),
            new ImageIcon("./src/cosc202/andie/icons/circle.png", "Circle"),
            new ImageIcon("./src/cosc202/andie/icons/line.png", "Line"),
            new ImageIcon("./src/cosc202/andie/icons/pipette.png", "Pipette"),
            new ImageIcon("./src/cosc202/andie/icons/curved.png", "Curved"),
            new ImageIcon("./src/cosc202/andie/icons/play.png", "Play"),
            new ImageIcon("./src/cosc202/andie/icons/stop.png", "Stop"),
            new ImageIcon("./src/cosc202/andie/icons/alex.png", "Alex")
    };

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
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * Newly created ImagePanels also have toggleSelection false on default
     * </p>
     * 
     * @param macro used to record previous actions
     */
    public ImagePanel(MacroRecorder macro) {
        this.macro = macro;
        image = new EditableImage(macro);
        scale = 1.0;

        // Making sure all is untoggled so weird things dont happen
        toggleRect = false;
        toggleDraw = false;
        toggleCir = false;
        toggleLine = false;

        // Adding a global mouselistener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (toggleRect || toggleCir || toggleLine) {
                    // Rectangle Selection & Circle
                    anchor = e.getPoint();
                    anchorEND = null;
                    repaint();
                } else if (toggleDraw) {
                    // Create a new GeneralPath object to store the user's drawing
                    drawPath = new GeneralPath();
                    drawPath.moveTo(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (toggleRect || toggleCir || toggleLine) {
                    // Rectangle Selection & Circle
                    anchorEND = e.getPoint();
                    repaint();
                } else if (toggleDraw) {
                    // Does it need anything in here?
                } else {
                    // potentially more items?
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (toggleRect || toggleCir || toggleLine) {
                    // Rectangle Selection & Circle
                    anchorEND = e.getPoint();
                    repaint();
                } else if (toggleDraw) {
                    // Create a new GeneralPath object to store the user's drawing
                    drawPath.lineTo(e.getX(), e.getY());
                    repaint();
                }
            }
        });
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
        if (toggleRect && anchor != null && anchorEND != null) {
            int x = Math.min(anchor.x, anchorEND.x);
            int y = Math.min(anchor.y, anchorEND.y);
            int width = Math.abs(anchorEND.x - anchor.x);
            int height = Math.abs(anchorEND.y - anchor.y);
            selection = new Rectangle(x, y, width, height);
            g.setColor(Color.black);
            g.drawRect(selection.x, selection.y, selection.width, selection.height);
        }
        if (toggleCir && anchor != null && anchorEND != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            selectCircle = new Ellipse2D.Double(
                    Math.min(anchor.x, anchorEND.x),
                    Math.min(anchor.y, anchorEND.y),
                    Math.abs(anchorEND.x - anchor.x),
                    Math.abs(anchorEND.y - anchor.y));
            g2.setColor(Color.BLACK);
            g2.draw(selectCircle);
        }
        if (toggleLine && anchor != null && anchorEND != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            selectLine = new Line2D.Double(anchor, anchorEND);
            g2.setColor(Color.BLACK);
            g2.draw(selectLine);
            g2.dispose();
        }
        if (toggleDraw) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            g2.draw(drawPath);
            g2.dispose();
        }
    }

    /**
     * <p>
     * Method that returns the rectangle when called
     * Used mainly to apply crop and other features
     * </p>
     * 
     * @return selection A Rectangle which the user selected
     */
    public Rectangle getSelection() {
        return selection;
    }

    /**
     * <p>
     * Method to remove selection
     * </p>
     */
    public void removeRect() {
        selection = null;
    }

    /**
     * <p>
     * Method that sets toggleRect to true so
     * that it shows the drawing and also calculates
     * the rectangle
     * </p>
     * 
     */
    public void activateRect() {
        toggleRect = true;
    }

    /**
     * <p>
     * Method that sets toggleRect to false so
     * that it doesn't show the drawing and not
     * calculate the rectangle
     * </p>
     * 
     */
    public void deactivateRect() {
        toggleRect = false;
    }

    /**
     * <p>
     * Method that returns the status of toggleRect
     * </p>
     * 
     * @return toggleRect Which will be either true or false
     */
    public boolean rectToggled() {
        return toggleRect;
    }

    /**
     * <p>
     * Method that returns the drawpath created by the user
     * </p>
     * 
     * @return drawPath Which is the path created by the user
     */
    public GeneralPath drawingPath() {
        return drawPath;
    }

    /**
     * <p>
     * Method that sets toggle draw to true so
     * It enables the drawing funciionality
     * </p>
     */
    public void activateDraw() {
        toggleDraw = true;
    }

    /**
     * <p>
     * Method that sets toggle draw to false so
     * It disables the drawing funciionality
     * </p>
     */
    public void deactivateDraw() {
        toggleDraw = false;
    }

    /**
     * <p>
     * Method that returns the status of toggleDraw
     * </p>
     * 
     * @return toggleDraw which will either be true or false
     */
    public boolean drawToggled() {
        return toggleDraw;
    }

    /**
     * <p>
     * Method that returns the circle when called
     * Could also be used with alot of features
     * </p>
     * 
     * @return selectCir The Circle created when the user drew one
     */
    public Ellipse2D.Double getCircle() {
        return selectCircle;
    }

    /**
     * <p>
     * Method that sets toggleCir to true so
     * that it shows the drawing and also calculates the circle
     * </p>
     * 
     */
    public void activateCir() {
        toggleCir = true;
    }

    /**
     * <p>
     * Method that sets toggleCir to false so
     * that it doesn't show the drawing and
     * not calculate the circle
     * </p>
     * 
     */
    public void deactivateCir() {
        toggleCir = false;
    }

    /**
     * <p>
     * Method that returns the status of toggleCir
     * </p>
     * 
     * @return toggleCir Which will be either true of false
     */
    public boolean cirToggled() {
        return toggleCir;
    }

    /**
     * <p>
     * Method that returns the line plotted by user
     * </p>
     * 
     * @return selectLine The line created
     */
    public Line2D getLine() {
        return selectLine;
    }

    /**
     * <p>
     * Method that returns the status of toggleLine
     * </p>
     * 
     * @return toggleLine Which will be either true of false
     */
    public boolean lineToggled() {
        return toggleLine;
    }

    /**
     * <p>
     * Method that sets toggleLine to true so
     * the user is able to draw a line on the imagepanel
     * </p>
     * 
     */
    public void activateLine() {
        toggleLine = true;
    }

    /**
     * <p>
     * Method that sets toggleLine to false so
     * the user is not able to draw a line on the imagepanel
     * </p>
     * 
     */
    public void deactivateLine() {
        toggleLine = false;
    }
}
