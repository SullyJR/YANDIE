package test.cosc202.andie;

import java.awt.Desktop.Action;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.MacroRecorder;

public class MacroRecorderTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void getInitialMacro() {
        MacroRecorder macro = new MacroRecorder();
        Assertions.assertFalse(macro.isRecording());
    }

    @Test
    void getMacroOn() {
        MacroRecorder macro = new MacroRecorder();
        macro.startRecording();
        Assertions.assertTrue(macro.isRecording());
    }

    @Test
    void getMacroOff() {
        MacroRecorder macro = new MacroRecorder();
        macro.stopRecording();
        Assertions.assertFalse(macro.isRecording());
    }
}