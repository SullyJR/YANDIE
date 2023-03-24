package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
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
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
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
     * @throws IOException
     */

     
    public SettingsActions() throws IOException {
        Image image = ImageIO.read(new File("./src/cosc202/andie/icons/language.png"));
        //Image image = ImageIO.read(Andie.class.getClassLoader().getResource("language.png"));
                ImageIcon languageIcon = new ImageIcon(image);
                languageIcon.setImage(languageIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        actions = new ArrayList<Action>();
        actions.add(new LanguageAction(Language.translate("Language"), languageIcon, Language.translate("Change language"),
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

            // Creates a array of languages and opens a panel of a combobox
            String[] languages = { Language.translate("English"), Language.translate("French"), Language.translate("Malay") };

             
            try {
                Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
                ImageIcon languageIcon = new ImageIcon(image);
            
            
            
           
            String option = (String) JOptionPane.showInputDialog(null, Language.translate("Choose a language") + ":",
                    null, JOptionPane.QUESTION_MESSAGE, languageIcon, languages, languages[0]);

            // Checks the returning value of the combobox and evaluates choosen option using
            // switch
            if (option == languages[0]) {
                Language.setLanguage("en");
            } else if (option == languages[1]) {
                Language.setLanguage("fr");
            } else if (option == languages[2]) {
                Language.setLanguage("my");
            }

            if (option != null) {
                try {
                    Andie.frame.dispose();
                    Andie.createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
}
