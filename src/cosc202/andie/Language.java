package cosc202.andie;

public class Language {
static String language = "en";

    public static void setLanguage(String input){
        language = input;
    
    }


    public static String translate(String input){
        if(language == "fr"){
            if(input == "Greyscale")
            return "Hello please work :))!";
        }
        if(language == "en"){
            return input;
        }
        return"";
    }
}


