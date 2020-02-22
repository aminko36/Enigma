package ciphers;

import ciphers.impl.Root13Cipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Root13Test {
    protected final String NO_ALPHABETIC_SIGNS = "112312[]][";
    protected final String ALPHABETIC_SIGNS = "abcdd";
    protected final String EXPECTED_ENCODE_ALPHABETIC_SIGNS = "nopqq";
    protected final String mixedTest = "123sf//";
    protected final String expectedTextForMixedText = "123fs//";
    protected Cipher root13Cipher = new Root13Cipher();

    @DisplayName("Testing correction on encoding with no alphabetic letters")
    @Test
    public void testIfOnlyAlphabetSignsAreEncoded() {
        String encoded = root13Cipher.encode(NO_ALPHABETIC_SIGNS);
        Assertions.assertEquals(NO_ALPHABETIC_SIGNS, encoded);
    }

    @DisplayName("Testing correction on encoding with alphabetic letters ")
    @Test
    public void testIfEncodingModifyTextWithAlphabeticSigns() {
        String encoded = root13Cipher.encode(ALPHABETIC_SIGNS);
        Assertions.assertEquals(EXPECTED_ENCODE_ALPHABETIC_SIGNS, encoded);
    }

    @DisplayName("Testing correction on decoding with alphabetic letters ")
    @Test
    public void testIfMixAlphabetSignsAreDecoding() {
        String decoded = root13Cipher.decode(NO_ALPHABETIC_SIGNS);
        Assertions.assertEquals(NO_ALPHABETIC_SIGNS, decoded);
    }

    @DisplayName("Testing correction of decode test with mix alphabetic letters")
    @Test
    public void testIfDecodingModifyTextWithAlphabeticSigns() {
        String decode = root13Cipher.decode(mixedTest);
        Assertions.assertEquals(expectedTextForMixedText, decode);
    }
}

