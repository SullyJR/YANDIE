package cosc202.andie;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;

public class MacroRecorder {

    private boolean macroOn = false;

    private List<ImageOperation> actions = new ArrayList<>();

    public MacroRecorder() {
     // this.actions = actions;
      startRecording();
    }

    public void add(ImageOperation action) {
        if(macroOn){
            actions.add(action);
            System.out.println(actions.toString());
        }else{
            System.out.println("Macro is not on");
        }

        
    }

    public void startRecording() {
        macroOn = true;
    }

    public void stopRecording() {
        macroOn = false;
    }

    public boolean isRecording() {
        return macroOn;
    }

    public List<ImageOperation> getActions() {
        return actions;
    }

}
