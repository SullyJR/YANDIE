package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Select menu.
 * </p>
 * 
 * <p>
 * The Select menu contains actions that selects a certain area
 * of an image while applying different operations such as
 * Crop, Draw (Shapes) and (andrew Add more stuff here)
 * </p>
 */

public class SelectActions {

  /** A list of actions for the Select menu. */
  protected ArrayList<Action> actions;

  /** A image panel that serves as a linkage to use imagePanel from andie */
  private ImagePanel imagePanel;

  /** A toggle button to toggle on and off Select Rectangle */
  private JToggleButton toggleSelectButton;

  /** A toggle button to toggle on and off Draw */
  private JToggleButton toggleDrawButton;

  /** A toggle button to toggle on and off Select Circle */
  private JToggleButton toggleCirButton;

  /** A color variable to remember what color the user has picked */
  private Color selectedColor;

  /**
   * <p>
   * Create a set of Select menu actions
   * </p>
   * 
   * @param imagePanel the image panel that serves as a linkage
   * 
   * @throws IOException user input exception
   */
  public SelectActions(ImagePanel imagePanel) throws IOException {
    this.imagePanel = imagePanel;

    ImagePanel ip = new ImagePanel(); // For ICONS
    // Adds Icons and Scales them down to fit in the box
    ip.iconArray[19].setImage(ip.iconArray[19].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Crop
    ip.iconArray[20].setImage(ip.iconArray[20].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Select
    ip.iconArray[21].setImage(ip.iconArray[21].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Paint
    ip.iconArray[22].setImage(ip.iconArray[22].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Draw

    // Creates a new Actions array list
    actions = new ArrayList<Action>();
    // Adds actions into the arraylist which will then look like buttons
    actions.add(new SelectRectangleAction(Language.translate("Select Rectangle"), ip.iconArray[20],
        Language.translate("Select a rectangle"), Integer.valueOf(KeyEvent.VK_S)));
    actions.add(new CropAction(Language.translate("Crop Image"), ip.iconArray[19],
        Language.translate("Crop an image"), Integer.valueOf(KeyEvent.VK_C)));
    actions.add(new FillRectAction(Language.translate("Draw Rectangle"), ip.iconArray[22],
        Language.translate("Draw a Rectangle"), Integer.valueOf(KeyEvent.VK_R)));
    actions.add(new FillCirAction(Language.translate("Draw Circle"), ip.iconArray[22], 
        Language.translate("Draw a Circle"), Integer.valueOf(KeyEvent.VK_R)));   
  }

  /**
   * <p>
   * Create a new menu containing the list of select actions.
   * </p>
   * 
   * @return The image menu UI element.
   */
  public JMenu createMenu() {

    JMenu selectMenu = new JMenu(Language.translate("Select"));

    // Created a toggle button just for Selection and add it to the edit menu
    toggleSelectButton = new JToggleButton("Enable Selection");
    toggleSelectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.rectToggled()) {
          imagePanel.deactivateRect();
          toggleSelectButton.setText("Enable Selection");
        } else {
          imagePanel.deactivateDraw();
          imagePanel.deactivateCir();
          imagePanel.activateRect();
          toggleDrawButton.setSelected(false);
          toggleCirButton.setSelected(false);
          toggleCirButton.setText("Enable Circle");
          toggleSelectButton.setText("Disable Selection");
          toggleDrawButton.setText("Enable Drawing");
        }
      }
    });

    // Created a toggle button for drawing and add it to the edit menu
    toggleDrawButton = new JToggleButton("Enable Drawing");
    toggleDrawButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.drawToggled()) {
          imagePanel.deactivateDraw();
          toggleDrawButton.setText("Enable Drawing");
        } else {
          imagePanel.deactivateRect();
          imagePanel.deactivateCir();
          imagePanel.activateDraw();
          toggleSelectButton.setSelected(false);
          toggleCirButton.setSelected(false);
          toggleCirButton.setText("Enable Circle");
          toggleDrawButton.setText("Disable Drawing");
          toggleSelectButton.setText("Enable Selection");
        }
      }
    });

    // Created a toggle button for Circle and add it to the edit menu
    toggleCirButton = new JToggleButton("Enable Circle");
    toggleCirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.cirToggled()) {
          imagePanel.deactivateCir();
          toggleCirButton.setText("Enable Circle");
        } else {
          imagePanel.deactivateRect();
          imagePanel.deactivateDraw();
          imagePanel.activateCir();
          toggleDrawButton.setSelected(false);
          toggleSelectButton.setSelected(false);
          toggleDrawButton.setText("Enable Drawing");
          toggleSelectButton.setText("Enable Selection");
          toggleCirButton.setText("Disable Circle");
        }
      }
    });

 // Create new JDialog object which serve as the color picker window
 JDialog colorPicker = new JDialog();

 // Created a new JColorChooser object to serve as the color picker component
 JColorChooser chooser = new JColorChooser();

 // Add chooser to colorpicker dialog
 colorPicker.add(chooser);

 // Set Modal to true to prevent interaction with main application window
 colorPicker.setModal(true);

 // ok Button literally does as the name suggest
 // can put icons in as well
 JButton okButton = new JButton("OK");
 okButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     selectedColor = chooser.getColor();

     colorPicker.dispose();
   }
 });

 // Adds the okbutton to 
 colorPicker.add(okButton, BorderLayout.SOUTH);

 // Adds a button to the main Select menu so that it will open the color picker when clicked
 JButton colorPickerButton = new JButton("Pick Color");
colorPickerButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
     colorPicker.pack(); // Resize the color picker window
     colorPicker.setLocationRelativeTo(null); // Center the window on the screen
     colorPicker.setVisible(true); // Show the color picker window
 }
});


    selectMenu.add(toggleSelectButton); // Button for Select Tool
    selectMenu.add(toggleCirButton); // Button for Circle Tool
    selectMenu.add(toggleDrawButton); // Button for Drawing tool
    selectMenu.add(colorPickerButton); // Button for color picker
    for (Action action : actions) {
      selectMenu.add(new JMenuItem(action));
    }

    return selectMenu;
  }

  /**
   * <p>
   * Action to select a rectangular area to edit
   * </p>
   * 
   */
  public class SelectRectangleAction extends ImageAction {

    /**
     * <p>
     * Create a new SelectRectangle action.
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    SelectRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
      super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the Select Rectangle action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the SelectRectangle is triggered.
     * It flips the image
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {

      // Pop-up dialog box to inform user to make sure there is a
      // Selection in place

      if (imagePanel.rectToggled()) {
        JOptionPane.showOptionDialog(null, "Press Yes to Proceed", "Select Rectangle",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new SelectRectangle(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please make a selection!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
  }

  /**
   * <p>
   * Action to Crop an image {@link ImageAction}
   * </p>
   */
  public class CropAction extends ImageAction {
    /**
     * <p>
     * Create a new Crop action.
     * </p>
     * 
     * @param name
     * @param icon
     * @param desc
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if nul)
     */
    CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
      super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the crop action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the CropAction is triggered.
     * It crops the images based on the user input
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {

      // Pop-up dialog box to inform user to make sure there is a
      // Selection in place

      if (imagePanel.rectToggled()) {
        JOptionPane.showOptionDialog(null, "test", "Crop Image",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new Crop(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please make a selection before Cropping", "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
  }

  public class FillRectAction extends ImageAction {
    /**
     * <p>
     * Create a new FillRect action.
     * </p>
     * 
     * @param name
     * @param icon
     * @param desc
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null)
     */
    FillRectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
      super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the fillRect action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the FillRectAction is triggered.
     * It draws a rectangle on the images based on the user input
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {

      // Pop-up dialog box to inform user to make sure there is a
      // Selection in place

      if (imagePanel.rectToggled()) {
        JOptionPane.showOptionDialog(null, "test", "Draw a rectangle",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new FillRect(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please make a selection!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
  }

  public class FillCirAction extends ImageAction {
    /**
     * <p>
     * Create a new FillCir action
     * </p>
     * 
     * @param name
     * @param icon
     * @param desc
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null)
     */
    FillCirAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
      super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the fillCir action is triggered
     * </p>
     * 
     * <p>
     * This method is called whenever the FillCirAction is triggered.
     * It draws a circle on the images based on the user input
     * </p>
     * 
     * @param e The event triggering this callback
     */
    public void actionPerformed(ActionEvent e) {
      if (imagePanel.cirToggled()) {
        JOptionPane.showOptionDialog(null, "test", "Draw a Circle",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new FillCir(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please make a circle!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }  
    }
  }
}
