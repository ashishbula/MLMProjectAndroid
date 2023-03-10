// 
// Decompiled by Procyon v0.5.36
// 

package com.paytm.merchant;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import com.paytm.crypto.Encryption;
import com.paytm.crypto.CryptoUtils;
import com.paytm.crypto.EncryptionFactory;
import java.util.TreeMap;

public class CheckSumServiceHelper
{
    private static CheckSumServiceHelper checkSumServiceHelper;
    
    private CheckSumServiceHelper() {
    }
    
    public static String getVersion() {
        return "1.0";
    }
    
    public static CheckSumServiceHelper getCheckSumServiceHelper() {
        if (CheckSumServiceHelper.checkSumServiceHelper == null) {
            CheckSumServiceHelper.checkSumServiceHelper = new CheckSumServiceHelper();
        }
        return CheckSumServiceHelper.checkSumServiceHelper;
    }
    
    public String genrateCheckSum(final String Key, final TreeMap<String, String> paramap) throws Exception {
        final StringBuilder response = CheckSumServiceHelper.checkSumServiceHelper.getCheckSumString(paramap);
        String checkSumValue = null;
        try {
            final Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
            final String randomNo = CryptoUtils.generateRandomString(4);
            response.append(randomNo);
            String checkSumHash = CryptoUtils.getSHA256(response.toString());
            checkSumHash = checkSumHash.concat(randomNo);
            checkSumValue = encryption.encrypt(checkSumHash, Key);
            if (checkSumValue != null) {
                checkSumValue = checkSumValue.replaceAll("\r\n", "");
                checkSumValue = checkSumValue.replaceAll("\r", "");
                checkSumValue = checkSumValue.replaceAll("\n", "");
            }
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        return checkSumValue;
    }
    
    public StringBuilder getCheckSumString(final TreeMap<String, String> paramMap) throws Exception {
        final Set<String> keys = paramMap.keySet();
        final StringBuilder checkSumStringBuffer = new StringBuilder("");
        final TreeSet<String> parameterSet = new TreeSet<String>();
        for (final String key : keys) {
            if (!"CHECKSUMHASH".equalsIgnoreCase(key)) {
                parameterSet.add(key);
            }
        }
        for (final String paramName : parameterSet) {
            String value = paramMap.get(paramName);
            if (value == null || value.trim().equalsIgnoreCase("NULL")) {
                value = "";
            }
            checkSumStringBuffer.append(value.trim()).append("|");
        }
        return checkSumStringBuffer;
    }
    
    public boolean verifycheckSum(final String masterKey, final TreeMap<String, String> paramap, final String responseCheckSumString) throws Exception {
        boolean isValidChecksum = false;
        final StringBuilder response = CheckSumServiceHelper.checkSumServiceHelper.getCheckSumString(paramap);
        final Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
        final String responseCheckSumHash = encryption.decrypt(responseCheckSumString, masterKey);
        final String randomStr = getLastNChars(responseCheckSumHash, 4);
        final String payTmCheckSumHash = this.calculateRequestCheckSum(randomStr, response.toString());
        if (responseCheckSumHash != null && payTmCheckSumHash != null && responseCheckSumHash.equals(payTmCheckSumHash)) {
            isValidChecksum = true;
        }
        return isValidChecksum;
    }
    
    private String calculateRequestCheckSum(final String randomStr, final String checkSumString) throws Exception {
        final String reqCheckSumValue = checkSumString;
        String checkSumHash = CryptoUtils.getSHA256(reqCheckSumValue.concat(randomStr));
        checkSumHash = checkSumHash.concat(randomStr);
        return checkSumHash;
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
