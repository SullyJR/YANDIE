package test.cosc202.andie;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.SelectActions;
import cosc202.andie.ImagePanel;

public class SelectActionsTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void getInitialColourSelected() {
        ImagePanel ip = new ImagePanel(null);
        try {
            SelectActions sa = new SelectActions(ip.imagePanel);
            Assertions.assertEquals(sa.getColour(), Color.WHITE);

        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertEquals(1, 0);
        }

    }
}
