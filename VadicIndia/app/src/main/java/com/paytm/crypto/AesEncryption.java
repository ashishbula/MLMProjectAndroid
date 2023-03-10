// 
// Decompiled by Procyon v0.5.36
// 

package com.paytm.crypto;

//import com.google.android.gms.common.util.Base64Utils;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;


public class AesEncryption implements Encryption
{
    //private final Base64.Encoder base64Encoder = new Base64.Encoder();
    //private final Base64.Decoder base64Decoder = new Base64.Decoder();
    private final byte[] ivParamBytes;
    
    public AesEncryption() {
        ivParamBytes = new byte[] { 64, 64, 64, 64, 38, 38, 38, 38, 35, 35, 35, 35, 36, 36, 36, 36 };
    }
    
    @Override
    public String encrypt(final String toEncrypt, final String key) throws Exception {
        String encryptedValue = "";
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");//"SunJCE"
        cipher.init(1, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(this.ivParamBytes));
        encryptedValue = Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes()));
        return encryptedValue;
    }
    
    @Override
    public String decrypt(final String toDecrypt, final String key) throws Exception {
        String decryptedValue = "";
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(2, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(this.ivParamBytes));
        decryptedValue = new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
        return decryptedValue;
    }
}
