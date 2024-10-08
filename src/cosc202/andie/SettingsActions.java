package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Settings menu.
 * </p>
 * 
 * <p>
 * The Settings menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * For the time being it will just be language support.
 * </p>
 * 
 * @version 1.0
 */

public class SettingsActions {

    /** A list of actions for the Settings menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * A macroRecorder that connects the current MacroRecorder 
     * to record all actions being applied onto the image
     * </p>
     */
    private MacroRecorder macro;

    /**
     * <p>
     * Create a set of Settings menu actions.
     * </p>
     * 
     * @throws IOException user input exception
     */

    public SettingsActions() throws IOException {

        ImagePanel ip = new ImagePanel(macro);
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[18].setImage(ip.iconArray[18].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Language

        actions = new ArrayList<Action>();
        actions.add(new LanguageAction(Language.translate("Language"), ip.iconArray[18],
                Language.translate("Change language"),
                Integer.valueOf(KeyEvent.VK_O)));
    }

    /**
     * <p>
     * Create a menu containing the list of Settings actions.
     * </p>
     * 
     * @return The Settings menu UI element.
     */
    public JMenu createMenu() {
        JMenu settingsMenu = new JMenu(Language.translate("Settings"));

        for (Action action : actions) {
            settingsMenu.add(new JMenuItem(action));
        }

        return settingsMenu;
    }

    /**
     * <p>
     * Action to change language from settings.
     * </p>
     * 
     */
    public class LanguageAction extends ImageAction {

        /**
         * <p>
         * Create a new settings-language action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LanguageAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the settings-language action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SettingsLanguageAction is triggered.
         * It prompts the user to select a language from a checkbox group and will
         * change all the language from the program
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {

            try {
                // Creates an array of languages
                String[] newLanguages = new String[Language.getNumLanguages()];
                // Fills the language array with translated language options
                for (int i = 0; i < Andie.languages.length; i++) {
                    newLanguages[i] = Language.translate(Andie.languages[i]);
                }
                // Creates a JOptionPane which prompts the user to choose a language
                String option = (String) JOptionPane.showInputDialog(null, null,
                        Language.translate("Choose a language") + ":", JOptionPane.QUESTION_MESSAGE, null, newLanguages,
                        newLanguages[0]);

                // checks if OK_OPTION was pressed
                if (option != null) {
                    // Checks if the new option is a duplicate of the initial language
                    String duplicateCheck = "";
                    if (option.equals(newLanguages[0])) {
                        duplicateCheck = "en";
                    } else if (option.equals(newLanguages[1])) {
                        duplicateCheck = "fr";
                    } else if (option.equals(newLanguages[2])) {
                        duplicateCheck = "my";
                    }

                    boolean change = false;
                    // Checks if the option matches to language and checks for duplicates
                    if (option.equals(newLanguages[0]) && !duplicateCheck.equals(Language.language)) {
                        Language.setLanguage("en");
                        change = true;
                    } else if (option.equals(newLanguages[1]) && !duplicateCheck.equals(Language.language)) {
                        Language.setLanguage("fr");
                        change = true;
                    } else if (option.equals(newLanguages[2]) && !duplicateCheck.equals(Language.language)) {
                        Language.setLanguage("my");
                        change = true;
                    }
                    // if an option is chosen, the frame will reload and change all the text to
                    // whatever was chosen previously, if it is the same a messagebox appears
                    if (option != null && change == true) {
                        try {
                            Andie.frame.dispose();
                            Andie.createAndShowGUI();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.exit(1);
                        }
                    } else if (change == false) {
                        JOptionPane.showMessageDialog(null, Language.translate("Language is already in use"),
                                Language.translate("Error"), JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
