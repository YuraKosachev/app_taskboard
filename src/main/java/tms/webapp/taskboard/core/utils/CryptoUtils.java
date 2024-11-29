package tms.webapp.taskboard.core.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CryptoUtils {


    public static byte[] PBKDF2hash(String src) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(src.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }

//    public static byte[] SHA512(String src, String key) throws NoSuchAlgorithmException {
////        SecureRandom random = new SecureRandom();
////        byte[] salt = new byte[16];
////        random.nextBytes(salt);
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        md.update(key.getBytes(StandardCharsets.UTF_8));
//        return md.digest(src.getBytes(StandardCharsets.UTF_8));
//    }

    public static byte[] SHA512(String src, String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(key.getBytes(StandardCharsets.UTF_8));
            return md.digest(src.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SHA512ToString(byte[] src) {
        BigInteger no = new BigInteger(1, src);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }


    public static String encryptSHA512String(String input, String key) {
        return SHA512ToString(SHA512(input, key));

    }

    public static String encrypt(String data, String key) {
        byte[] base64 = Base64.getEncoder().encode(encrypt(key.getBytes(), data.getBytes()));
        return new String(base64);
    }

    public static String decrypt(String data, String key) {
        return new String(encrypt(key.getBytes(), Base64.getDecoder().decode(data)));
    }

    private static byte[] encrypt(byte[] key, byte[] data) {
        return encryptOutput(key, data);
    }

    private static List<Byte> encryptInitalize(byte[] key) {
        List<Byte> array = IntStream.range(0, 256).boxed().map(Integer::byteValue).collect(Collectors.toList());
        int i1 = 0;
        int j = 0;
        for (; i1 < 256; ++i1) {
            j = j + (int) key[i1 % key.length] + (int) array.get(i1) & (int) Byte.MAX_VALUE;
            swap(array, i1, j);
        }
        return array;
    }

    private static byte[] encryptOutput(byte[] key, byte[] data) {
        List<Byte> s = encryptInitalize(key);
        int i = 0;
        int j = 0;
        byte[] result = new byte[data.length];
        for (int it = 0; it < data.length; it++) {
            i = i + 1 & (int) Byte.MAX_VALUE;
            j = j + (int) s.get(i) & (int) Byte.MAX_VALUE;
            swap(s, i, j);
            byte byteResult = (byte) (Byte.toUnsignedInt(data[it]) ^ Byte.toUnsignedInt(s.get((int) s.get(i) + (int) s.get(j) & (int) Byte.MAX_VALUE)));
            result[it] = byteResult;
        }

        return result;
    }

    private static void swap(List<Byte> s, int i, int j) {
        byte num = s.get(i);
        s.set(i, s.get(j));
        s.set(j, num);
    }
}

