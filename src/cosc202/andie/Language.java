package cosc202.andie;

import java.util.Scanner;
import java.io.*;

public class Language {
    static String language = "en";
    static int numTexts = 51;

    public static void setLanguage(String input) {
        language = input;
    }

    public static int getNumTexts() {
        return numTexts;

    }

    public static String translate(String input) {

        String[][] languageArray = new String[Andie.getNumLanguages()][getNumTexts()];
        try (Scanner sc = new Scanner(new File("LanguageList"))) {
            int counter = 0;
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(", ");
                for (int i = 0; i < arr.length; i++) {
                    languageArray[i][counter] = arr[i];
                }
                counter++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        switch(language){
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
                break;
        }
        // if (language.equals("en")) {
        //     for (int i = 0; i < languageArray[0].length; i++) {
        //         if (input.equals(languageArray[0][i])) {
        //             return languageArray[0][i];
        //         }
        //     }
        // } else if (language.equals("fr")) {
        //     for (int i = 0; i < languageArray[1].length; i++) {
        //         if (input.equals(languageArray[0][i])) {
        //             return languageArray[1][i];
        //         }
        //     }
        // } else if (language.equals("my")) {
        //     for (int i = 0; i < languageArray[2].length; i++) {
        //         if (input.equals(languageArray[0][i])) {
        //             return languageArray[2][i];
        //         }
        //     }
        // }
        return "";
    }
}
