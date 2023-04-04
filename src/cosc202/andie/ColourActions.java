package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
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
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     * 
     * @throws IOException
     */
    public ColourActions() throws IOException {
        // Adds Icons and Scales them down to fit in the box
        ImageIcon brightIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/brightness.png")));
        brightIcon.setImage(brightIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon greyIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/greyscale.png")));
        greyIcon.setImage(greyIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon conIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/contrast.png")));
        conIcon.setImage(conIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(Language.translate("Greyscale"), greyIcon,
                Language.translate("Convert to greyscale"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessAction(Language.translate("Brightness"), brightIcon,
                Language.translate("Adjust the brightness"), Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new ContrastAction(Language.translate("Contrast"), conIcon,
                Language.translate("Adjust the contrast"), Integer.valueOf(KeyEvent.VK_C)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Language.translate("Colour"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK)); // TODO: Make an
                                                                                                       // argument to
                                                                                                       // the
                                                                                                       // constructor
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
            }
        }

    }

    public class BrightnessAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        // Initial Default brightness

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int bright = 10;

            SpinnerNumberModel brightModel = new SpinnerNumberModel(10, -100, 100, 1);
            JSpinner brightSpinner = new JSpinner(brightModel);
            int option = JOptionPane.showOptionDialog(null, brightSpinner,
                    Language.translate("Enter filter brightness"), JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                bright = brightModel.getNumber().intValue();
            }
            try {
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
            }

        }

    }

    public class ContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new contrast action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        // Initial Default contrast

        /**
         * <p>
         * Callback for when the contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ContrastAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int contrast = 10;

            SpinnerNumberModel contrastModel = new SpinnerNumberModel(10, -100, 100, 1);
            JSpinner contrastSpinner = new JSpinner(contrastModel);
            int option = JOptionPane.showOptionDialog(null, contrastSpinner,
                    Language.translate("Enter filter contrast"), JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                contrast = contrastModel.getNumber().intValue();
            }
            try {
                target.getImage().apply(new Contrast(contrast));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
            }

        }

    }

}
