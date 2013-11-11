/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *class use for generate digest of string using MD5 algorithm
 * @author Ashok
 */
public class Digest {

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.trim().isEmpty();
    }

    public static String md5(String message) {
        byte[] bytes = message.getBytes();
        final StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(bytes);
            byte[] result = digest.digest();
            for (byte b : result) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}
