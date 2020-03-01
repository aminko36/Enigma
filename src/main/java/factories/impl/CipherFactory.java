package factories.impl;

import ciphers.Cipher;
import ciphers.impl.CesarCipher;
import ciphers.impl.Root13Cipher;
import ciphers.impl.VigenereCipher;
import exceptions.CipherNotFoundException;
import factories.Factory;

public class CipherFactory implements Factory {
    public static final String CESAR = "Cipher";
    public static final String ROOT13 = "Root13";
    public static final String VIGENERE = "Vigenere";

    @Override
    public Cipher create(String type) {
        if (type.equals(CESAR)) {
            return new CesarCipher();
        }
        if (type.equals(ROOT13)) {
            return new Root13Cipher();
        }
        if (type.equals(VIGENERE)) {
            return new VigenereCipher();
        }
        throw new CipherNotFoundException(type);
    }
}
