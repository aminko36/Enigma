package factories;

import ciphers.Cipher;

public interface Factory {
   Cipher create(String type);

}
