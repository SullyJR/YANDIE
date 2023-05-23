package test.cosc202.andie;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.ImagePanel;

public class ImagePanelTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void getZoomInitialValue() {
        ImagePanel testPanel = new ImagePanel(null);
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }

    @Test
    void getZoomAftersetZoom() {
        ImagePanel testPanel = new ImagePanel(null);
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom() == 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }

    @Test
    void getIconLengthArray() {
        ImagePanel testPanel = new ImagePanel(null);
        ImageIcon[] ip = testPanel.iconArray;
        Assertions.assertEquals(ip.length, 30);
    }

    @Test
    void getInitialToggles() {
        ImagePanel testPanel = new ImagePanel(null);
        Assertions.assertFalse(testPanel.rectToggled());
        Assertions.assertFalse(testPanel.cirToggled());
        Assertions.assertFalse(testPanel.drawToggled());
        Assertions.assertFalse(testPanel.lineToggled());
    }

    @Test
    void getActivatedToggles() {
        ImagePanel testPanel = new ImagePanel(null);
        testPanel.activateRect();
        testPanel.activateCir();
        testPanel.activateDraw();
        testPanel.activateLine();
        Assertions.assertTrue(testPanel.rectToggled());
        Assertions.assertTrue(testPanel.cirToggled());
        Assertions.assertTrue(testPanel.drawToggled());
        Assertions.assertTrue(testPanel.lineToggled());
    }
}
