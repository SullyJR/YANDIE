package cosc202.andie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MacroAction {
    private String type;
    private Map<String, Object> parameters;
    private MacroRecorder recorder;
    
    public MacroAction(MacroRecorder recorder) {
        this.recorder = recorder;
    }

    public MacroAction(String type, Map<String, Object> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    // Getters and setters for type and parameters
    // ...
}



