import com.liruisecond.liruisecond.utils.RSAEncryptorDemo;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSATest {

    @Test
    public void test_RSA() throws Exception {
        String s = "{\"name\": \"长征\"}";
        KeyPair keyPair = RSAEncryptorDemo.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("公钥" + publicKey);
        System.out.println("私钥" + privateKey);
        String encrypt = RSAEncryptorDemo.encrypt(s, keyPair.getPublic());
        System.out.println("encrypted: " + encrypt);
        System.out.println("decoded: " + RSAEncryptorDemo.decrypt(encrypt, privateKey));

    }
}
