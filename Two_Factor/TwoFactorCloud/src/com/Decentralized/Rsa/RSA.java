package com.Decentralized.Rsa;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA implements Serializable{
	private static final long serialVersionUID = 1L;
	Cipher cipher;
	SecureRandom random;
	KeyPairGenerator generator ;
	KeyPair pair;
	Key pubKey;
	Key privKey;
	
	public RSA()
	{
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024,random);
			pair = generator.generateKeyPair();
			System.out.println("pair===="+pair);
			pubKey=pair.getPublic();
			System.out.println("Public Key===="+pubKey);
			privKey = pair.getPrivate();
			System.out.println("Private Key===="+privKey);
		} catch (NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] RSA_Enc(String str)
	{
		
		byte[] input = str.getBytes();
		System.out.println(input);
		byte[] cipherText=null;
	
	    //Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	    try {
	    	
			cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
			
			cipherText = cipher.doFinal(input);
			
			
			System.out.println("cipher: " + new String(cipherText));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return cipherText;
		
	}
	public byte[] RSA_Enc(String str,Key pubKey)
	{
		
		byte[] input = str.getBytes();
		System.out.println(input);
		byte[] cipherText=null;
	
	    //Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	    try {
	    	
			cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
			
			cipherText = cipher.doFinal(input);
			
			
			System.out.println("cipher: " + new String(cipherText));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return cipherText;
		
	}
	public String RSA_Dnc(byte[] cipherText)
	{
		byte[] plainText=null;
	    try {
			cipher.init(Cipher.DECRYPT_MODE, privKey);
		    plainText = cipher.doFinal(cipherText);
		    //System.out.println("plain : " + new String(plainText));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(plainText);
		
	}
	public String RSA_Dnc(byte[] cipherText,Key privKey)
	{
		System.out.println("pppp"+cipherText.toString());
		System.out.println("pppp"+cipherText.toString().length());
		byte[] plainText=null;
	    try {
			cipher.init(Cipher.DECRYPT_MODE, privKey);
		    plainText = cipher.doFinal(cipherText);
		    //System.out.println("plain : " + new String(plainText));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(plainText);
		
	}
	
 
}