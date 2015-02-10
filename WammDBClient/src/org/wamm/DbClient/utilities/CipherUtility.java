/*
 * This has been modified to split some steps.
 * Original version from: http://techie-experience.blogspot.com/2012/10/encryption-and-decryption-using-aes.html
 */
package org.wamm.DbClient.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class CipherUtility {

	public static byte[] encrypt(byte[] bytesToEncrypt, byte[] key) {
		try
		{
			Cipher cipher = Cipher.getInstance("AES");///ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(bytesToEncrypt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

	public static String encrypt(String strToEncrypt, byte[] key) {
		try
		{
			return Base64.encode(encrypt(strToEncrypt.getBytes(), key));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

	public static byte[] decrypt(byte[] bytesToDecrypt, byte[] key) {
		try
		{
			Cipher cipher = Cipher.getInstance("AES");///ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(bytesToDecrypt);
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;
	}

	public static String decrypt(String strToDecrypt, byte[] key) {
		try
		{
			return new String(decrypt(Base64.decode(strToDecrypt), key));
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;
	}
}
