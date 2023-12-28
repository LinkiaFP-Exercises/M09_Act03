package ejercicio01;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Esta clase demuestra el uso de algoritmos de cifrado (3DES, AES, RSA) y la
 * generación de hash (SHA-256) en Java.
 * 
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 28/12/2023
 */
public class Ejercicio01 {

	/**
	 * Método principal que demuestra el cifrado y la generación de hash utilizando
	 * diferentes algoritmos.
	 * 
	 * @param args Los argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		String mensaje = """
				En la tierra mojada,
				sueñan los claveles.
				Bajo la luna clara,
				bailan los laureles.""";

		System.out.println("TEXTO ORIGINAL:\n" + mensaje + "\n");

		// ALGORITMO CIFRADO 3DES
		long startTime3DES = System.currentTimeMillis();
		String mensajeCifrado3DES = cifrar3DES(mensaje);
		long endTime3DES = System.currentTimeMillis();
		System.out.println("ALGORITMO CIFRADO 3DES:");
		System.out.println("Mensaje Cifrado: \"" + mensajeCifrado3DES + "\"");
		System.out.println("Tiempo de Cifrado: " + (endTime3DES - startTime3DES) + " milisegundos\n");

		// ALGORITMO CIFRADO AES
		long startTimeAES = System.currentTimeMillis();
		String mensajeCifradoAES = cifrarAES(mensaje);
		long endTimeAES = System.currentTimeMillis();
		System.out.println("ALGORITMO CIFRADO AES:");
		System.out.println("Mensaje Cifrado: \"" + mensajeCifradoAES + "\"");
		System.out.println("Tiempo de Cifrado: " + (endTimeAES - startTimeAES) + " milisegundos\n");

		// ALGORITMO CIFRADO RSA
		long startTimeRSA = System.currentTimeMillis();
		String mensajeCifradoRSA = cifrarRSA(mensaje);
		long endTimeRSA = System.currentTimeMillis();
		System.out.println("ALGORITMO CIFRADO RSA:");
		System.out.println("Mensaje Cifrado: \"" + mensajeCifradoRSA + "\"");
		System.out.println("Tiempo de Cifrado: " + (endTimeRSA - startTimeRSA) + " milisegundos\n");

		// MessageDigest (SHA-256)
		String hash = generarHash(mensaje);
		System.out.println("MessageDigest (SHA-256):");
		System.out.println("Hash del mensaje: \"" + hash + "\"");
	}

	/**
	 * Realiza el cifrado del mensaje utilizando el algoritmo 3DES.
	 * 
	 * @param mensaje El mensaje a cifrar.
	 * @return El mensaje cifrado en formato Base64.
	 */
	private static String cifrar3DES(String mensaje) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(168);
			SecretKey key = keyGenerator.generateKey();

			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] encryptedBytes = cipher.doFinal(mensaje.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Realiza el cifrado del mensaje utilizando el algoritmo AES.
	 * 
	 * @param mensaje El mensaje a cifrar.
	 * @return El mensaje cifrado en formato Base64.
	 */
	private static String cifrarAES(String mensaje) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			SecretKey key = keyGenerator.generateKey();

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] encryptedBytes = cipher.doFinal(mensaje.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Realiza el cifrado del mensaje utilizando el algoritmo RSA.
	 * 
	 * @param mensaje El mensaje a cifrar.
	 * @return El mensaje cifrado en formato Base64.
	 */
	private static String cifrarRSA(String mensaje) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

			byte[] encryptedBytes = cipher.doFinal(mensaje.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Genera el hash del mensaje utilizando el algoritmo SHA-256.
	 * 
	 * @param mensaje El mensaje para generar el hash.
	 * @return El hash del mensaje en formato Base64.
	 */
	private static String generarHash(String mensaje) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(mensaje.getBytes());
			return Base64.getEncoder().encodeToString(hashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
}
