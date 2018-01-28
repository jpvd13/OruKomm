package orukomm.logic.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/*
 * Salting and SHA-256 hashing methods for password encryption.
 */
public class Encryption {

	private static final int ITERATIONS = 1000;
	private static final String algorithm = "SHA-256";

	public static String generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] bSalt = new byte[10];
		sr.nextBytes(bSalt);
		
		return Base64.getEncoder().encodeToString(bSalt);
	}
	
	public static String generatePasswordHash(String password, String salt) {
		String passwordHash = "";
		byte[] bSalt = salt.getBytes(StandardCharsets.UTF_8);
		
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.reset();
			
			// Pass the salt to the digest for the computation.
			digest.update(bSalt);
			byte[] bDigest = digest.digest(password.getBytes("UTF-8"));

			// Iterate to make brute-force computation more time consuming.
			for (int i = 0; i < ITERATIONS; i++) {
				digest.reset();
				bDigest = digest.digest(bDigest);
			}

			// Convert the digest and salt to base64.
			passwordHash = Base64.getEncoder().encodeToString((bDigest));
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
		}

		return passwordHash;
	}
}