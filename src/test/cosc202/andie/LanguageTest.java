package test.cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.Language;

public class LanguageTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void getInitialNumLanguagesValue() {
        Language language = new Language();
        Assertions.assertEquals(3, language.getNumLanguages());
    }

    @Test
    void getInitialNumTextsValue() {
        Assertions.assertEquals(93, Language.getNumTexts());
    }

    @Test
    void getInitialLanguageValue() {
        Language.setLanguage("en");
        Assertions.assertEquals("en", Language.language);
    }

    @Test
    void getLanguageAfterSetLanguage() {
        Assertions.assertEquals("en", Language.language);
        Language.setLanguage("my");
        Assertions.assertEquals("my", Language.language);
        Language.setLanguage("en");
    }
}
