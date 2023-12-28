package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Este programa implementa un cliente seguro que utiliza SSL para comunicarse
 * con un servidor seguro. El cliente se conecta al servidor, envía un mensaje
 * (en este caso, un nombre), y recibe una respuesta del servidor.
 * 
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 28/12/2023
 */
public class SecureClient {

	public static void main(String[] args) {
		try {
			// Configuración del almacén de confianza (truststore) para el cliente SSL
			System.setProperty("javax.net.ssl.trustStore",
					"/Users/faunoguazina/Documents/GitHub/Linkia/M09_Act03/clienttruststore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "123456");

			// Creación de un objeto de fábrica de sockets SSL
			SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

			// Creación de un socket SSL para conectarse al servidor en el puerto 8888
			SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 8888);

			System.out.println("¡Programa Cliente Iniciado!");

			// Solicitar al usuario que ingrese su nombre
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Por favor, ingrese su nombre: ");
			String nombre = userInput.readLine();

			// Configuración del escritor para enviar el nombre al servidor
			PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true);
			writer.println(nombre);

			// Configuración del lector para recibir la respuesta del servidor
			BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
			System.out.println("Recibiendo del SERVIDOR: " + reader.readLine());

			// Cierre del socket SSL
			sslSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

