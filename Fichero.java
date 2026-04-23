package Principal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Fichero {
	public static void guardarFichero (String ganador) {
		// Añadimos el ganador al archivo.txt
		try {
			FileWriter escritor = new FileWriter("ganadores_coldwar.txt", true); 
			// EN caso de que no ganase nadie (Empate) mostramos que hubo un empate
			if (ganador.equals("Nadie")) {
				escritor.write("EMPATE (Ningún superviviente)\n");
			} else {
				escritor.write(ganador + "\n"); 
			}

			escritor.close();
		} catch (IOException ex) {
			System.out.println("❌ Error interno: No se pudo guardar el historial de ganadores.");
		}
	}

	/**
	 * Lee el archivo de texto donde guardamos los ganadores (ganadores_coldwar.txt)
	 * y los muestra por pantalla como si fuera un Salón de la Fama.
	 */
	public static void mostrarGanadores() {
		System.out.println("\n\t\t=================================");
		System.out.println("\t\t||      🏆 SALÓN DE LA FAMA 🏆 ||");
		System.out.println("\t\t=================================");

		File archivo = new File("ganadores_coldwar.txt");

		// SI el archivo aun no ha sido creado lo indicamos
		if (!archivo.exists()) {
			System.out.println("\n\tAún no hay ningún planeta ganador registrado. ¡Sé el primero!");
			return;
		}

		try {
			Scanner lectorArchivo = new Scanner(archivo);
			int contador = 1;

			while (lectorArchivo.hasNextLine()) {
				String nombre = lectorArchivo.nextLine();
				System.out.println("\t" + contador + ". " + nombre);
				contador++;
			}

			lectorArchivo.close(); 

			System.out.println("\t\t=================================\n");

		} catch (Exception ex) {
			System.out.println("❌ Error: No se pudo leer el archivo de ganadores.");
		}
	}
}

