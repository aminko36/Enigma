package ciphers.impl;

import ciphers.Cipher;

public class Root13Cipher implements Cipher {

    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final int ROOOT_DISPLACEMENT = 13;

    @Override
    public String encode(String textToEncode) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < textToEncode.length(); i++) {
            char sign = textToEncode.charAt(i);
            if (isIncluded(sign)) {
                boolean isLowerCase = Character.isLowerCase(sign);
                int indexInAlphabet = ALPHABET.indexOf(Character.toLowerCase(sign));
                int indexAfterDisplacement = (indexInAlphabet + ROOOT_DISPLACEMENT) % ALPHABET.length();
                if (isLowerCase) {
                    stringbuilder.append(ALPHABET.charAt(indexAfterDisplacement));
                } else {
                    stringbuilder.append(Character.toUpperCase(ALPHABET.charAt(indexAfterDisplacement)));
                }
            } else {
                stringbuilder.append(sign);

            }
        }
        return stringbuilder.toString();
    }

    @Override
    public String decode(String textToDecode) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < textToDecode.length(); i++) {
            char sign = textToDecode.charAt(i);
            if (isIncluded(sign)) {
                boolean isLowerCase = Character.isLowerCase(sign);
                int indexInAlphabet = ALPHABET.indexOf(Character.toLowerCase(sign));
                int indexAfterDisplacement = (indexInAlphabet + ALPHABET.length() - ROOOT_DISPLACEMENT) % ALPHABET.length();
                if (isLowerCase) {
                    stringbuilder.append(ALPHABET.charAt(indexAfterDisplacement));
                } else {
                    stringbuilder.append(Character.toUpperCase(ALPHABET.charAt(indexAfterDisplacement)));
                }
            } else {
                stringbuilder.append(sign);
            }
        }
        return stringbuilder.toString();
    }

    private boolean isIncluded(char sign) {
        return ALPHABET.contains(Character.toString(sign).toLowerCase());
    }
}


