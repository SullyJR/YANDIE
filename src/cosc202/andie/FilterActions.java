package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
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

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() throws Exception {

        ImageIcon blurIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/blur.png")));
        blurIcon.setImage(blurIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon filterIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/filter.png")));
        filterIcon.setImage(filterIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon resizeIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/resize.png")));
        resizeIcon.setImage(resizeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon rotateIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/rotate.png")));
        rotateIcon.setImage(rotateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon flipIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/flip.png")));
        flipIcon.setImage(flipIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(Language.translate("Mean Filter"), filterIcon,
                Language.translate("Apply a mean filter"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(
                new SoftBlurAction(Language.translate("Soft Blur"), blurIcon, Language.translate("Apply a soft blur"),
                        Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new SharpenFilterAction(Language.translate("Sharpen Filter"), filterIcon,
                Language.translate("Apply sharpen"), Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new GaussianBlurAction(Language.translate("Gaussian Blur"), blurIcon,
                Language.translate("Apply a Gaussian blur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new MedianFilterAction(Language.translate("Median Filter"), filterIcon,
                Language.translate("Apply a median filter"), Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new ResizeAction(Language.translate("Resize"), resizeIcon, Language.translate("Resize the image"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAction(Language.translate("Rotate"), rotateIcon, Language.translate("Rotate the image"),
                Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new FlipHorizontallyAction(Language.translate("Flip Horizontally"), flipIcon,
                Language.translate("Flips image horizontally"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FlipVerticallyAction(Language.translate("Flip Vertically"), flipIcon,
                Language.translate("Flips image vertically"), Integer.valueOf(KeyEvent.VK_V)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
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
     * Action to blur an image with a mean filter.
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
         * Callback for when the convert-to-grey action is triggered.
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
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
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
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class SharpenFilterAction extends ImageAction {
        SharpenFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SoftBlurAction extends ImageAction {

        SoftBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class FlipHorizontallyAction extends ImageAction {

        FlipHorizontallyAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipHorizontally());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class FlipVerticallyAction extends ImageAction {

        FlipVerticallyAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new FlipVertically());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class GaussianBlurAction extends ImageAction {

        GaussianBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
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
            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class MedianFilterAction extends ImageAction {

        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
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
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class ResizeAction extends ImageAction {

        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            double percentage = 1.0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel percentageModel = new SpinnerNumberModel(1.0, 0.01, 10.0, 0.1);
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

    public class RotateAction extends ImageAction {

        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Determine the degree of rotation based on the button clicked
            double degree = 0.0;

            // Create the buttons
            JButton button90Left = new JButton("Rotate 90° Left");
            button90Left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = -90.0;
                    rotateImage(degree);
                }
            });

            JButton button90Right = new JButton("Rotate 90° Right");
            button90Right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = 90.0;
                    rotateImage(degree);
                }
            });

            JButton button180Left = new JButton("Rotate 180° Left");
            button180Left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = -180.0;
                    rotateImage(degree);
                }
            });

            JButton button180Right = new JButton("Rotate 180° Right");
            button180Right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = 180.0;
                    rotateImage(degree);
                }
            });

            JButton button270Left = new JButton("Rotate 270° Left");
            button270Left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = -270.0;
                    rotateImage(degree);
                }
            });

            JButton button270Right = new JButton("Rotate 270° Right");
            button270Right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = 270.0;
                    rotateImage(degree);
                }
            });

            // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
            buttonPanel.add(button90Left);
            buttonPanel.add(button90Right);
            buttonPanel.add(button180Left);
            buttonPanel.add(button180Right);
            buttonPanel.add(button270Left);
            buttonPanel.add(button270Right);

            // Show the panel in a dialog box
            int option = JOptionPane.showOptionDialog(null, buttonPanel, Language.translate("Rotate Image"),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            // Repaint and revalidate the target image
            if (option == JOptionPane.OK_OPTION) {
                target.repaint();
                target.getParent().revalidate();
            }
        }

        private void rotateImage(double degree) {
            // Apply the filter to the target image
            target.getImage().apply(new Rotate(degree));
        }
    }
}
