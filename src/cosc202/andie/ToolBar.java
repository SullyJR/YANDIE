package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

import cosc202.andie.EditActions.*;

/**
 * A ToolBar Class creates a toolbar internally and used by calling externally
 * for ease of use
 */
public class ToolBar {

    /** The array of Actions for the toolbar icons */
    protected static ArrayList<Action> actions;
    /** The instance of the toolbar */
    protected static JToolBar toolBar = new JToolBar();
    /** The coloured label */
    protected static JLabel colourLabel;

    /**
     * Creates the toolbar by stores the icons and actions of each button in a loop
     * and adds to toolbar
     * 
     * @param imagePanel the image panel where to toolbar will be
     * @return the finished toolbar
     */
    public static JToolBar createToolBar(ImagePanel imagePanel) throws Exception {
	    toolBar = new JToolBar();		
        ImagePanel ip = new ImagePanel();
        FileActions fa = new FileActions();
        EditActions ea = new EditActions();
        ImageActions ia = new ImageActions(imagePanel);
        SelectActions sa = new SelectActions(imagePanel);
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

        ip.iconArray[20].setImage(ip.iconArray[20].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Select
        // ip.iconArray[21].setImage(ip.iconArray[21].getImage().getScaledInstance(16,
        // 16, Image.SCALE_SMOOTH)); // Paint
        // ip.iconArray[22].setImage(ip.iconArray[22].getImage().getScaledInstance(16,
        // 16, Image.SCALE_SMOOTH)); // Draw

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


        actions.add(sa.new SelectRectangleAction(null, ip.iconArray[20], null, Integer.valueOf(KeyEvent.VK_S)));
        actions.add(sa.new CropAction(null, ip.iconArray[20], null, Integer.valueOf(KeyEvent.VK_S)));
        actions.add(sa.new SelectRectangleAction(null, ip.iconArray[20], null, Integer.valueOf(KeyEvent.VK_S)));

        JButton[] buttons = {
                new JButton(actions.get(0)),
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

        JToggleButton[] toggleButton = { new JToggleButton(actions.get(10)) };

        // Create the left-aligned buttons panel with FlowLayout
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Create the right-aligned buttons panel with FlowLayout
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Add the left and right panels to the toolbar panel
        toolBar.add(leftPanel, BorderLayout.WEST);
        toolBar.add(rightPanel, BorderLayout.EAST);

        /** Adds non-togglable buttons to the toolbar */
        for (int i = 0; i < buttons.length; i++) {
            leftPanel.add(buttons[i]);
        }

        /** Adds togglable buttons to the toolbar */
        for (int i = 0; i < toggleButton.length; i++) {
            leftPanel.add(toggleButton[i]);
        }
        colourLabel = new JLabel();
        colourLabel.setPreferredSize(new Dimension(30, 30));
        colourLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        colourLabel.setOpaque(true);
        rightPanel.add(new JLabel("Selected Colour: "));
        rightPanel.add(colourLabel);

        // Add the left and right panels to the toolbar panel
        toolBar.add(leftPanel, BorderLayout.WEST);
        toolBar.add(rightPanel, BorderLayout.EAST);

        return toolBar;

    }

    public void updateColour(Color selectedColor){
        colourLabel.setBackground(selectedColor);
    }

}
