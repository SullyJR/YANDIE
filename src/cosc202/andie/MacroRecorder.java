package cosc202.andie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.Action;

public class MacroRecorder {

    private boolean macroOn = false;

    private Stack<ImageOperation> actions = new Stack<ImageOperation>();

    public MacroRecorder() {
     // this.actions = actions;
      startRecording();
    }

    public void add(ImageOperation action) {
        if(macroOn){
            actions.push(action);
            System.out.println("This is redo: " + actions);
        }else{
            System.out.println("Macro is not on");
        }

        
    }

    public void delete() {
        if(macroOn){
            if(actions != null){
                actions.pop();
            }
            
            System.out.println("This is undo: " + actions);
        }else{
            System.out.println("Macro is not on");
        }

        
    }


    public void startRecording() {
        macroOn = true;
        System.out.println("Macro is on");
    }

    public void stopRecording() {
        macroOn = false;
        System.out.println("Macro is off");
    }

    public boolean isRecording() {
        return macroOn;
    }

    public List<ImageOperation> getActions() {
        return actions;
    }

}
