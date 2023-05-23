package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

import cosc202.andie.EditActions.*;

/**
 * Creates a toolbar class to create interally and then called
 * externally using a constructor to avoid complication.
 */
public class ToolBar {

        /** An array of actions */
        protected static ArrayList<Action> actions;
        /** An initialised toolbar */
        protected static JToolBar toolBar = new JToolBar();
        /** A JLabel used for to notify the user of the colour selected */
        protected static JLabel colourLabel;

        /**
         * A method that creates the toolbar
         * 
         * @param imagePanel the image panel
         * @param sActions   the SelectActions class
         * @param macro      MacroRecording class
         * @return the toolbar
         * 
         * @throws Exception general exception errors
         */
        public static JToolBar createToolBar(ImagePanel imagePanel, SelectActions sActions, MacroRecorder macro)
                        throws Exception {
                toolBar = new JToolBar();
                ImagePanel ip = new ImagePanel(macro);
                FileActions fa = new FileActions();
                EditActions ea = new EditActions();
                ImageActions ia = new ImageActions(imagePanel);
                ViewActions va = new ViewActions();

                // Image scaling
                ip.iconArray[0].setImage(ip.iconArray[0].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open
                ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
                ip.iconArray[3].setImage(ip.iconArray[3].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
                                                                                                                    // as
                ip.iconArray[5].setImage(ip.iconArray[5].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Undo
                ip.iconArray[6].setImage(ip.iconArray[6].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Redo
                ip.iconArray[10].setImage(ip.iconArray[10].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Zoom
                                                                                                                      // in
                ip.iconArray[11].setImage(ip.iconArray[11].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Zoom
                                                                                                                      // out
                ip.iconArray[7].setImage(ip.iconArray[7].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Resize
                ip.iconArray[8].setImage(ip.iconArray[8].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Rotate
                ip.iconArray[9].setImage(ip.iconArray[9].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Flip

                ip.iconArray[19].setImage(ip.iconArray[19].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Crop
                ip.iconArray[20].setImage(ip.iconArray[20].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Select
                ip.iconArray[21].setImage(ip.iconArray[21].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Paint
                ip.iconArray[22].setImage(ip.iconArray[22].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Draw
                ip.iconArray[23].setImage(ip.iconArray[23].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Circle
                ip.iconArray[24].setImage(ip.iconArray[24].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Line

                ip.iconArray[27].setImage(ip.iconArray[27].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Play
                ip.iconArray[28].setImage(ip.iconArray[28].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Stop

                // Create actions list
                actions = new ArrayList<Action>();
                actions.add(fa.new FileOpenAction(null, ip.iconArray[0], Language.translate("Open a file"),
                                Integer.valueOf(KeyEvent.VK_O)));
                actions.add(fa.new FileSaveAction(null, ip.iconArray[2], Language.translate("Save the file"),
                                Integer.valueOf(KeyEvent.VK_S)));
                actions.add(fa.new FileSaveAsAction(null, ip.iconArray[3], Language.translate("Save a copy"),
                                Integer.valueOf(KeyEvent.VK_A)));
                actions.add(ia.new ResizeAction(null, ip.iconArray[7], Language.translate("Resize the image"),
                                Integer.valueOf(KeyEvent.VK_R)));
                actions.add(ia.new RotateAction(null, ip.iconArray[8], Language.translate("Rotate the image"),
                                Integer.valueOf(KeyEvent.VK_P)));
                actions.add(sActions.new CropAction(null, ip.iconArray[19],
                                Language.translate("Crop an image"), Integer.valueOf(KeyEvent.VK_C)));
                actions.add(sActions.new FillColorAction(null, ip.iconArray[21],
                                Language.translate("Make a Drawing"), Integer.valueOf(KeyEvent.VK_R)));
                actions.add(va.new ZoomInAction(null, ip.iconArray[10], Language.translate("Zoom In"),
                                Integer.valueOf(KeyEvent.VK_PLUS)));
                actions.add(va.new ZoomOutAction(null, ip.iconArray[11], Language.translate("Zoom Out"),
                                Integer.valueOf(KeyEvent.VK_MINUS)));
                actions.add(new UndoAction(null, ip.iconArray[5], Language.translate("Undo"),
                                Integer.valueOf(KeyEvent.VK_Z)));
                actions.add(ea.new RedoAction(null, ip.iconArray[6], Language.translate("Redo"),
                                Integer.valueOf(KeyEvent.VK_Y)));

                // Create buttons array
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
                                new JButton(actions.get(9)),
                                new JButton(actions.get(10)),
                };

                // Create left and right panels
                JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                // Add buttons to the left panel
                for (int i = 0; i < buttons.length; i++) {
                        leftPanel.add(buttons[i]);
                }

                JToggleButton toggleMacroButton = new JToggleButton();

                ip.iconArray[27].setImage(ip.iconArray[27].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Play
                ip.iconArray[28].setImage(ip.iconArray[28].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Stop

                toggleMacroButton.setIcon((Icon) ip.iconArray[27]);
                toggleMacroButton.setToolTipText(Language.translate("Play Macro"));

                toggleMacroButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // if toggle macro is selected turns on macro, changes icon and starts
                                // recording,
                                // when off, macro asks to be either save
                               
                                if (toggleMacroButton.isSelected()) {
                                        
                                        toggleMacroButton.setSelectedIcon(ip.iconArray[28]);
                                        toggleMacroButton.setToolTipText(Language.translate("Stop Macro"));
                                        if (!(macro.isRecording())) {
                                                macro.startRecording();
                                        }
                                } else {

                                        String[] options = { Language.translate("Save"), Language.translate("Remove") };
                                        String result = (String) JOptionPane.showInputDialog(null, null,
                                                        Language.translate("Macro Options") + ":",
                                                        JOptionPane.QUESTION_MESSAGE, null, options,
                                                        options[0]);
                                        if (result == null) {
                                                System.out.println("Ignored"); // cancel or 'X' pressed
                                                toggleMacroButton.doClick();
                                        } else if (result.equals(options[0])) { // Save option
                                                System.out.println("Macro saved");
                                                toggleMacroButton.doClick();
                                                // save the macro
                                        } else if (result.equals(options[1])) { // Remove option
                                                System.out.println("Macro removed");
                                                macro.getActions().clear(); // empty the macro
                                                toggleMacroButton.setSelectedIcon(ip.iconArray[27]);
                                                toggleMacroButton.setToolTipText(Language.translate("Play Macro"));
                                                macro.stopRecording();
                                        }
                                }
                        }
                });

                // Add toggle buttons from SelectionActions to the right panel

                rightPanel.add(toggleMacroButton);
                rightPanel.add(sActions.getToggleSelect());
                rightPanel.add(sActions.getToggleCircle());
                rightPanel.add(sActions.getToggleLine());
                rightPanel.add(sActions.getToggleDraw());
                rightPanel.add(sActions.getPaint());

                colourLabel = new JLabel();
                colourLabel.setPreferredSize(new Dimension(30, 30));
                colourLabel.setBackground(Color.WHITE);
                colourLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                colourLabel.setOpaque(true);
                rightPanel.add(new JLabel(Language.translate("Current Colour") + ": "));
                rightPanel.add(colourLabel);

                // Add left and right panels to the toolbar
                toolBar.add(leftPanel, BorderLayout.WEST);
                toolBar.addSeparator();
                toolBar.add(rightPanel, BorderLayout.EAST);

                return toolBar;
        }

        /**
         * A method which updates the colour of the label when the
         * selected colour is changed.
         * 
         * @param selectedColor the selected colour
         */
        public void updateColour(Color selectedColor) {
                colourLabel.setBackground(selectedColor);
        }
}