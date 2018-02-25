package cn.edu.nju.cocoelf.code_elf_back_end.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtil {

    private static final int TIME_LIMIT = 60 * 60 * 1000;

    private static Map<String, String> signatureMap = new TreeMap<>();

    private static Map<String, Long> timeMap = new TreeMap<>();

    public static String getSignature(String email) throws NoSuchAlgorithmException {
        Long currentTime = System.currentTimeMillis();
        Long activeTime = currentTime + TIME_LIMIT;
        String signature = toMd5String(email + activeTime);

        if (signatureMap.containsKey(email)) {
            signatureMap.remove(email);
        }
        signatureMap.put(email, signature);
        timeMap.put(signature, activeTime);

        return signature;
    }

    public static String getEmail(String signature) {
        String email = "";
        for (Map.Entry<String, String> entry : signatureMap.entrySet()) {
            if (entry.getValue().equals(signature)) {
                email = entry.getKey();
                break;
            }
        }
        return email;
    }

    public static String updateSignature(String email, String signature) throws NoSuchAlgorithmException {
        if (signatureMap.containsKey(email)) {
            signatureMap.remove(email);
        }
        if (timeMap.containsKey(signature)) {
            timeMap.remove(signature);
        }

        Long currentTime = System.currentTimeMillis();
        Long activeTime = currentTime + TIME_LIMIT;
        String newSignature = toMd5String(email + activeTime);

        signatureMap.put(email, signature);
        timeMap.put(signature, activeTime);

        return newSignature;
    }

    public static String toMd5String(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        StringBuilder builder = new StringBuilder();
        byte[] cipherData = md5.digest(plainText.getBytes());
        for (byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }
        return builder.toString();
    }
}
