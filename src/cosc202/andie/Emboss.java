package cosc202.andie;
import java.awt.image.*;

public class Emboss implements ImageOperation, java.io.Serializable {
    

    /**
     * Constructs a SoftBlur filter.
     */
    Emboss() {
        // Any construction code goes here
    }

    /**
     * Applies an emboss filter to the given image.
     * 
     * @param input the image to apply the filter to
     * @return the blurred image
     */
    public BufferedImage apply(BufferedImage input) {
      float[] h = { 1/-2, 0, 1/2,
                      -1, 0, 1,
                    1/-2, 0, 1/2 };

      float[] v = { 1/-2, -1, -1/2,
                        0, 0, 0,
                      1/2, 1, 1/2 };

                      float[] kernel = {
                        -2, -1,  0,
                        -1,  1,  1,
                         0,  1,  2
                    };                

       float[] ar1 = { 0,0,0,1,0,-1,0,0,0};
       float[] ar2 = { 1,0,0,0,0,0,0,0,-1};
       float[] ar3 = { 0,1,0,0,0,0,0,-1,0};
       float[] ar4 = { 0,0,0,-1,0,1,0,0,0};
       float[] ar5 = { 0,0,0,-1,0,1,0,0,0};
       float[] ar6 = { -1,0,0,0,0,0,0,0,1};
       float[] ar7 = { 0,-1,0,0,0,0,0,1,0};
       float[] ar8 = { 0,0,-1,0,0,0,1,0,0};
     
     
        // Make a 3x3 filter from the array kernal
        Kernel k1 = new Kernel(3, 3, h);
        Kernel k2 = new Kernel(3, 3, v);
        Kernel k3 = new Kernel(3, 3, ar1);
        Kernel k4 = new Kernel(3, 3, ar2);
        Kernel k5 = new Kernel(3, 3, ar3);
        Kernel k6 = new Kernel(3, 3, ar4);
        Kernel k7 = new Kernel(3, 3, ar5);
        Kernel k8 = new Kernel(3, 3, ar6);
        Kernel k9 = new Kernel(3, 3, ar7);
        Kernel k10 = new Kernel(3, 3, ar8);
        Kernel k = new Kernel(3, 3, kernel);

        // Apply this as a convolution - same code as in MeanFilter convOp
        ConvolveOp c1 = new ConvolveOp(k1);
        ConvolveOp c2 = new ConvolveOp(k2);
         ConvolveOp c3 = new ConvolveOp(k3);
        ConvolveOp c4 = new ConvolveOp(k4);
        ConvolveOp c5 = new ConvolveOp(k5);
        ConvolveOp c6 = new ConvolveOp(k6);
        ConvolveOp c7 = new ConvolveOp(k7);
        ConvolveOp c8 = new ConvolveOp(k8);
        ConvolveOp c9 = new ConvolveOp(k9);
        ConvolveOp c10 = new ConvolveOp(k10);
        ConvolveOp c = new ConvolveOp(k);
      

        BufferedImage output = new BufferedImage(input.getColorModel(),input.copyData(null),input.isAlphaPremultiplied(), null);
        // c1.filter(input, output);
        // c2.filter(input, output);
        // c3.filter(input, output);
        // c4.filter(input, output);
        // c5.filter(input, output);
        // c6.filter(input, output);
        // c7.filter(input, output);
        // c8.filter(input, output);
        // c9.filter(input, output);
         c10.filter(input, output);
    // c.filter(input, output); // <-- this is the funky emboss. maybe add at the end

     for (int x = 0; x < output.getWidth(); x++) {
      for (int y = 0; y < output.getHeight(); y++) {
        int pixel = output.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        // Change the brightness of the pixel by the specified amount
        red += 128;
        green += 128;
        blue += 128;  

        // Ensure that the pixel values are within the valid range of 0-255
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        // Combine the new RGB values into a single pixel and set it on the output image
        int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
        output.setRGB(x, y, newPixel);
      }
    }

        
        
        // And we're done
        return output;

    }
}

