package ar.com.laboratory.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for AES 256 encryption and decryption.
 *
 * \@author Maximiliano R Soria
 */
@ApplicationScoped
public class AES256EncrypterUtil {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16;
    private static final int GCM_IV_LENGTH = 12;
    private static final int AES_KEY_SIZE = 256;

    /**
     * Generates a secret AES key.
     *
     * @return the generated SecretKey
     * @throws Exception if an error occurs during key generation
     */
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey();
    }

    /**
     * Encrypts a string using AES 256.
     *
     * @param plainText the plain text to encrypt
     * @param secretKey the secret key for encryption
     * @return the encrypted text in Base64 encoding
     * @throws Exception if an error occurs during encryption
     */
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] encryptedWithIv = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, encryptedWithIv, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedWithIv);
    }

    /**
     * Decrypts a string using AES 256.
     *
     * @param encryptedText the encrypted text in Base64 encoding
     * @param secretKey the secret key for decryption
     * @return the decrypted plain text
     * @throws Exception if an error occurs during decryption
     */
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedText);
        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] encryptedBytes = new byte[encryptedWithIv.length - GCM_IV_LENGTH];
        System.arraycopy(encryptedWithIv, 0, iv, 0, iv.length);
        System.arraycopy(encryptedWithIv, iv.length, encryptedBytes, 0, encryptedBytes.length);
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}