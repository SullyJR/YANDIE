package cosc202.andie;

import java.util.List;
import java.util.Stack;

/**
 * Records a macro of all the actions of an editable image and saved
 * it into a stack. This recorder can reproduce the commands onto other files.
 */
public class MacroRecorder {

    /** Boolean to check if macro recorder is on */
    private boolean macroOn;
    /** Initialisation of actions into a stack */
    private Stack<ImageOperation> actions = new Stack<ImageOperation>();

    /** MacroRecorder constructor */
    public MacroRecorder() {
        // this.actions = actions;
        macroOn = false;
    }

    /**
     * When the macro recorder is on, and actions are completed, the
     * add method records the actions and places them into the stack.
     * If the macro is not on, doesn't place inside the stack.
     * 
     * @param action the stack of actions for the macro
     */
    public void add(ImageOperation action) {
        if (macroOn) {
            actions.push(action);
            System.out.println("This is redo: " + actions);
        } else {
            System.out.println("Macro is not on");
        }

    }

    /**
     * When the macro deletes the macro actions from the stack by
     * popping them off. It resets the stack for a new stack of actions.
     */
    public void delete() {
        if (macroOn) {
            if (actions != null) {
                actions.pop();
            }

            System.out.println("This is undo: " + actions);
        } else {
            System.out.println("Macro is not on");
        }

    }

    /**
     * If macro is on, boolean true
     */
    public void startRecording() {
        macroOn = true;
        System.out.println("Macro is on");
    }

    /**
     * If macro is on, boolean off
     */
    public void stopRecording() {
        macroOn = false;
        System.out.println("Macro is off");
    }

    /**
     * Accessor for macro recording, checks if recording
     * 
     * @return value of macroOn
     */
    public boolean isRecording() {
        return macroOn;
    }

    /**
     * Accessor for the macro stack
     * 
     * @return gets stack of actions
     */
    public List<ImageOperation> getActions() {
        return actions;
    }
}
