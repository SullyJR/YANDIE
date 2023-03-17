package cosc202.andie;

public class Language {
    static String language = "en";

    public static void setLanguage(String input) {
        language = input;

    }

    public static String translate(String input){
        if(language == "en"){
            return input;
        }

        if(language == "fr"){
            if(input == "Greyscale")
            return "";
            if(input == "Brightness")
            return "";
            if(input == "Zoom in")
            return "";
            if(input == "Zoom out")
            return "";
            if(input == "Open")
            return "";
        }

        
        return "Error: Language not Found"; //If it gets through the whole code without finding a match, return this (should never happen)
    }
}
