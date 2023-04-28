package cosc202.andie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageSelection extends JPanel {

  private BufferedImage image;
  private Rectangle selection;
  private Point anchor;

  public ImageSelection(BufferedImage image) {
    this.image = image;
    this.selection = null;
    this.anchor = null;

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        anchor = e.getPoint();
        selection = null;
        repaint();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (selection != null && selection.width > 0 && selection.height > 0) {
          // Do something with the selected region
          System.out.println("Selected region: " + selection);
        } else {
          // Cancel the selection
          selection = null;
          repaint();
        }
      }
    });

    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = Math.min(anchor.x, e.getX());
        int y = Math.min(anchor.y, e.getY());
        int width = Math.abs(e.getX() - anchor.x);
        int height = Math.abs(e.getY() - anchor.y);
        selection = new Rectangle(x, y, width, height);
        repaint();
      }
    });
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(image.getWidth(), image.getHeight());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null);

    if (selection != null) {
      g.setColor(new Color(255, 255, 255, 128));
      g.fillRect(selection.x, selection.y, selection.width, selection.height);
      g.setColor(Color.RED);
      g.drawRect(selection.x, selection.y, selection.width, selection.height);
    }
  }

  public static void main(String[] args) {
    BufferedImage image = null;
  try { 
      File file = new File("J:/COSC202/andie/src/image.jpg");
      image = ImageIO.read(file);
  } catch (IOException e) {
      e.printStackTrace();
  }

    JFrame frame = new JFrame("Image Selection");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new ImageSelection(image));
    frame.pack();
    frame.setVisible(true);
  }
}
