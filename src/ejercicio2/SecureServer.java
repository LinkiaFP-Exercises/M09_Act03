package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * Este programa implementa un servidor seguro que utiliza SSL para la
 * comunicación con un cliente. El servidor espera a que un cliente se conecte,
 * recibe un mensaje, imprime el mensaje recibido y envía una respuesta al
 * cliente.
 * 
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 28/12/2023
 */
public class SecureServer {

	public static void main(String[] args) {
		try {
			// Configuración del almacén de claves (keystore) para el servidor SSL
			System.setProperty("javax.net.ssl.keyStore",
					"/Users/faunoguazina/Documents/GitHub/Linkia/M09_Act03/serverkeystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "123456");

			// Creación de un objeto de fábrica de sockets SSL
			SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();

			// Creación de un socket de servidor SSL en el puerto 8888
			SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(8888);

			System.out.println("¡Esperando al Cliente!");

			// Espera a que un cliente se conecte y acepta la conexión
			SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

			// Configuración del lector para leer datos del cliente
			BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
			System.out.println("Recibiendo del CLIENTE: " + reader.readLine());

			// Configuración del escritor para enviar una respuesta al cliente
			PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true);
			writer.println("Nombre Recibido Correctamente");

			// Cierre del socket SSL y el socket del servidor
			sslSocket.close();
			sslServerSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

