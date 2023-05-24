package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <p>
 * Actions provided by the Macro menu.
 * </p>
 * 
 * <p>
 * The Macro menu enables the user to save macro and load macro.
 * </p>
 * 
 * @author Andrew John
 * @version 1.0
 */
public class MacroActions {
    /** A list of actions for the Macro menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     *
     * @throws IOException user input exception
     */
    public MacroActions() throws IOException {

        ImagePanel ip = new ImagePanel(null);
        ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
        ip.iconArray[8].setImage(ip.iconArray[8].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Load

        actions = new ArrayList<Action>();
        actions.add(new SaveMacroAction(Language.translate("Save"), ip.iconArray[2], Language.translate("Save"), null));
        actions.add(new LoadMacroAction(Language.translate("Load"), ip.iconArray[8], Language.translate("Load"), null));
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
    public JMenu createMenu() {
        JMenu macroMenu = new JMenu("Macro");
        for (Action action : actions) {
            macroMenu.add(new JMenuItem(action));
        }
        return macroMenu;
    }

    /**
     * <p>
     * Action to save the macro from file
     * </p>
     * 
     * @see EditableImage #saveMacro()
     */
    public class SaveMacroAction extends ImageAction {
        /**
         * <p>
         * Create a new macro-save macro action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SaveMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the macro-save macro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SaveMacroAction is triggered.
         * It saves the macro recorded to a designated location.
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
                    System.out.println("i'm not yet saved?");
                    target.getImage().saveMacro(imageFilepath);
                    System.out.println("i'm saved?");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    /**
     * <p>
     * Action to load the macro from file
     * </p>
     * 
     * @see EditableImage#loadMacros(String)
     */
    public class LoadMacroAction extends ImageAction {
        /**
         * <p>
         * Create a new macro-load macro action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LoadMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the macro-load macro action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the LoadMacroAction is triggered.
         * It prompts the user to select a file and opens the corresponding macro file.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // A file chooser that only lets .macro files to be selected
            JFileChooser fileChooser = new JFileChooser();
            FileFilter ff = new FileNameExtensionFilter("Macro Files", "macro");
            fileChooser.setFileFilter(ff);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showOpenDialog(target);

            try {
                String macroFilepath = fileChooser.getSelectedFile().getCanonicalPath(); // IOException

                // Load the macros from the macro file
                target.getImage().loadMacros(macroFilepath);

            } catch (Exception err) {
                System.out.println("it reached here");
            }

            target.repaint();
            target.getParent().revalidate();

        }

    }
}