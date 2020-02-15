import ciphers.Cipher;
import ciphers.impl.CesarCipher;

public class Test {

    public static void main(String[] args) {
        Cipher cesarCipher = new CesarCipher();
        String example = cesarCipher.encode("djsdwejdewjdiewioejdoewjdowk3i3903eejeje");
        System.out.println(example);
        String decoded = cesarCipher.decode("rrnjeknkeklewmkledmlwkldwljuwel");
        System.out.println(decoded);
    }
}


