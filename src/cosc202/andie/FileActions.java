package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    /** A String of the original extension */
    protected String oriExtension;

    /**
     * <p>
     * A macroRecorder that connects the current MacroRecorder 
     * to record all actions being applied onto the image
     * </p>
     */
    private MacroRecorder macro;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     * 
     * @throws IOException user input exception
     */
    public FileActions() throws IOException {

        // Adds Icons and Scales them down to fit in the box
        // Adds Icons and Scales them down to fit in the box
        ImagePanel ip = new ImagePanel(macro);

        ip.iconArray[0].setImage(ip.iconArray[0].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open
        ip.iconArray[1].setImage(ip.iconArray[1].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open def
        ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
        ip.iconArray[3].setImage(ip.iconArray[3].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save //
                                                                                                            // as/Export
        ip.iconArray[4].setImage(ip.iconArray[4].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Exit

        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(Language.translate("Open"), ip.iconArray[0], Language.translate("Open a file"),
                Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileOpenDefaultAction(Language.translate("Open Default"), ip.iconArray[1],
                Language.translate("Open a default image"), Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new FileSaveAction(Language.translate("Save"), ip.iconArray[2], Language.translate("Save the file"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(Language.translate("Save As"), ip.iconArray[3],
                Language.translate("Save a copy"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExportAsAction(Language.translate("Export As"), ip.iconArray[3],
                Language.translate("Export a copy"), Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExitAction(Language.translate("Exit"), ip.iconArray[4],
                Language.translate("Exit the program"), Integer.valueOf(0)));
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

            // A file chooser that only lets jpg, jpeg, and pngs to be selected, all other
            // file types/extensions are removed from being selected
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            FileFilter ff = new FileNameExtensionFilter("Suitable ANDIE extensions", "jpg", "jpeg", "png", "tiff");
            fileChooser.addChoosableFileFilter(ff);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) { // Check if a file was selected
                String imageFilepath;
                try {
                    File selectedFile = fileChooser.getSelectedFile(); // Get the selected file
                    imageFilepath = selectedFile.getCanonicalPath();
                    oriExtension = imageFilepath.substring(1 + imageFilepath.lastIndexOf(".")).toLowerCase();     
                } catch (IOException err) {
                    imageFilepath = null;
                    // Handle IO exception
                } 
                EditableImage var = target.getImage();
                try{
                    var.open(imageFilepath);
                }catch(Exception err){
                    System.out.println(err);// Handle Exception
                }

            } else {
                // No file was selected or dialog was canceled
                System.out.println("No file selected.");
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
                    // ImageIO.read(new File("./src/cosc202/andie/icons/save.png"));
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
            if (target.getImage().getCurrentImage() == null) {
                JPanel error = new JPanel();
                error.add(new JLabel(Language.translate("You have no image to export!")));
                error.setVisible(enabled);
                JOptionPane.showMessageDialog(target, error, Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
                return;
            }
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
                            JOptionPane.showMessageDialog(target, newP, "",
                                    JOptionPane.OK_CANCEL_OPTION);
                        } catch (IOException ex) {
                            JPanel error = new JPanel();
                            error.add(new JLabel(Language.translate("There was a problem saving the image")));
                            error.setVisible(enabled);
                            JOptionPane.showMessageDialog(target, error, Language.translate("Error"),
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            ImageIO.write(bi, extension, output);
                            JOptionPane.showMessageDialog(null,
                                    new JLabel(Language.translate("Image") + " " + Language.translate("Saved"), null,
                                            JOptionPane.OK_CANCEL_OPTION));
                        } catch (IOException ex) {
                            JPanel error = new JPanel();
                            error.add(new JLabel(Language.translate("There was a problem saving the image")));
                            error.setVisible(enabled);
                            JOptionPane.showMessageDialog(target, error, Language.translate("Error"),
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (Exception ex) {

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
