package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;


public class SelectionsActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    private ImagePanel panel;    

    /**
     * 
     * <p>
     * Create a set of Selection Menu Actions.
     * </p>
     * 
     * @throws IOException
     */
    public SelectionsActions(ImagePanel panel) {
        this.panel = panel;

        // ip is Icons for Toolbar
        ImagePanel ip = new ImagePanel();

        actions = new ArrayList<Action>();
        actions.add(new toggleRectangle(panel,"toggle Rectangle", ip.iconArray[1], "lalala", Integer.valueOf(KeyEvent.VK_T)));

    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Language.translate("Selection"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to toggle Selection Rectangle On
     * </p>
     * 
     */
    public class toggleRectangle extends ImageAction {
        /**
         * <p>
         * Toggles the selection to On 
         * </p>
         *
         */   
        toggleRectangle(ImagePanel panel,String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }   

        /**
         * <p>
         * Callback for when the toggleRectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the toggleRectangle is triggered.
         * When clicked it enables the Selection to work accordingly
         * 
         * </p>
         */

        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showOptionDialog(null, "Toggle Selection", "Do you wish to toggle Selection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if(option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                panel.activateSelection();
            }
            
            try{
                System.out.println("SUCCESSFUL WOW");
            }catch(java.lang.NullPointerException err) {

            }
        }   
    }

}