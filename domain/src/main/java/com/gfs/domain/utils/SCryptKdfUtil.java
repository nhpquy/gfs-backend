package com.gfs.domain.utils;

import com.lambdaworks.crypto.SCrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class SCryptKdfUtil {

    private static final String SCRYPT_KDF_PREFIX = "scrypt";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final byte VERSION = 0;
    private static final byte logN = 16;
    private static final byte r = 8;
    private static final byte p = 1;

    private static byte[] sha256(byte[] bytes) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(bytes);
    }

    private static boolean compareByteArray(byte[] b1, byte[] b2, int offset, int length) {
        for (int i = offset; i < length; ++i)
            if (b1[i] != b2[i])
                return false;
        return true;
    }

    private static byte[] subByteArray(byte[] bytes, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(bytes, offset, result, 0, length);
        return result;
    }

    public static String encode(String rawPassword) throws Exception {

        byte[] result = new byte[96];

        byte[] prefix = SCRYPT_KDF_PREFIX.getBytes(DEFAULT_CHARSET);

        System.arraycopy(prefix, 0, result, 0, prefix.length);
        result[6] = VERSION;
        result[7] = logN;
        result[8] = 0;
        result[9] = 0;
        result[10] = 0;
        result[11] = r;
        result[12] = 0;
        result[13] = 0;
        result[14] = 0;
        result[15] = p;

        byte[] salt = new byte[32];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
        System.arraycopy(salt, 0, result, 16, salt.length);

        byte[] prefix48 = subByteArray(result, 0, 48);
        byte[] sha256 = sha256(prefix48);
        System.arraycopy(sha256, 0, result, 48, 16);

        int N = 65536;
        byte[] key = SCrypt.scrypt(rawPassword.getBytes("UTF-8"), salt, N, r, p, 64);
        byte[] prefix64 = subByteArray(result, 0, 64);
        byte[] hmacSha256 = HMACSHA256.sign(subByteArray(key, 32, key.length - 32), prefix64);
        System.arraycopy(hmacSha256, 0, result, 64, 32);

        return Base64.getEncoder().encodeToString(result);
    }

    public static boolean verify(String rawPassword, String hashed) throws Exception {

        byte[] decoded = Base64.getDecoder().decode(hashed.getBytes("UTF-8"));

        String prefix = new String(decoded, 0, 6, "UTF-8");

        byte version = decoded[6];

        byte log2N = decoded[7];

        int r = decoded[8] << 24 | decoded[9] << 16 | decoded[10] << 8 | decoded[11];

        int p = decoded[12] << 24 | decoded[13] << 16 | decoded[14] << 8 | decoded[15];

        byte[] salt = subByteArray(decoded, 16, 32);
        byte[] checksum = subByteArray(decoded, 48, 16);
        byte[] hmac = subByteArray(decoded, 64, 32);

        if (!SCRYPT_KDF_PREFIX.equals(prefix))
            return false;


        byte[] prefix48 = subByteArray(decoded, 0, 48);
        byte[] sha256 = sha256(prefix48);
        if (!compareByteArray(checksum, sha256, 0, 16))
            return false;


        int N = (int) Math.pow(2, log2N);
        byte[] key = SCrypt.scrypt(rawPassword.getBytes("UTF-8"), salt, N, r, p, 64);
        byte[] prefix64 = subByteArray(decoded, 0, 64);
        byte[] hmacSha256 = HMACSHA256.sign(subByteArray(key, 32, key.length - 32), prefix64);

        return compareByteArray(hmac, hmacSha256, 0, 32);
    }
}
