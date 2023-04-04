package cosc202.andie;

import java.util.Scanner;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Language {

    public static String language = "en";
    static int getNumTexts = getNumTexts();
    static int getNumLanguages = getNumLanguages();

    public static String[][] languageArray = new String[getNumLanguages][getNumTexts];

    /**
     * </p>
     * Sets the value of language to what was input in the parameter.
     * This causes the value of language to globally change and after a restart all
     * texts will change
     * </p>
     * 
     * @param input The type of language being changed to
     */
    public static void setLanguage(String input) {
        language = input;
    }

    /**
     * </p>
     * Gets the number of texts changes in 'LanguageList' by scanning in the file,
     * and counting the number of lines
     * in the text file by using a while loop. Returns 0 if a file cannot be found.
     * </p>
     * 
     * @return Counter the number of lines in the text file
     */
    public static int getNumTexts() {

        int counter = 0;
        URL languageList = Language.class.getClassLoader().getResource("LanguageList");
        try {
            Scanner sc = new Scanner(new File(languageList.toURI()));
            while (sc.hasNextLine()) {
                counter++;
                sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException | URISyntaxException e) {
            return 0;
        }
        return counter;
    }

    /**
     * </p>
     * Gets the number of languages in the language array in Andie using length
     * </p>
     * 
     * @return Andie.languages.length the length of languages
     */
    public static int getNumLanguages() {
        return Andie.languages.length;
    }

    /**
     * </p>
     * Creates a new 2D array called languageArray and scans the 'LanguageList'. The
     * contents of LanguageList are put into a 2D array,
     * where a row is a language and a column is the set of all possible texts in
     * that language. After languageArray is filled, uses a switch
     * to check which language will be implemented, using input, and changes
     * language.
     * </p>
     * 
     * @param input the type of text being changed to
     * @return Andie.languages.length the length of languages
     */
    public static String translate(String input) {

        URL languageList = Language.class.getClassLoader().getResource("LanguageList");
        try {
            if (languageArray[0][0] == null) {
                System.out.println("Not full");
                Scanner sc = new Scanner(new File(languageList.toURI()));
                int counter = 0;
                while (sc.hasNextLine()) {
                    String[] arr = sc.nextLine().split(", ");
                    for (int i = 0; i < arr.length; i++) {
                        languageArray[i][counter] = arr[i];
                    }
                    counter++;
                }
                sc.close();
            }
            System.out.println("Full");
        } catch (FileNotFoundException | URISyntaxException e) {
            return "No Language";
            // e.printStackTrace();
        }
        // switch case that checks what language is used, and scrolls through that row
        // to check if a text matches the input
        // if the text matched then it will replace it with the correct language
        // translation

        switch (language) {
            case "en":
                for (int i = 0; i < languageArray[0].length; i++) {
                    if (input.equals(languageArray[0][i])) {
                        return languageArray[0][i];
                    }
                }
                break;
            case "fr":
                for (int i = 0; i < languageArray[1].length; i++) {
                    if (input.equals(languageArray[0][i])) {
                        return languageArray[1][i];
                    }
                }
                break;
            case "my":
                for (int i = 0; i < languageArray[2].length; i++) {
                    if (input.equals(languageArray[0][i])) {
                        return languageArray[2][i];
                    }
                }

        }
        return "";
    }
}
