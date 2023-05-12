package cosc202.andie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.imageio.*;
import javax.swing.UIManager.*;
import java.io.*;

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
     * @see SettingsActions
     * 
     * @throws Exception if something goes wrong.
     */
    public static void createAndShowGUI() throws Exception {

        ImagePanel ip = new ImagePanel();

        // Set up the main GUI frame
        frame = new JFrame("ANDIE");
        frame.setForeground(Color.GRAY);

        Image image = ImageIO.read(new File("./src/cosc202/andie/icons/alex.png")); // andie icon
        // new File("./src/cosc202/andie/icons/icon.png"

        // Set the location of the frame to the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocationRelativeTo(null);
        frame.setLocation((frame.getWidth() - frame.getWidth()) / 2, (frame.getHeight() - frame.getHeight()) / 2);
        int x = (screenSize.width - frame.getWidth()) / 2; // center horizontally
        int y = (screenSize.height - frame.getHeight()) / 2 - 50; // center vertically and shift upwards
        frame.addMouseListener(null);
        frame.setPreferredSize(new Dimension(420, 720));

        // Set the location of the frame to the center ADD IF YOU WANT (sam)
       // frame.setLocation(x-250, y-300);

        frame.setIconImage(image); // ip.iconArray[19]
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

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // Image actions purposedly for Rotate, flips and drawings :)
        ImageActions imageActions = new ImageActions(imagePanel);
        menuBar.add(imageActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions(imagePanel);
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Changes all the texts language on the UI
        SettingsActions settingsActions = new SettingsActions();
        menuBar.add(settingsActions.createMenu());

        // Sets the frame
        frame.setJMenuBar(menuBar);
        JToolBar toolBar = ToolBar.createToolBar(imagePanel);
        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.addKeyListener(new KeyPress());
        frame.pack();
        frame.setVisible(true);

        // Sets the menubar
        Dimension menuBarSize = new Dimension(400, 35);
        menuBar.setPreferredSize(menuBarSize);
        menuBar.setBackground(Color.GRAY);

        // Sets the toolbar
        Dimension toolBarSize = new Dimension(400, 18);
        toolBar.setPreferredSize(toolBarSize);
        toolBar.setFloatable(false);

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
                    frame.setPreferredSize(new Dimension(1000, 1500));
                  

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
                // Create a mouse listener on the image display panel

            }
        });
    }

}
