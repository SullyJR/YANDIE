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

  /** An image panel that serves as a linkage to use imagePanel from andie */
  private ImagePanel imagePanel;

  /** A toggle button to toggle on and off Select Rectangle */
  private JToggleButton toggleSelectButton;

  /** A toggle button to toggle on and off Free Draw */
  private JToggleButton toggleDrawButton;

  /** A toggle button to toggle on and off Select Circle */
  private JToggleButton toggleCirButton;

  /** A toggle button to toggle on and off Draw Line */
  private JToggleButton toggleLineButton;

  /** A button for the user to choose a color */
  private JButton colorPickerButton;

  /** A color variable to remember what color the user has picked */
  private Color selectedColor;

  private MacroRecorder macro;

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

    ImagePanel ip = new ImagePanel(macro); // For ICONS
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
    actions.add(new FillColorAction(Language.translate("Make a Drawing"), ip.iconArray[22],
        Language.translate("Make a Drawing"), Integer.valueOf(KeyEvent.VK_R)));
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
    ImagePanel ip = new ImagePanel(macro);
    // Created a toggle button just for Selection and add it to the edit menu
    // ip.iconArray[20].setImage(ip.iconArray[20].getImage().getScaledInstance(16,
    // 16, Image.SCALE_SMOOTH)); // Select
    Image resizedImage = ip.iconArray[20].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    Icon smallIcon = new ImageIcon(resizedImage);
    toggleSelectButton = new JToggleButton(smallIcon);
    toggleSelectButton.setToolTipText(Language.translate("Select a rectangle"));
    toggleSelectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.rectToggled()) {
          imagePanel.deactivateRect();
          imagePanel.removeRect();
        } else {
          imagePanel.deactivateDraw();
          imagePanel.deactivateCir();
          imagePanel.deactivateLine();
          imagePanel.activateRect();
          toggleDrawButton.setSelected(false);
          toggleCirButton.setSelected(false);
          toggleLineButton.setSelected(false);
        }
      }
    });

    // Created a toggle button for drawing and add it to the edit menu
    resizedImage = ip.iconArray[26].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    smallIcon = new ImageIcon(resizedImage);
    toggleDrawButton = new JToggleButton(smallIcon);
    toggleDrawButton.setToolTipText(Language.translate("Select a curved line"));
    toggleDrawButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.drawToggled()) {
          imagePanel.deactivateDraw();
        } else {
          imagePanel.deactivateRect();
          imagePanel.deactivateCir();
          imagePanel.activateDraw();
          imagePanel.deactivateLine();
          toggleSelectButton.setSelected(false);
          toggleCirButton.setSelected(false);
          toggleLineButton.setSelected(false);
        }
      }
    });

    // Created a toggle button for Circle and add it to the edit menu
    resizedImage = ip.iconArray[23].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    smallIcon = new ImageIcon(resizedImage);
    toggleCirButton = new JToggleButton(smallIcon);
    toggleCirButton.setToolTipText(Language.translate("Select a circle"));
    toggleCirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.cirToggled()) {
          imagePanel.deactivateCir();
        } else {
          imagePanel.deactivateRect();
          imagePanel.deactivateDraw();
          imagePanel.deactivateLine();
          imagePanel.activateCir();
          toggleDrawButton.setSelected(false);
          toggleSelectButton.setSelected(false);
          toggleLineButton.setSelected(false);
        }
      }
    });

    // Created a toggle button for Line and add it to the edit menu
    resizedImage = ip.iconArray[24].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    smallIcon = new ImageIcon(resizedImage);
    toggleLineButton = new JToggleButton(smallIcon);
    toggleLineButton.setToolTipText(Language.translate("Select a line"));
    toggleLineButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (imagePanel.lineToggled()) {
          imagePanel.deactivateLine();
        } else {
          imagePanel.activateLine();
          imagePanel.deactivateCir();
          imagePanel.deactivateDraw();
          imagePanel.deactivateRect();
          toggleDrawButton.setSelected(false);
          toggleCirButton.setSelected(false);
          toggleSelectButton.setSelected(false);
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
        ToolBar tb = new ToolBar();
        tb.updateColour(selectedColor);
        System.out.println(selectedColor);
        colorPicker.dispose();
      }
    });

    // Adds the okbutton to
    colorPicker.add(okButton, BorderLayout.SOUTH);

    // Adds a button to the main Select menu so that it will open the color picker
    // when clicked
    resizedImage = ip.iconArray[25].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    smallIcon = new ImageIcon(resizedImage);
    colorPickerButton = new JButton(smallIcon);
    colorPickerButton.setToolTipText(Language.translate("Select a colour"));
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
    selectMenu.add(toggleLineButton); // Button for Line Tool
    selectMenu.add(colorPickerButton); // Button for color picker
    for (Action action : actions) {
      selectMenu.add(new JMenuItem(action));
    }

    return selectMenu;
  }

  /**
   * Accessor method for toggleselectbutton
   * 
   * @return the button
   */
  public JToggleButton getToggleSelect() {
    return toggleSelectButton;
  }

  /**
   * Accessor method for toggledrawbutton
   * 
   * @return the button
   */
  public JToggleButton getToggleDraw() {
    return toggleDrawButton;
  }

  /**
   * Accessor method for togglecircbutton
   * 
   * @return the button
   */
  public JToggleButton getToggleCircle() {
    return toggleCirButton;
  }

  /**
   * Accessor method for toggleLineButton
   * 
   * @return the button
   */
  public JToggleButton getToggleLine() {
    return toggleLineButton;
  }

  /**
   * Accessor method for colorpickerbutton
   * 
   * @return the button
   */
  public JButton getPaint() {
    return colorPickerButton;
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
        JOptionPane.showOptionDialog(null, "Press Yes to Proceed", Language.translate("Select Rectangle"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new SelectRectangle(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, Language.translate("Please make a valid selection"),
            Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
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

      // if draw is toggled
      if (imagePanel.drawToggled()) {
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to crop the image") + "?",
            Language.translate("Crop Image"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new Lasso(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else if (imagePanel.rectToggled()) { // if Rectangle is toggled
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to crop the image") + "?",
            Language.translate("Crop Image"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new CropRect(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else if (imagePanel.cirToggled()) { // if Circle is toggled
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to crop the image") + "?",
            Language.translate("Crop Image"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new CropCir(imagePanel));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, Language.translate("Please make a valid selection"),
            Language.translate("Error"),
            JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
  }

  public class FillColorAction extends ImageAction {
    /**
     * <p>
     * Create a new FillColorAction
     * </p>
     * 
     * @param name
     * @param icon
     * @param desc
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null)
     */
    FillColorAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
      super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the FillColorAction is triggered
     * </p>
     * 
     * <p>
     * This method is called whenever the FillColorAction is triggered.
     * It draws a rectangle or circle or custom shaped or line
     * based on the toggled button.
     * The shape is then applied
     * on the images based on the user input.
     * </p>
     * 
     * @param e The event triggering this callback
     */
    public void actionPerformed(ActionEvent e) {
      // if draw is toggled
      if (imagePanel.drawToggled()) {
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to draw on the image") + "?",
            Language.translate("Draw"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new CustomFill(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else if (imagePanel.rectToggled()) { // if Rectangle is toggled
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to draw on the image") + "?",
            Language.translate("Draw"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new FillRect(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else if (imagePanel.cirToggled()) { // if Circle is toggled
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to draw on the image") + "?",
            Language.translate("Draw"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new FillCir(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else if (imagePanel.lineToggled()) { // if Line is toggled
        JOptionPane.showOptionDialog(null, Language.translate("Would you like to draw on the image") + "?",
            Language.translate("Draw"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        try {
          target.getImage().apply(new DrawLine(imagePanel, selectedColor));
          target.repaint();
          target.getParent().revalidate();
        } catch (Exception ea) {
          // TODO: handle exception
        }
      } else {
        JOptionPane.showMessageDialog(null, Language.translate("Please make a valid selection"),
            Language.translate("Error"), JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
  }

}
