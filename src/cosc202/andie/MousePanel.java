package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MousePanel extends JPanel {

    private Point anchor;
    private Point anchorEND;
    private Rectangle selection;

    public MousePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                anchor = e.getPoint();
                anchorEND = null;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                anchorEND = e.getPoint();
                repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                anchorEND = e.getPoint();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (anchor != null && anchorEND != null) {
            int x = Math.min(anchor.x, anchorEND.x);
            int y = Math.min(anchor.y, anchorEND.y);
            int width = Math.abs(anchorEND.x - anchor.x);
            int height = Math.abs(anchorEND.y - anchor.y);
            selection = new Rectangle(x, y, width, height);
            g.setColor(Color.black);
            g.drawRect(selection.x, selection.y, selection.width, selection.height);
        }
    }

    public Rectangle getSelection() {
        return selection;
    }
}
