package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private MacroRecorder macroRecorder;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     * 
     * @throws IOException user input exception
     */
    public ColourActions() throws IOException {

        ImagePanel ip = new ImagePanel();
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[15].setImage(ip.iconArray[15].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Greyscale
        ip.iconArray[16].setImage(ip.iconArray[16].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Brightness
        ip.iconArray[17].setImage(ip.iconArray[17].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Contrast

        actions = new ArrayList<Action>();
        actions.add(new BrightnessAction(Language.translate("Brightness and Contrast"), ip.iconArray[16],
                Language.translate("Adjust the Brightness and Contrast"), Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new ConvertToGreyAction(Language.translate("Greyscale"), ip.iconArray[15],
                Language.translate("Convert to greyscale"), Integer.valueOf(KeyEvent.VK_G)));

        // actions.add(new ContrastAction(Language.translate("Contrast"),
        // ip.iconArray[17],
        // Language.translate("Adjust the contrast"), Integer.valueOf(KeyEvent.VK_C)));
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
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
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
               // macroRecorder.addAction("Greyscale");
                target.getImage().apply(new ConvertToGrey(null));
                target.repaint();
                target.getParent().revalidate();
            } catch (java.lang.NullPointerException err) {
                // cannot initiate filter without image
            }
        }

    }

    /**
     * <p>
     * Action to change the brightness based on user input.
     * </p>
     * 
     * @see Brightness
     */
    public class BrightnessAction extends ImageAction {

        /**
         * <p>
         * Create a new brightness action.
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

        /**
         * <p>
         * Callback for when the brightness action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessAction is triggered.
         * It changes the images brightness based on user input.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create sliders for brightness and contrast
            JSlider brightnessSlider = new JSlider(-100, 100, 0);
            JSlider contrastSlider = new JSlider(-100, 100, 0);

            // Set tick marks for the sliders
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setMajorTickSpacing(50);
            brightnessSlider.setMinorTickSpacing(10);
            brightnessSlider.setPaintLabels(true);

            contrastSlider.setPaintTicks(true);
            contrastSlider.setMajorTickSpacing(50);
            contrastSlider.setMinorTickSpacing(10);
            contrastSlider.setPaintLabels(true);

            // Create labels to display the current values of the sliders
            JLabel brightnessLabel = new JLabel("Brightness: " + brightnessSlider.getValue());
            JLabel contrastLabel = new JLabel("Contrast: " + contrastSlider.getValue());

            // Create a change listener for the brightness slider
            ChangeListener brightnessChangeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int selectedBrightness = brightnessSlider.getValue();
                    brightnessLabel.setText("Brightness: " + selectedBrightness);
                }
            };

            // Create a change listener for the contrast slider
            ChangeListener contrastChangeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int selectedContrast = contrastSlider.getValue();
                    contrastLabel.setText("Contrast: " + selectedContrast);
                }
            };

            // Add the change listeners to the sliders
            brightnessSlider.addChangeListener(brightnessChangeListener);
            contrastSlider.addChangeListener(contrastChangeListener);

            // Create a panel to hold the sliders and labels
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.add(brightnessLabel);
            panel.add(brightnessSlider);
            panel.add(contrastLabel);
            panel.add(contrastSlider);

            // Show a message dialog with the panel
            int result = JOptionPane.showOptionDialog(
                    null, // parent component
                    panel, // message
                    "Adjust Brightness and Contrast", // title
                    JOptionPane.OK_CANCEL_OPTION, // option type
                    JOptionPane.PLAIN_MESSAGE, // message type
                    null, // icon
                    null, // options
                    null // default option
            );

            // If the user clicked OK, get the selected values from the sliders
            if (result == JOptionPane.OK_OPTION) {
                int brightnessValue = brightnessSlider.getValue();
                int contrastValue = contrastSlider.getValue();
                System.out.println("Brightness: " + brightnessValue);
                System.out.println("Contrast: " + contrastValue);

                try {
                    // Apply the brightness and contrast filters to the image
                    target.getImage().apply(new Brightness(brightnessValue));
                    target.getImage().apply(new Contrast(contrastValue));
                    target.repaint();
                    target.getParent().revalidate();
                } catch (java.lang.NullPointerException err) {
                    // Cannot initiate filter without image
                }
            }
        }

    }

    /**
     * <p>
     * Action to change the contrast based on user input.
     * </p>
     * 
     * @see Brightness
     */
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

            SpinnerNumberModel contrastModel = new SpinnerNumberModel(contrast, -100, 100, 1);
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
                // cannot initiate filter without image
            }

        }

    }

}
