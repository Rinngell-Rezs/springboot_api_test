package com.project.springboot_api_test.helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.UUID;

public class PasswordHelper {
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;
    private static final String SECRET_KEY = "secret_key_example";
    private static final String SALT = "salt_example";

    /*
        AES encryption is not recommended for password storage in production systems,
        this is because AES is a symmetric encryption algorithm,
        meaning the same key is used for both encryption and decryption,
        making it vulnerable if the key is compromised.

        However, for the purpose of this test assignment, we will use AES encryption to demonstrate the concept.
    */
    public static String encrypt(String strToEncrypt) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            byte[] encryptedData = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            // Handle the exception properly
            throw new RuntimeException("Error while encrypting: " + e.toString());
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);

            byte[] iv = new byte[16];
            byte[] cipherText = new byte[decodedData.length - 16];

            System.arraycopy(decodedData, 0, iv, 0, 16);
            System.arraycopy(decodedData, 16, cipherText, 0, cipherText.length);

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(),
                    SALT.getBytes(),
                    ITERATION_COUNT,
                    KEY_LENGTH
            );
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

            return new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting: " + e.toString());
        }
    }

    public static Boolean comparePassword(String plainPassword, String encryptedPassword) {
        return plainPassword != null && plainPassword.equals(decrypt(encryptedPassword));
    }
}
