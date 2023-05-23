package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Image menu.
 * </p>
 * 
 * <p>
 * The Image menu contains actions that alters an image without changing
 * the image itself such as, rotation, flip, and resizing.
 * </p>
 * 
 */
public class ImageActions {

    /** A list of actions for the Image menu. */
    protected ArrayList<Action> actions;

    private MacroRecorder macro;

    /**
     * <p>
     * Create a set of Image menu actions.
     * </p>
     * 
     * @param imagePanel the image panel
     * 
     * @throws IOException user input exception
     */
    public ImageActions(ImagePanel imagePanel) throws IOException {
        ImagePanel ip = new ImagePanel(macro);
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[7].setImage(ip.iconArray[7].getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)); // Resize
        ip.iconArray[8].setImage(ip.iconArray[8].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Rotate
        ip.iconArray[30].setImage(ip.iconArray[30].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Rotate
        ip.iconArray[9].setImage(ip.iconArray[9].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Flip

        actions = new ArrayList<Action>();
        actions.add(
                new ResizeAction(Language.translate("Resize"), ip.iconArray[7], Language.translate("Resize the image"),
                        Integer.valueOf(KeyEvent.VK_R)));
        actions.add(
                new RotateAction(Language.translate("Rotate"), ip.iconArray[8], Language.translate("Rotate the image"),
                        Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new FlipHorizontallyAction(Language.translate("Flip Horizontally"), ip.iconArray[30],
                Language.translate("Flips image horizontally"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FlipVerticallyAction(Language.translate("Flip Vertically"), ip.iconArray[9],
                Language.translate("Flips image vertically"), Integer.valueOf(KeyEvent.VK_V)));

    }

    /**
     * <p>
     * Create a menu containing the list of image actions.
     * </p>
     * 
     * @return The image menu UI element.
     */
    public JMenu createMenu() {

        JMenu imageMenu = new JMenu(Language.translate("Image"));

        for (Action action : actions) {
            imageMenu.add(new JMenuItem(action));
        }

        return imageMenu;
    }

    /**
     * <p>
     * Action to resize an image {@link ImageAction}.
     * </p>
     * 
     */
    public class ResizeAction extends ImageAction {
        double percentage = 100.0;
        /**
         * <p>
         * Create a new resize action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * It resizes the images based on the user input.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the resize percentage - ask the user.
            
    
            // Create the slider
            JSlider percentageSlider = new JSlider(0, 200, 100);
            percentageSlider.setMajorTickSpacing(50);
            percentageSlider.setPaintTicks(true);
            percentageSlider.setPaintLabels(true);
    
            // Create the title label
            JLabel titleLabel = new JLabel("Resize Percentage: " + percentage + "%");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
            // Create the dialog panel
            JPanel dialogPanel = new JPanel(new BorderLayout());
            dialogPanel.add(titleLabel, BorderLayout.NORTH);
            dialogPanel.add(percentageSlider, BorderLayout.CENTER);
    
            // Update the title label when the slider value changes
            percentageSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    percentage = percentageSlider.getValue() / 100.0;
                    titleLabel.setText("Resize Percentage: " + (percentage * 100) + "%");
                }
            });
    
            // Show the dialog and get the user's input
            int option = JOptionPane.showOptionDialog(null, dialogPanel,
                    Language.translate("Enter resize percentage"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                // Create and apply the filter
                target.getImage().apply(new Resize(percentage));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }
    /**
     * <p>
     * Action to rotate an image
     * </p>
     * 
     */
    public class RotateAction extends ImageAction {

        /**
         * <p>
         * Create a new rotate action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateAction is triggered.
         * It rotates the image based on what button is pressed by the user
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                // Create the buttons
                JButton button90Left = new JButton("");
                ImageIcon leftIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/rotateleft.png"))
                        .getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                button90Left.setIcon(leftIcon);
                button90Left.addActionListener(new ActionListener() {
                    /**
                     * Rotates image 90 degrees left
                     * 
                     * @param e The event triggering this callback.
                     */
                    public void actionPerformed(ActionEvent e) {
                        try {
                            target.repaint();
                            target.getParent().revalidate();
                            rotateImage(-90.0);
                        } catch (Exception err) {
                            // cannot initiate filter without image
                        }
                    }
                });

                JButton button90Right = new JButton("");
                ImageIcon rightIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/rotateright.png"))
                        .getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                button90Right.setIcon(rightIcon);
                button90Right.addActionListener(new ActionListener() {
                    /**
                     * Rotates image 90 degrees right
                     * 
                     * @param e The event triggering this callback.
                     */
                    public void actionPerformed(ActionEvent e) {
                        try {
                            target.repaint();
                            target.getParent().revalidate();
                            rotateImage(90.0);
                        } catch (Exception err) {
                            // cannot initiate filter without image
                        }
                    }
                });

                // Create a panel to hold the buttons
                JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
                buttonPanel.add(button90Left);
                buttonPanel.add(button90Right);

                // Show the panel in a dialog box
                int option = JOptionPane.showOptionDialog(null, buttonPanel, Language.translate("Rotate Image"),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                // Repaint and revalidate the target image
                if (option == JOptionPane.OK_OPTION) {
                    target.repaint();
                    target.getParent().revalidate();
                }
            } catch (Exception ea) {
                // TODO: handle exception
            }

        }

        /**
         * Executes the rotation using the degrees from the user
         */
        private void rotateImage(double degree) {
            // Apply the filter to the target image
            target.getImage().apply(new Rotate(degree));
        }

    }

    /**
     * <p>
     * Action to horizontally flip an image
     * </p>
     * 
     */
    public class FlipHorizontallyAction extends ImageAction {

        /**
         * <p>
         * Create a new flip horizontal action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipHorizontallyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipAction is triggered.
         * It flips the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            try {
                target.getImage().apply(new FlipHorizontally());
                target.repaint();
                target.getParent().revalidate();
            } catch (NullPointerException err) {
                // cannot initiate filter without image
            }
        }
    }

    /**
     * <p>
     * Action to vertically flip an image
     * </p>
     * 
     */
    public class FlipVerticallyAction extends ImageAction {

        /**
         * <p>
         * Create a new flip vertical action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipVerticallyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipAction is triggered.
         * It flips the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            try {
                target.getImage().apply(new FlipVertically());
                target.repaint();
                target.getParent().revalidate();
            } catch (NullPointerException err) {
                // cannot initiate filter without image
            }
        }
    }

}
