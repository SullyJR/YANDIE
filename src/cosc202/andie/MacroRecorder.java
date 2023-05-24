package cosc202.andie;

import java.util.Stack;

/**
 * Records a macro of all the actions of an editable image and saved
 * it into a stack. This recorder can reproduce the commands onto other files.
 */
public class MacroRecorder implements java.io.Serializable {

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
        } else {
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
        } else {
        }

    }

    /**
     * If macro is on, boolean true
     */
    public void startRecording() {
        macroOn = true;
    }

    /**
     * If macro is on, boolean off
     */
    public void stopRecording() {
        macroOn = false;
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
    public Stack<ImageOperation> getActions() {
        return actions;
    }
}
