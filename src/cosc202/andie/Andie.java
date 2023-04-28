package cosc202.andie;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.imageio.*;
import javax.swing.UIManager.*;
import java.io.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
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
public class Andie {

    static String[] languages = { "English", "French", "Malay" };
    static JFrame frame;

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ImageActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see SettingAction
     * 
     * @throws Exception if something goes wrong.
     */
    public static void createAndShowGUI() throws Exception {

        ArrayList<Action> actions = new ArrayList<Action>();

        ImagePanel ip = new ImagePanel();

        ip.iconArray[0].setImage(ip.iconArray[0].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Open
        ip.iconArray[2].setImage(ip.iconArray[2].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Save
        ip.iconArray[5].setImage(ip.iconArray[5].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Undo
        ip.iconArray[6].setImage(ip.iconArray[6].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // Redo

        FileActions fActions = new FileActions();
        EditActions eActions = new EditActions();

        actions.add(fActions.new FileOpenAction(null, ip.iconArray[0], null, Integer.valueOf(KeyEvent.VK_O)));
        actions.add(fActions.new FileSaveAction(null, ip.iconArray[2], null, Integer.valueOf(KeyEvent.VK_S)));
        // actions.add(eActions.new UndoAction(null, ip.iconArray[5], null,
        // Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(eActions.new RedoAction(null, ip.iconArray[6], null, Integer.valueOf(KeyEvent.VK_Y)));

        // Set up the main GUI frame
        frame = new JFrame("ANDIE");
        frame.setForeground(Color.GRAY);

        Image image = ImageIO.read(new File("./src/cosc202/andie/icons/icon.png")); // andie icon

        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            // Set the look and feel to Windows
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Override the default background color of the menu

        } catch (Exception e) {
            e.printStackTrace();
        }

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setBackground(Color.gray);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);

        // Add in toolbar for easier access to commonly used actions
        JToolBar toolBar = new JToolBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // Image actions purposedly for Rotate and flips
        ImageActions imageActions = new ImageActions();
        menuBar.add(imageActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Changes all the texts language on the UI
        SettingsActions settingsActions = new SettingsActions();
        menuBar.add(settingsActions.createMenu());

        // Sets the frame
        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.addKeyListener(new KeyPress());
        frame.pack();
        frame.setVisible(true);

        // Toolbar buttons
        toolBar.add(new JButton(null, ip.iconArray[0]));
        toolBar.add(new JButton(null, ip.iconArray[2]));
        toolBar.add(new JButton(null, ip.iconArray[5]));
        toolBar.add(new JButton(null, ip.iconArray[6]));

        // Sets the menubar
        Dimension menuBarSize = new Dimension(400, 35);
        menuBar.setPreferredSize(menuBarSize);
        menuBar.setBackground(Color.GRAY);

        // Sets the toolbar
        Dimension toolBarSize = new Dimension(400, 18);
        toolBar.setPreferredSize(toolBarSize);
        // toolBar.setBackground(Color.GRAY);

    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    createAndShowGUI();
                    // FileActions.FileOpenDefaultAction();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }

}
