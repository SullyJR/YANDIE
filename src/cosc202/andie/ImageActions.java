package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
public class ImageActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    public ImageActions() throws Exception {
ImageIcon resizeIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/resize.png")));
        resizeIcon.setImage(resizeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon rotateIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/rotate.png")));
        rotateIcon.setImage(rotateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon flipIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/flip.png")));
        flipIcon.setImage(flipIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

actions = new ArrayList<Action>();
actions.add(new ResizeAction(Language.translate("Resize"), resizeIcon, Language.translate("Resize the image"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAction(Language.translate("Rotate"), rotateIcon, Language.translate("Rotate the image"),
                Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new FlipHorizontallyAction(Language.translate("Flip Horizontally"), flipIcon,
                Language.translate("Flips image horizontally"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FlipVerticallyAction(Language.translate("Flip Vertically"), flipIcon,
                Language.translate("Flips image vertically"), Integer.valueOf(KeyEvent.VK_V)));
    }
public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Language.translate("Image"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
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
                    final double degree = 90.0;
                    rotateImage(degree);
                }
            });

            JButton button90Right = new JButton("Rotate 90° Right");
            button90Right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = -90.0;
                    rotateImage(degree);
                }
            });

            JButton button180 = new JButton("Rotate 180°");
            button180.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final double degree = 180.0;
                    rotateImage(degree);
                }
            });

            // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
            buttonPanel.add(button90Left);
            buttonPanel.add(button90Right);
            buttonPanel.add(button180);

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
}
