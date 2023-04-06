package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
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
public class FileActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;
    protected String oriExtension;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     * 
     * @throws IOException
     */
    public FileActions() throws IOException {

        // Adds Icons and Scales them down to fit in the box
        ImageIcon openIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/open.png")));
        openIcon.setImage(openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon opdefIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/default_image.png")));
        opdefIcon.setImage(opdefIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon saveIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/save.png")));
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon saveasIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/save_as.png")));
        saveasIcon.setImage(saveasIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        ImageIcon exitIcon = new ImageIcon(ImageIO.read(new File("./src/cosc202/andie/icons/exit.png")));
        exitIcon.setImage(exitIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(Language.translate("Open"), openIcon, Language.translate("Open a file"),
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileOpenDefaultAction(Language.translate("Open Default"), opdefIcon,
                Language.translate("Open a default image"), Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new FileSaveAction(Language.translate("Save"), saveIcon, Language.translate("Save the file"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(Language.translate("Save As"), saveasIcon, Language.translate("Save a copy"),
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExportAsAction(Language.translate("Export As"), saveasIcon,
                Language.translate("Export a copy"), Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExitAction(Language.translate("Exit"), exitIcon, Language.translate("Exit the program"),
                Integer.valueOf(0)));
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Language.translate("File"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * If it is an unsupported file, it will display an error message
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    oriExtension = imageFilepath.substring(1 + imageFilepath.lastIndexOf(".")).toLowerCase();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    JPanel error = new JPanel();
                    error.add(new JLabel(Language.translate("This file type is not Supported")));
                    error.setVisible(enabled);
                    JOptionPane.showMessageDialog(target, error, Language.translate("Error"),
                            JOptionPane.ERROR_MESSAGE);

                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }
    /**
     * <p>
     * Action to open a default image to the UI.
     * </p>
     */
    public class FileOpenDefaultAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenDefaultAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // JFileChooser fileChooser = new JFileChooser();
            // int result = fileChooser.showOpenDialog(target);

            if (true) {
                try {
                    // String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open("src/image.jpg");
                    //ImageIO.read(new File("./src/cosc202/andie/icons/save.png"));
                } catch (Exception ex) {
                    JPanel error = new JPanel();
                    error.add(new JLabel(Language.translate("There was a problem opening the image")));
                    error.setVisible(enabled);
                    JOptionPane.showMessageDialog(target, error, Language.translate("Error"),
                            JOptionPane.ERROR_MESSAGE);
                    // System.exit(1);
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
            } catch (Exception ex) {
                JPanel error = new JPanel();
                error.add(new JLabel(Language.translate("There was a problem saving the image")));
                error.setVisible(enabled);
                JOptionPane.showMessageDialog(target, error, Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
                // System.exit(1);
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to export an image to a new file location.
     * </p>
     */
    public class FileExportAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExportAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-export-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ExportAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {

                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    BufferedImage bi = target.getImage().getCurrentImage();

                    File output = new File(imageFilepath);

                    String extension = imageFilepath.substring(1 + imageFilepath.lastIndexOf(".")).toLowerCase();

                    int length = extension.length();
                    if (length > 5) {
                        try {
                            imageFilepath = imageFilepath + "." + oriExtension;
                            File outputDe = new File(imageFilepath);
                            ImageIO.write(bi, oriExtension, outputDe);
                            JPanel newP = new JPanel();
                            newP.add(new JLabel(Language.translate("Image have been saved to original extension")));
                            newP.setVisible(enabled);
                            JOptionPane.showMessageDialog(target, newP, Language.translate(""), JOptionPane.OK_CANCEL_OPTION);
                        } catch (IOException ex) {
                            JPanel error = new JPanel();
                            error.add(new JLabel(Language.translate("There was a problem saving the image")));
                            error.setVisible(enabled);
                            JOptionPane.showMessageDialog(target, error, Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            ImageIO.write(bi, extension, output);
                            JOptionPane.showMessageDialog(null, new JLabel("Image Saved"), "Success",
                            JOptionPane.OK_CANCEL_OPTION);
                        } catch (IOException ex) {
                            JPanel error = new JPanel();
                            error.add(new JLabel(Language.translate("There was a problem saving the image")));
                            error.setVisible(enabled);
                            JOptionPane.showMessageDialog(target, error, Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

}
