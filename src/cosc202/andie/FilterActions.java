package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
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
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    private ImagePanel panel;
    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     * 
     * @throws IOException
     */
    public FilterActions(ImagePanel panel) throws Exception {
        this.panel = panel;

        // Icons for Toolbar
        ImagePanel ip = new ImagePanel();
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[13].setImage(ip.iconArray[13].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Blur
        ip.iconArray[14].setImage(ip.iconArray[14].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Filter

        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(Language.translate("Mean Filter"), ip.iconArray[14],
                Language.translate("Apply a mean filter"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction(Language.translate("Soft Blur"), ip.iconArray[13],
                Language.translate("Apply a soft blur"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new SharpenFilterAction(Language.translate("Sharpen Filter"), ip.iconArray[14],
                Language.translate("Apply sharpen"), Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new GaussianBlurAction(Language.translate("Gaussian Blur"), ip.iconArray[13],
                Language.translate("Apply a Gaussian blur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BoxBlurAction(Language.translate("Box Blur"), ip.iconArray[13],
                Language.translate("Apply a Box blur"), Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new MedianFilterAction(Language.translate("Median Filter"), ip.iconArray[14],
                Language.translate("Apply a median filter"), Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new EmbossFilterAction(Language.translate("Emboss Filter"), ip.iconArray[14],
                Language.translate("Apply an emboss filter"), Integer.valueOf(KeyEvent.VK_E)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Language.translate("Filter"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to filter an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */

    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the mean action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(radius, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Language.translate("Enter filter radius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            try {
                target.getImage().apply(new MeanFilter(radius, panel));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
            }
        }

    }

    /**
     * <p>
     * Action to sharpen an image with a sharpen filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenAction is triggered.
         * {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            try {
                target.getImage().apply(new SharpenFilter());
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }
        }
    }

    /**
     * <p>
     * Action to blur an image with a soft blur filter.
     * </p>
     * 
     * @see SoftBlur
     */
    public class SoftBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new soft blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the soft blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * Command
         * {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            try {
                target.getImage().apply(new SoftBlur());
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }

        }
    }

    /**
     * <p>
     * Action to blur an image with a Gaussian blur filter.
     * </p>
     * 
     * @see GaussianBlur
     */
    public class GaussianBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new Gaussian blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Gaussian blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianBlurAction is triggered.
         * {@link GaussianBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(radius, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Language.translate("Enter filter radius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            try {
                target.getImage().apply(new GaussianBlur(radius));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }
        }
    }

    /**
     * <p>
     * Action to blur an image with a box blur filter.
     * </p>
     * 
     * @see BoxBlur
     */
    public class BoxBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new box blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BoxBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the box blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BoxBlurAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(radius, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Language.translate("Enter filter radius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            try {
                target.getImage().apply(new BoxBlur(radius));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }
        }
    }

    public class EmbossFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new soft blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the soft blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * Command
         * {@link Emboss}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            try {
                Brightness b = new Brightness(128);
                target.getImage().apply(new Emboss());
                // target.getImage().apply(new Brightness(128));
                // BufferedImage bu = getImage().apply(new Emboss());
                // b.apply(bu);

                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }

        }
    }

    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        /**
         * <p>
         * Action to blur an image with a Gaussian blur filter.
         * </p>
         **/
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(radius, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Language.translate("Enter filter radius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            try {
                target.getImage().apply(new MedianFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }

        }


    }

}
