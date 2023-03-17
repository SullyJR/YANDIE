package cosc202.andie;

public class Language {
    static String language = "en";

    public static void setLanguage(String input) {
        language = input;

    }

    public static String translate(String input) {
        
        String[] enValues = {"File", "Edit", "View", "Filter", "Image", "Colour", "Settings",
        "Open", "Open Default", "Save", "Save As", "Exit", "Undo", "Redo", "Zoom In", "Zoom Out", "Zoom Full",
        "Mean Filter", "Soft Blur", "Sharpen Filter", "Gaussian Blur", "Median Filter", "Rotate", "Resize", "Greyscale",
        "Brightness", "Language"};

        String[] frValues = {"Fichier", "Édition", "Affichage", "Filtre", "Image", "Couleur", "Paramètres",
        "Ouvrir", "Ouvrir défaut", "Enregistrer", "Enregistrer sous", "Quitter", "Annuler", "Rétablir",
        "Zoom avant", "Zoom arrière", "Zoom pleine page", "Filtre de la moyenne", "Flou doux", "Filtre de netteté",
        "Flou gaussien", "Filtre de médiane", "Rotation", "Redimensionner", "Niveau de gris", "Luminosité",
        "Langue"};

        String[] myValues = {"Fail", "Sunting", "Paparan", "Penapis", "Imej", "Warna", "Tetapan",
        "Buka", "Simpan", "Simpan sebagai", "Keluar", "Undo", "Redo", "Zoom Masuk", "Zoom Keluar", "Zoom Penuh",
        "Min", "Lembut", "Tajamkan", "Gaussian", "Median", "Putar", "Ukur semula", "Skala kelabu",
        "Kecerahan", "Bahasa"};

        if (language.equals("en")) {
            for(int i = 0; i < enValues.length; i++){
                if(input.equals(enValues[i])){
                    return enValues[i];
                }
            }
        }else if (language.equals("fr")) {
            for(int i = 0; i < frValues.length; i++){
                if(input.equals(enValues[i])){
                    return frValues[i];
                }
            }
        }else if (language.equals("my")) {
            for(int i = 0; i < myValues.length; i++){
                if(input.equals(enValues[i])){
                    return myValues[i];
                }
            }
        }
        return "";
    }
}

