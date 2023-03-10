// 
// Decompiled by Procyon v0.5.36
// 

package com.paytm.crypto;

public class EncryptionFactory
{
    private EncryptionFactory() {
    }
    
    public static Encryption getEncryptionInstance(final String algorithmType) {
        Encryption encryption = null;
        encryption = new AesEncryption();
        return encryption;
    }
}
