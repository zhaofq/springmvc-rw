package com.java.spring.util.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author sobranie
 */
public class DESTextCipher implements TextCipher {
	
	
	
	public static DESTextCipher desTextCipher;

	/**-
     * 加密
     */
    private Cipher encryptCipher;

    /**
     * 解密
     */
    private Cipher decryptCipher;

    /**
     * KeyFactory
     */
    private SecretKeyFactory keyFactory;

    public DESTextCipher() {
        try {
            encryptCipher = Cipher.getInstance("DES");
            decryptCipher = Cipher.getInstance("DES");
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (GeneralSecurityException ex) {
      
        }
    }

    @Override
    public void init(String salt) {
        try {
            SecretKey sk = keyFactory.generateSecret(new DESKeySpec(salt.getBytes()));
            encryptCipher.init(Cipher.ENCRYPT_MODE, sk);
            decryptCipher.init(Cipher.DECRYPT_MODE, sk);
        } catch (InvalidKeyException | InvalidKeySpecException ex) {
       
        }
    }

    @Override
    public String encrypt(String value) throws GeneralSecurityException {
        return new String(Base64.encodeBase64(encryptCipher.doFinal(value.getBytes())));
    }

    @Override
    public String decrypt(String value) throws GeneralSecurityException {
        return new String(decryptCipher.doFinal(Base64.decodeBase64(value.getBytes())));
    }   
    
    public static DESTextCipher getDesTextCipher() {
        if(desTextCipher==null){
            desTextCipher =new DESTextCipher();
            desTextCipher.init("CreditCloudRock!");
        }
        return desTextCipher;
    } 
}
