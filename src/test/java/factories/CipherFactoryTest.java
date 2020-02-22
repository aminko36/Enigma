package factories;

import ciphers.Cipher;
import ciphers.impl.CesarCipher;
import exceptions.CipherNotFoundException;
import factories.impl.CipherFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CipherFactoryTest {

    private CipherFactory factory = new CipherFactory();
    private static final String MESSAGE = "Type of cipher is not recognized";

    @Test
    protected void ifCesarInstanceReturnedWithCesarTypeTest() {
        Cipher cipher = factory.create(CipherFactory.CESAR);
        Assertions.assertTrue(cipher instanceof CesarCipher);
    }

    @Test
    protected void ifRoot13CipherInstanceReturnedWithCesarTypeTest() {
        Cipher cipher = factory.create(CipherFactory.ROOT13);
        Assertions.assertTrue(cipher instanceof CesarCipher);
    }

    @Test
    protected void shouldTrowExceptionWithGivenUnknownType() {
        String unknown = "unknown";
        Assertions.assertThrows(CipherNotFoundException.class, () -> factory.create(unknown),
                MESSAGE + unknown);

    }
}
