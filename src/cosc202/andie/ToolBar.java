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
        ImageActions ia = new ImageActions();
        ViewActions va = new ViewActions();

        ip.iconArray[0].setImage(ip.iconArray[0].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open
        ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
        ip.iconArray[3].setImage(ip.iconArray[3].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save as
        ip.iconArray[5].setImage(ip.iconArray[5].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Undo
        ip.iconArray[6].setImage(ip.iconArray[6].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Redo
        ip.iconArray[10].setImage(ip.iconArray[10].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Zoom in
        ip.iconArray[11].setImage(ip.iconArray[11].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Zoom
                                                                                                              // out
        ip.iconArray[7].setImage(ip.iconArray[7].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Resize
        ip.iconArray[8].setImage(ip.iconArray[8].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Rotate
        ip.iconArray[9].setImage(ip.iconArray[9].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Flip

        actions = new ArrayList<Action>();
        actions.add(fa.new FileOpenAction(null, ip.iconArray[0], null, Integer.valueOf(KeyEvent.VK_O)));
        actions.add(fa.new FileSaveAction(null, ip.iconArray[2], null, Integer.valueOf(KeyEvent.VK_S)));
        actions.add(fa.new FileSaveAsAction(null, ip.iconArray[3], null, Integer.valueOf(KeyEvent.VK_A)));
        actions.add(ia.new ResizeAction(null, ip.iconArray[7], null, Integer.valueOf(KeyEvent.VK_R)));
        actions.add(ia.new RotateAction(null, ip.iconArray[8], null, Integer.valueOf(KeyEvent.VK_P)));
        actions.add(ia.new FlipHorizontallyAction(null, ip.iconArray[9], null, Integer.valueOf(KeyEvent.VK_O)));
        actions.add(va.new ZoomInAction(null, ip.iconArray[10], null, Integer.valueOf(KeyEvent.VK_PLUS)));
        actions.add(va.new ZoomOutAction(null, ip.iconArray[11], null, Integer.valueOf(KeyEvent.VK_MINUS)));
        actions.add(new UndoAction(null, ip.iconArray[5], null, Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(ea.new RedoAction(null, ip.iconArray[6], null, Integer.valueOf(KeyEvent.VK_Y)));

        JButton[] buttons = { new JButton(actions.get(0)),
                new JButton(actions.get(1)),
                new JButton(actions.get(2)),
                new JButton(actions.get(3)),
                new JButton(actions.get(4)),
                new JButton(actions.get(5)),
                new JButton(actions.get(6)),
                new JButton(actions.get(7)),
                new JButton(actions.get(8)),
                new JButton(actions.get(9))

        };

        for (int i = 0; i < buttons.length; i++) {
            toolBar.add(buttons[i]);
        }
        return toolBar;

    }

}
