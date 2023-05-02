package cosc202.andie;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

// import cosc202.andie.FileActions.*;
// //import cosc202.andie.FileActions.FileSaveAction;
import cosc202.andie.EditActions.*;
// //import cosc202.andie.EditActions.RedoAction;

public class ToolBar {

    protected static ArrayList<Action> actions;
    protected static JToolBar toolBar = new JToolBar();

    public static JToolBar createToolBar() throws IOException {

        ImagePanel ip = new ImagePanel();
        FileActions fa = new FileActions();
        EditActions ea = new EditActions();

        ip.iconArray[0].setImage(ip.iconArray[0].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open
        ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
        ip.iconArray[5].setImage(ip.iconArray[5].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Undo
        ip.iconArray[6].setImage(ip.iconArray[6].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Redo

        actions = new ArrayList<Action>();
        actions.add(fa.new FileOpenAction(null, ip.iconArray[0], null, Integer.valueOf(KeyEvent.VK_O)));
        actions.add(fa.new FileSaveAction(null, ip.iconArray[2], null, Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new UndoAction(null, ip.iconArray[5], null, Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(ea.new RedoAction(null, ip.iconArray[6], null, Integer.valueOf(KeyEvent.VK_Y)));

        JButton[] buttons = {
                new JButton(actions.get(0)),
                new JButton(actions.get(1)),
                new JButton(actions.get(2)),
                new JButton(actions.get(3))
        };

        for (int i = 0; i < buttons.length; i++) {
            toolBar.add(buttons[i]);
        }
        return toolBar;

    }

}
