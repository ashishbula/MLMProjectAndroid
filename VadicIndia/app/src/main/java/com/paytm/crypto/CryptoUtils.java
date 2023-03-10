// 
// Decompiled by Procyon v0.5.36
// 

package com.paytm.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.security.MessageDigest;

public class CryptoUtils
{
    public static String getHashFromSHA(final String value) throws Exception {
        String hashValue = "";
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        hashValue = byteArray2Hex(messageDigest.digest(value.getBytes()));
        return hashValue;
    }
    
    private static String byteArray2Hex(final byte[] hash) {
        final Formatter formatter = new Formatter();
        for (final byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    public static String generateRandomString(final int length) {
        final String ALPHA_NUM = "9876543210ZYXWVUTSRQPONMLKJIHGFEDCBAabcdefghijklmnopqrstuvwxyz!@#$&_";
        final StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final int ndx = (int)(Math.random() * ALPHA_NUM.length());
            sb.append(ALPHA_NUM.charAt(ndx));
        }
        return sb.toString();
    }
    
    public static String getSHA256(final String value) throws SecurityException {
        String hashValue = "";
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hashValue = byteArray2Hex(messageDigest.digest(value.getBytes()));
        }
        catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getMessage(), e);
        }
        return hashValue;
    }
    
    public static String getLastNChars(final String inputString, final int subStringLength) {
        if (inputString == null || inputString.length() <= 0) {
            return "";
        }
        final int length = inputString.length();
        if (length <= subStringLength) {
            return inputString;
        }
        final int startIndex = length - subStringLength;
        return inputString.substring(startIndex);
    }
}
