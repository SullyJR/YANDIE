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
        Assertions.assertEquals(3, Language.getNumLanguages());
    }

    @Test
    void getInitialNumTextsValue() {
        Assertions.assertEquals(94, Language.getNumTexts());
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
