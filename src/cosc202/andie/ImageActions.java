package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    private ImagePanel imagePanel;

    /**
     * <p>
     * Create a set of Image menu actions.
     * </p>
     * 
     * @throws IOException
     */
    public ImageActions(ImagePanel imagePanel) throws IOException {
        this.imagePanel = imagePanel;
        ImagePanel ip = new ImagePanel();
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[7].setImage(ip.iconArray[7].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Resize
        ip.iconArray[8].setImage(ip.iconArray[8].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Rotate
        ip.iconArray[9].setImage(ip.iconArray[9].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Flip

        actions = new ArrayList<Action>();
        actions.add(
                new ResizeAction(Language.translate("Resize"), ip.iconArray[7], Language.translate("Resize the image"),
                        Integer.valueOf(KeyEvent.VK_R)));
        actions.add(
                new RotateAction(Language.translate("Rotate"), ip.iconArray[8], Language.translate("Rotate the image"),
                        Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new FlipHorizontallyAction(Language.translate("Flip Horizontally"), ip.iconArray[9],
                Language.translate("Flips image horizontally"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FlipVerticallyAction(Language.translate("Flip Vertically"), ip.iconArray[9],
                Language.translate("Flips image vertically"), Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new SelectRectangleAction(Language.translate("Select Rectangle"), ip.iconArray[9],
                Language.translate("Select a rectangle"), Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new PixelAction(Language.translate("Pixelate"), ip.iconArray[9],
                Language.translate("Pixelate the image"), Integer.valueOf(KeyEvent.VK_V)));
    }

    /**
     * <p>
     * Create a menu containing the list of image actions.
     * </p>
     * 
     * @return The image menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(Language.translate("Image"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to resize an image {@link ImageAction}.
     * </p>
     * 
     */
    public class ResizeAction extends ImageAction {

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
         * It resizes the images based on the user input
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            double percentage = 1.0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel percentageModel = new SpinnerNumberModel(percentage, 0.01, 10.0, 0.1);
            JSpinner percentageSpinner = new JSpinner(percentageModel);
            int option = JOptionPane.showOptionDialog(null, percentageSpinner,
                    Language.translate("Enter resize percentage in decimal places"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                percentage = percentageModel.getNumber().doubleValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Resize(percentage));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class PixelAction extends ImageAction {

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
        PixelAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * It resizes the images based on the user input
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            double percentage = 1.0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel percentageModel = new SpinnerNumberModel(percentage, 0.01, 10.0, 0.1);
            JSpinner percentageSpinner = new JSpinner(percentageModel);
            int option = JOptionPane.showOptionDialog(null, percentageSpinner,
                    Language.translate("Enter resize percentage in decimal places"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                percentage = percentageModel.getNumber().doubleValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Resize(0.2));
            target.repaint();
            target.getParent().revalidate();
            target.getImage().apply(new Resize(5));
            target.repaint();
            target.getParent().revalidate();
        }
    }

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
                        target.repaint();
                        target.getParent().revalidate();
                        rotateImage(-90.0);
                    }
                });

                JButton button90Right = new JButton("");
                ImageIcon rightIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/rotateright.png"))
                        .getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                button90Right.setIcon(rightIcon);
                // button90Right.setIcon("./src/cosc202/andie/icons/resize.png");

                button90Right.addActionListener(new ActionListener() {
                    /**
                     * Rotates image 90 degrees right
                     * 
                     * @param e The event triggering this callback.
                     */
                    public void actionPerformed(ActionEvent e) {
                        target.repaint();
                        target.getParent().revalidate();
                        rotateImage(90.0);
                    }
                });

                // JButton button180 = new JButton(Language.translate("Rotate") +"180°");
                // button180.addActionListener(new ActionListener() {
                // /**
                // * Rotates image 180 degrees
                // * @param e The event triggering this callback.
                // */
                // public void actionPerformed(ActionEvent e) {
                // rotateImage(180.0);
                // }
                // });

                // Create a panel to hold the buttons
                JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
                buttonPanel.add(button90Left);
                buttonPanel.add(button90Right);
                // buttonPanel.add(button180);

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

        private void rotateImage(double degree) {
            // Apply the filter to the target image
            target.getImage().apply(new Rotate(degree));
        }

    }

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
            target.getImage().apply(new FlipHorizontally());
            target.repaint();
            target.getParent().revalidate();
        }
    }

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
            target.getImage().apply(new FlipVertically());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SelectRectangleAction extends ImageAction {

        /**
         * <p>
         * Create a new SelectRectangle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SelectRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Select Rectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SelectRectangle is triggered.
         * It flips the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SelectRectangle(imagePanel));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
