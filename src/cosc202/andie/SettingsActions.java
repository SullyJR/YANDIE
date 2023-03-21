package cosc202.andie;

import java.util.*;
import java.awt.event.*;
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
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class SettingsActions {
    
    /** A list of actions for the Settings menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Settings menu actions.
     * </p>
     */
    public SettingsActions() {
        actions = new ArrayList<Action>();
        actions.add(new LanguageAction(Language.translate("Language"), null, Language.translate("Change language"), Integer.valueOf(KeyEvent.VK_O)));
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

        for(Action action: actions) {
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
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
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

            // Creates a array of languages and opens a panel of a combobox 
            String[] languages = {Language.translate("English"), Language.translate("French"), Language.translate("Malay")};
            String option = (String)JOptionPane.showInputDialog(null, Language.translate("Choose a language" + ":"), 
                null, JOptionPane.QUESTION_MESSAGE, null, languages, languages[0]);

            // Checks the returning value of the combobox and evaluates choosen option using switch
            switch (option) {
                    case "English":
                        Language.setLanguage("en");
                        break;
                    case "French":
                        Language.setLanguage("fr");
                        break;
                    case "Malay":
                        Language.setLanguage("my");
                        break;
                    default:
                        return;
        
            }
            
        }
    }
}

