package cosc202.andie;

public class Language {
    static String language = "en";

    public static void setLanguage(String input) {
        language = input;

    }

    public static String translate(String input) {
        if (language == "fr") {
            if (input == "File")
                return "Fishier";
            if (input == "Edit")
                return "Modifier";
            if (input == "View")
                return "Affichage";
            if (input == "Filter")
                return "Le Filtre";
            if (input == "Image")
                return "Hello please work :))!";
            if (input == "Colour")
                return "Hello please work :))!";
            if (input == "Settings")
                return "Hello please work :))!";

        }
        if (language == "en") {
            return input;
        }
        return "";
    }
}
