package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be
 * added.
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
public class EditActions {

    /** A list of actions for the Edit menu. */
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
     * Create a set of Edit menu actions.
     * </p>
     * 
     * @throws IOException user input exception
     */
    public EditActions() throws IOException {

        ImagePanel ip = new ImagePanel(macro);
        // Adds Icons and Scales them down to fit in the box
        ip.iconArray[5].setImage(ip.iconArray[5].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Undo
        ip.iconArray[6].setImage(ip.iconArray[6].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Redo

        actions = new ArrayList<Action>();
        actions.add(new UndoAction(Language.translate("Undo"), ip.iconArray[5], Language.translate("Undo"),
                Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new RedoAction(Language.translate("Redo"), ip.iconArray[6], Language.translate("Redo"),
                Integer.valueOf(KeyEvent.VK_Y)));
    }

    /**
     * <p>
     * Create a menu containing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(Language.translate("Edit"));
        // editMenu.setForeground(Color.RED);

        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public static class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().undo();
                target.repaint();
                target.getParent().revalidate();
            } catch (EmptyStackException err) {
                // cannot initiate filter without image
            }
        }
    }

    /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().redo();
                target.repaint();
                target.getParent().revalidate();
            } catch (EmptyStackException err) {
                // cannot initiate filter without image
            }
        }
    }

}
