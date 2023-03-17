package cosc202.andie;

public class Language {
    static String language = "en";

    public static void setLanguage(String input) {
        language = input;

    }

    public static String translate(String input) {
        
        if (language == "fr") {
            if (input == "File")
                return "Fichier";
            if (input == "Edit")
                return "Édition";
            if (input == "View")
                return "Affichage";
            if (input == "Filter")
                return "Filtre";
            if (input == "Image")
                return "Image";
            if (input == "Colour")
                return "Couleur";
            if (input == "Settings")
                return "Paramètres";
            if (input == "Open")
                return "Ouvrir";
            if (input == "Open Default")
                return "Ouvrir défaut";
            if (input == "Save")
                return "Enregistrer";
            if (input == "Save As")
                return "Enregistrer sous";
            if (input == "Exit")
                return "Quitter";
            if (input == "Undo")
                return "Annuler";
            if (input == "Redo")
                return "Rétablir";
            if (input == "Zoom In")
                return "Zoom avant";
            if (input == "Zoom Out")
                return "Zoom arrière";
            if (input == "Zoom Full")
                return "Zoom pleine page";
            if (input == "Mean Filter")
                return "Filtre de la moyenne";
            if (input == "Soft Blur")
                return "Flou doux";
            if (input == "Sharpen Filter")
                return "Filtre de netteté";
            if (input == "Gaussian Blur")
                return "Flou gaussien";
            if (input == "Median Filter")
                return "Filtre de médiane";
            if (input == "Rotate")
                return "Rotation";
            if (input == "Resize")
                return "Redimensionner";
            if (input == "Greyscale")
                return "Niveau de gris";
            if (input == "Brightness")
                return "Luminosité";
            if (input == "Language")
                return "Langue";
        }
        if (language == "en") {
            return input;
        }
        return "";
    }
}

