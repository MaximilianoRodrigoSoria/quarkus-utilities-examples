package ar.com.laboratory.ultil;

import ar.com.laboratory.util.AES256EncrypterUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AES256EncrypterUtilTest {

    @InjectMocks
    private AES256EncrypterUtil aes256EncrypterUtil;

    @Mock
    @ConfigProperty(name = "aes.256.key")
    private String aes_key;

    private String plainText;
    private String encryptedText;

    @BeforeEach
    void setUp() {
        aes_key = "your-base64-encoded-key-here"; // Asegúrate de que este sea un valor válido para tu clave AES

        plainText = "Hello, World!";
    }

    @Test
    void testEncryptAndDecrypt() throws Exception {
        // Generar la clave AES a partir de la clave codificada en Base64
        byte[] decodedKey = Base64.getDecoder().decode(aes_key);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        // Mockear la generación de la clave
        when(aes256EncrypterUtil.generateAESKey()).thenReturn(secretKey);

        // Probar el método de encriptación
        encryptedText = aes256EncrypterUtil.encrypt(plainText);
        System.out.println("Texto Encriptado: " + encryptedText);

        // Probar el método de desencriptación
        String decryptedText = aes256EncrypterUtil.decrypt(encryptedText);
        System.out.println("Texto Desencriptado: " + decryptedText);

        // Verificar que el texto desencriptado sea igual al texto original
        assertEquals(plainText, decryptedText);
    }
}
