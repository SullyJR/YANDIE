package cosc202.andie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageSelectionRectangle implements ImageOperation, java.io.Serializable {

    /* private datafields, not sure what yet */
    private Rectangle selection;
    private Point anchor;
    private BufferedImage image;
     
    ImageSelectionRectangle(BufferedImage image) {
        this.selection = null;
        this.anchor = null;
        this.image = image;
    }

    public BufferedImage apply(BufferedImage input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }}    
