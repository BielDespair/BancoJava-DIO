package com.leonel.banco.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final String ALGORITHM = "SHA-256";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String hashPassword(String senha) {
        try {
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(salt);
            byte[] hash = digest.digest(senha.getBytes());

            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashBase64 = Base64.getEncoder().encodeToString(hash);

            return hashBase64 + ":" + saltBase64;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String senha, String hash) {
        try {
            String[] hashes = hash.split(":");
            String saltBase64 = hashes[1];

            byte[] saltBytes = Base64.getDecoder().decode(saltBase64); // Convertendo de String para Bytes novamente

            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);


            digest.update(saltBytes); // Hasheando senha utilizando o mesmo salt

            byte[] hashSenha = digest.digest(senha.getBytes()); // Hash da senha informada
            String hashSenhaBase64 = Base64.getEncoder().encodeToString(hashSenha);
            String hashFinal = hashSenhaBase64 + ":" + saltBase64;

            return hashFinal.equals(hash);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
