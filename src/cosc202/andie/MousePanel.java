package cosc202.andie;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MousePanel extends JPanel implements MouseListener{

    private Point anchor;
    private Point anchorEND;
    private Rectangle selection;
    private boolean down = false;
    public MousePanel() {
        
        addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        down = true;
        anchor = e.getPoint();
        while(down){
            anchorEND = e.getPoint();        
            int x = Math.min(anchor.x, e.getX());
            int y = Math.min(anchor.y, e.getY());
            int width = Math.abs(e.getX() - anchor.x);
            int height = Math.abs(e.getY() - anchor.y);
            selection = new Rectangle(x, y, width, height);
            System.out.println(selection.getSize());
            repaint();
          }

    }

    public double getMX() {
        return anchor.x;
    }

    public double getMY() {
        return anchor.y;
    }

    public Rectangle getSelection() {
        return selection;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        down = false;
        System.out.println("Mouse released");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(selection!= null) {
            g.setColor(Color.black);
            g.drawRect(selection.x, selection.y, selection.width, selection.height);
        }
    }  

    /**
     * Not used yet idk what to do with em (ANDRU)
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stubjohan372
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
    


}
