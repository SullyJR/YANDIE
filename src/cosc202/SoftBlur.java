package cosc202.andie;

import java.awt.image.*;


public class SoftBlur implements ImageOperation, java.io.Serializable {
SoftBlur() {
// Any construction code goes here
}
public BufferedImage apply (BufferedImage input) {
    return input;
}
}