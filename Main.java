package Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Hijos.PlanetaAzul;
import Hijos.PlanetaEnano;
import Hijos.PlanetaGigante;
import Hijos.PlanetaNormal;
import Hijos.PlanetaRojo;
import Hijos.PlanetaVerde;
import Padre.Planeta;

/**
 * Clase principal donde arranca el juego Coldwar. Aquí está el menú, 
 * la preparación de la partida, los turnos y la lista de ganadores.
 * @author Aarón Martíns Vilas
 * @author Víctor Mosquera Puchades
 * @author Liliana Ortega Arteaga
 * @author Óscar Rama Blanco
 * @author Gabriela Salazar Franco
 * @author Fabián Sanchez Rodriguez
 * @version 1.0
 * @since 1.0
 */
public class Main {
	static Scanner e = new Scanner(System.in);

	/**
	 * Punto de partida del programa. Enseña el logo y te deja en el menú principal 
	 * dando vueltas hasta que decidas salir poniendo un 0.
	 * * @param args Argumentos de la línea de comandos (no los usamos).
	 */
	public static void main(String[] args) {
		int opciones = 0;
		MostrarInfo.mostrarLogo();

		// Mantiene el programa en ejecución mostrando el menú principal continuamente
		// hasta que el usuario introduce la opción de salida (0).
		do {
			opciones = mostrarMenu();
			switch (opciones) {
			case 1:
				jugar();
				break;
			case 2:
				MostrarInfo.mostrarReglas();
				break;
			case 3:
				MostrarInfo.mostrarInformacion();
				break;
			case 4:
				Fichero.mostrarGanadores();
				break;
			case 0:
				salir();
				break;
			}
		} while (opciones != 0);
	}

	/**
	 * Prepara todo antes de empezar a jugar. Te pregunta el modo de juego, 
	 * cuántos equipos van a participar y cómo quieres configurar las vidas y los misiles.
	 */
	public static void jugar() {
		System.out.println("\n\t\t=================================");
		System.out.println("\t\t||          Partida            ||");
		System.out.println("\t\t|| 1-Partida personalizada     ||");
		System.out.println("\t\t|| 2-Partida Normal            ||");
		System.out.println("\t\t|| 3-Partida con bots          ||");
		System.out.println("\t\t|| 0-Volver                    ||");
		System.out.println("\t\t=================================");
		int opcion = 0;
		do {
			System.out.print("Introduce opcion (0-3): ");
			opcion = pedirNumero();
			if (opcion < 0 || opcion > 3) {
				System.out.println("❌ Número fuera de rango.");
			}
		} while (opcion < 0 || opcion > 3);
		// Volver
		if (opcion == 0) {
			return;
		}

		System.out.print("¿Cuántos equipos van a participar? (Mínimo 3): ");
		int numEquipos = 0;
		do {
			numEquipos = pedirNumero();
			if (numEquipos < 3) {
				System.out.print("❌ Tienen que ser al menos 3 equipos. Inténtalo de nuevo: ");
			}
		} while (numEquipos < 3);

		ArrayList<Planeta> equipos = new ArrayList<>();
		int misilesIniciales = 50;
		int vida = 200;

		// Personalizada o Partida con bots
		if (opcion == 1 || opcion == 3) {
			System.out.print("¿Cantidad de vida personalizada?: ");
			do {
				vida = pedirNumero();
				if (vida < 3) {
					System.out.print("La vida ha de ser mayor a 2. Intentalo de nuevo: ");
				}
			} while (vida < 3);

			System.out.print("¿Cantidad de misiles personalizada?: ");
			do {
				misilesIniciales = pedirNumero();
				if (misilesIniciales >= vida || misilesIniciales <= 1) {
					System.out.print(
							"La cantidad de misiles iniciales no puede ser menor o igual a 1 y tampoco igual o mayor a la cantidad de vida.\nIntentalo de nuevo: ");
				}
			} while (misilesIniciales >= vida || misilesIniciales <= 1);
		}

		iniciarPartida(equipos, misilesIniciales, numEquipos, opcion, vida);
	}

	/**
	 * Crea los equipos y controla los turnos de la partida. Se encarga de que 
	 * los jugadores ataquen, se curen, mueran y de ver quién gana al final.
	 * * @param equipos          Lista donde vamos a guardar los planetas que juegan.
	 * @param misilesIniciales Los misiles con los que empieza cada equipo.
	 * @param numEquipos       Cuántos jugadores hay en total.
	 * @param tipoPartida      El modo de juego que se ha elegido (1, 2 o 3).
	 * @param vidaPers         La vida inicial si se eligió un modo personalizado o con bots.
	 */
	public static void iniciarPartida(ArrayList<Planeta> equipos, int misilesIniciales, int numEquipos, int tipoPartida, int vida) {
		String eventos = "";
		int ronda = 1;

		System.out.println("\n--- Bienvenido a Coldwar ---");
		System.out.println("Creando equipos...");

		// En caso de haber elegido Partida con bots usamos el metodo generarBots para que el usuario cree su personaje
		// y que los bots se creen automaticamente con nombres aleatorios
		if (tipoPartida == 3) {
			generarBots(equipos, numEquipos, vida, misilesIniciales);

		} else {
			for (int i = 0; i < numEquipos; i++) {
				boolean nombreValido= false;
				String nombre = "";
				do {
					nombreValido = true;
					System.out.print("\nInserta el nombre del equipo Nº" + (i + 1) + ": ");
					nombre = e.nextLine().trim();
					if (nombre.isEmpty()) {
						System.out.println("❌ El nombre no puede estar vacío.");
						nombreValido = false;
						continue;
					} else if (nombre.endsWith(".")) {
						System.out.println("❌ El nombre no puede terminar en punto.");
						nombreValido = false;
						continue;
					}
					// Verificamos que el nombre introducido no esté ya en uso por otro jugador 
					// en la lista actual para evitar conflictos de identificación.
					for (int j = 0; j < equipos.size(); j++) {
						if (equipos.get(j).getNombre().equalsIgnoreCase(nombre)) {
							System.out.println("❌ Ese nombre ya está en uso. Por favor, elige otro.");
							nombreValido = false;
							break;
						}
					}
				} while (!nombreValido);

				Planeta nuevoPlaneta = null;
				if (tipoPartida == 2) {
					MostrarInfo.mostrarTipoPlaneta();
					int tipoPlaneta = 0;
					do {
						System.out.print("Introduce el tipo (1-6): ");
						tipoPlaneta = pedirNumero();
						if (tipoPlaneta < 1 || tipoPlaneta > 6) {
							System.out.println("❌ Opción inválida. Debe ser entre 1 y 6.");
						}
					} while (tipoPlaneta < 1 || tipoPlaneta > 6);
					if(tipoPlaneta == 1) {
						nuevoPlaneta = new PlanetaNormal();
					}else if(tipoPlaneta == 2) {
						nuevoPlaneta = new PlanetaRojo();
					}else if(tipoPlaneta == 3) {
						nuevoPlaneta = new PlanetaAzul();
					}else if(tipoPlaneta == 4) {
						nuevoPlaneta = new PlanetaVerde();
					}else if(tipoPlaneta == 5) {
						nuevoPlaneta = new PlanetaGigante();
					}else if(tipoPlaneta == 6) {
						nuevoPlaneta = new PlanetaEnano();
					}
				}
				nuevoPlaneta.setNombre(nombre);
				equipos.add(nuevoPlaneta);
			}
		}

		// Repetimos el siguiente bucle mientras queden 2 equipos vivos o más
		while (contarVivos(equipos) >= 2) {
			System.out.println("\n==================================");
			System.out.println("          RONDA " + ronda);
			System.out.println("==================================");
			imprimirEstadoEquipos(equipos, tipoPartida);

			boolean[] empezoRondaVivo = new boolean[equipos.size()];
			int[] vidaInicialRonda = new int[equipos.size()];

			// Capturamos una instantánea de la salud de todos los participantes justo al inicio, en caso de que su salud
			// sea mayor a 0 los guadamos en el array empezoRondaVivo para que así no pierdan su turno en caso de morir en esa misma ronda
			for (int i = 0; i < equipos.size(); i++) {
				empezoRondaVivo[i] = equipos.get(i).getVidas() > 0;
				vidaInicialRonda[i] = equipos.get(i).getVidas();
			}
			//Emezamos con los turnos de cada equipo
			for (int i = 0; i < equipos.size(); i++) {
				Planeta atacante = equipos.get(i);

				// Omitimos el turno en caso de que el equipo no estuviese vivo
				if (!empezoRondaVivo[i]) {
					continue;
				}

				// Si el atacante es un bot usamos el metodo atacarBots el cual contiene el funcionamiento de ataque de los bots
				if (atacante.getNombre().endsWith(".")) {
					eventos += atacarBots(equipos, vidaInicialRonda, ronda, atacante);

				} else {
					while (atacante.getMisiles_ronda() > 0) {
						System.out.println("\n------------------------------------------");
						System.out.println("Turno de: " + atacante.getNombre() + " (" + vidaInicialRonda[i] + " HP)");
						System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());
						System.out.println("------------------------------------------");

						Planeta[] objetivos = new Planeta[equipos.size()];
						int opcionesDisponibles = mostrarObjetivos(atacante, equipos, objetivos, ronda, vidaInicialRonda);
						int opcion = -1;
						do {
							System.out.print("¿Qué deseas hacer?: ");
							opcion = pedirNumero();
							if (opcion < 0 || opcion > opcionesDisponibles) {
								System.out.println("❌ Opción no válida.");
							} else if (opcion == 0 && ronda == 1) {
								System.out.println("❌ ¡No puedes defenderte en la primera ronda!");
								opcion = -1; 
							}
						} while (opcion < 0 || opcion > opcionesDisponibles);

						if (opcion == 0) {
							System.out.print("¿Cuántos misiles vas a usar para defenderte?: ");
							int misilesDefensa = pedirMisilesValidos(atacante.getMisiles_ronda());
							eventos += atacante.curar(misilesDefensa);
						} else {
							System.out.print("¿Cuántos misiles vas a lanzar?: ");
							int misilesLanzar = pedirMisilesValidos(atacante.getMisiles_ronda());
							Planeta objetivo = objetivos[opcion - 1];
							eventos += atacante.combate(misilesLanzar, objetivo);
						}
					}

				}
			}

			// Al final de la ronda observamos quienes han muerto y los añadimos al registor 
			eventos += "\n--- RESUMEN DE BAJAS DE LA RONDA ---\n";
			boolean huboBajas = false;
			for (int i = 0; i < equipos.size(); i++) {
				if (empezoRondaVivo[i] && equipos.get(i).getVidas() <= 0) {
					eventos += "☠️ ¡EL EQUIPO " + equipos.get(i).getNombre().toUpperCase().replace('.', ' ')
							+ " HA SIDO DESTRUIDO!\n";
					huboBajas = true;
				}
			}

			// En caso de no haber bajas tambien lo informamos
			if (!huboBajas) {
				eventos += "Nadie ha sido destruido en esta ronda.\n";
			}
			imprimirRegistro(eventos, ronda);
			eventos = ""; // Reiniciamos el registro de la ronda

			// Comprobamos si aun quedan 2 o mas equipos vivos
			if (contarVivos(equipos) <= 1) {
				// EN caso de que solo quede un equipo vivo se declarará a ese equipo como ganador
				System.out.println("\n🏆 ¡LA GUERRA HA TERMINADO! El ganador es: " + obtenerGanador(equipos));
				break;
			}
			ronda++;
			for(Planeta p: equipos) {
				if(p.getVidas() > 0 && p instanceof PlanetaGigante) {
					p.rellenarMisiles(ronda);
				}else 	if(p.getVidas() > 0){
					p.rellenarMisiles(50);
				}
			}
		}
	}

	/**
	 * Muestra el menú principal en pantalla y te pide que elijas una opción.
	 * * @return El número de la opción que ha elegido el usuario (del 0 al 4).
	 */
	public static int mostrarMenu() {
		MostrarInfo.mostrarMenu();
		int opciones = 0;
		do {
			System.out.print("Elige una opción (0-4): ");
			opciones = pedirNumero();
		} while (opciones < 0 || opciones > 4); 
		return opciones;
	}

	/**
	 * Simplemente muestra un mensaje para despedirse cuando cierras el juego.
	 */
	public static void salir() {
		System.out.println("Fin del juego");
	}

	/**
	 * Pide un número por teclado y se asegura de que no metas letras por error, 
	 * así evitamos que el programa se rompa (salte una excepción).
	 * * @return El número que ha escrito el usuario.
	 */
	public static int pedirNumero() {
		int cantidad = -1;
		boolean esNumero = false;
		do {
			try {
				String input = e.nextLine().trim();
				if (input.isEmpty()) {
					System.out.print("Por favor, introduce un número: ");
					continue;
				}
				cantidad = Integer.parseInt(input);
				esNumero = true;
			} catch (NumberFormatException ex) {
				System.out.print("No es un número válido. Intenta de nuevo: ");
			}
		} while (!esNumero);
		return cantidad;
	}

	/**
	 * Te pregunta cuántos misiles quieres usar y vigila que no intentes gastar 
	 * más de los que tienes o menos de 1.
	 * * @param maximoDisponible El tope de misiles que puedes gastar en este turno.
	 * @return Los misiles que finalmente vas a usar.
	 */
	public static int pedirMisilesValidos(int maximoDisponible) {
		int cantidad = -1;
		do {
			cantidad = pedirNumero();
			if (cantidad < 1) {
				System.out.print("Debes usar al menos 1: ");
			} else if (cantidad > maximoDisponible) {
				System.out.print("Solo tienes " + maximoDisponible + " misiles: ");
			}
		} while (cantidad < 1 || cantidad > maximoDisponible);
		return cantidad;
	}

	/**
	 * Muestra en pantalla la vida que le queda a cada equipo. Si es el modo de juego
	 * "Normal", también te avisa de qué tipo de planeta es cada uno (Rojo, Azul, etc.).
	 * * @param equipos     La lista con todos los planetas de la partida.
	 * @param tipoPartida Sirve para saber si estamos en el modo "Normal" y mostrar los tipos.
	 */
	public static void imprimirEstadoEquipos(ArrayList<Planeta> equipos, int tipoPartida) {
		for (Planeta p : equipos) {
			if (p.getVidas() > 0) {
				// En caso de haber elegido la partida normal tambien mostramos los tipos de planetas seleccionados por cada equipo
				if (tipoPartida == 2) {
					System.out.println(
							"[+] " + p.getNombre().replace('.', ' ') + "----> " + p.getVidas() + " HP");
				} else {
					System.out.println("[+] " + p.getNombre().replace('.', ' ') + " ---> " + p.getVidas() + " HP");
				}
			} else {
				System.out.println("[-] " + p.getNombre().replace('.', ' ') + " ---> DESTRUIDO");
			}
		}
	}

	/**
	 * Muestra a quién puedes atacar en tu turno (solo a los que siguen vivos) y 
	 * te da la opción de curarte si no estás en la primera ronda.
	 * @param atacante         El planeta al que le toca jugar ahora.
	 * @param equipos          Todos los equipos de la partida.
	 * @param objetivos        Un array que usamos para saber a qué enemigo corresponde cada número del menú.
	 * @param ronda            La ronda actual (para saber si bloquear la opción de defenderse).
	 * @param vidaInicialRonda Las vidas al empezar la ronda, para no dejarte atacar a alguien que acaba de morir.
	 * @return El número máximo de opciones que hay en el menú para atacar.
	 */
	public static int mostrarObjetivos(Planeta atacante, ArrayList<Planeta> equipos, Planeta[] objetivos, int ronda,
			int[] vidaInicialRonda) {
		int pos = 0;
		for (int i = 0; i < equipos.size(); i++) {
			Planeta p = equipos.get(i);
			if (p != atacante && vidaInicialRonda[i] > 0) {
				objetivos[pos] = p;
				if (!atacante.getNombre().endsWith(".")) {
					System.out.println((pos + 1) + ". Atacar a " + p.getNombre().replace('.', ' ') + " ("
							+ vidaInicialRonda[i] + " HP)");
				}
				pos++;
			}
		}

		if (!atacante.getNombre().endsWith(".")) {
			if (ronda == 1) {
				System.out.println("0. Defender (BLOQUEADO EN RONDA 1)");
			} else {
				System.out.println("0. Defender y recuperar HP");
			}
		}
		return pos;
	}

	/**
	 * Imprime un bloque de texto bonito con todo lo que ha pasado durante la ronda 
	 * (los ataques, curaciones, muertes...).
	 * * @param eventos Todo el texto con lo que ha pasado.
	 * @param ronda   El número de la ronda que acaba de terminar.
	 */
	public static void imprimirRegistro(String eventos, int ronda) {
		MostrarInfo.mostrarRegistro(ronda, eventos);
	}

	/**
	 * Cuenta cuántos equipos siguen con vida (con vida mayor que cero).
	 * * @param equipos La lista de todos los planetas.
	 * @return El número de supervivientes.
	 */
	public static int contarVivos(ArrayList<Planeta> equipos) {
		int vivos = 0;
		for (Planeta p : equipos) {
			if (p.getVidas() > 0) {
				vivos++;
			}
		}
		return vivos;
	}

	/**
	 * Busca qué equipo ha ganado (el último que queda vivo), guarda su nombre 
	 * en un archivo de texto llamado ganadores_coldwar.txt y te dice quién es.
	 * * @param equipos La lista de equipos cuando la partida ya ha acabado.
	 * @return El nombre del ganador o "Nadie" si ha habido un empate raro.
	 */
	public static String obtenerGanador(ArrayList<Planeta> equipos) {
		//EN caso de no haber ganador como no modificaremos el string lo inicializamos con la palabra "Nadie"
		String ganador = "Nadie";

		// Buscamos cual es el equipo ganador
		for (Planeta p : equipos) {
			if (p.getVidas() > 0) {
				ganador = p.getNombre().replace('.', ' ');
				break; 
			}
		}

		Fichero.guardarFichero(ganador);

		return ganador;
	}

	/**
	 * Crea tu equipo para la partida y rellena los huecos que faltan con bots. 
	 * A los bots les pone nombres aleatorios de una lista bastante graciosa.
	 * * @param equipos          La lista vacía donde vamos a meter a los jugadores.
	 * @param numEquipos       Cuántos equipos quieres que haya en total en la partida.
	 * @param vidaPers         La vida inicial que tendrán todos.
	 * @param misilesIniciales Los misiles iniciales de todos los equipos.
	 */
	public static void generarBots(ArrayList<Planeta> equipos, int numEquipos, int vidaPers, int misilesIniciales) {
		ArrayList<String> nomAleatorio = new ArrayList<>(Arrays.asList("Croquetofilia.", "Pancetamania.",
				"Gazpachobiónico.", "Albondigazo.", "Macarronninja.", "Chistorrapower.", "Garbanzostyle.",
				"Churrogaláctico.", "Mortadelatrón.", "Salmorejofit.", "Aceitunafuturista.", "Pimentonero.",
				"Calamaresuelto.", "Berenjenatómica.", "Tortilladepatata.", "Jamoniberico.", "Quesitofundido.",
				"Bacalaosalado.", "Fabadacósmica.", "Polloasado.", "Pantuflaveloz.", "Calcetinperdiditico.",
				"Escobavoladora.", "Grifogoteante.", "Semáforonaranja.", "Ladrilloblando.", "Tornillosuelto.",
				"Neveravacia.", "Microondasloco.", "Bombillafundida.", "Paraguasroto.", "Sombreropaja.",
				"Alfombratágica.", "Ventanabierta.", "Cojínmullido.", "Tenedortorcido.", "Jabonresbaladizo.",
				"Cepillodental.", "Cortinaducha.", "Archivadormetalico.", "Mapacherebelde.", "Ornitorrincoco.",
				"Flamencocojonuo.", "Jirafasinfoto.", "Hipopotamofeliz.", "Zarigüeyasandia.", "Tortugaveloz.",
				"Perrogato.", "Gatosalchicha.", "Pájaroestático.", "Elefantevolador.", "Camaleonazul.",
				"Tiburonvegetariano.", "Medusagominola.", "Cangrejofiestero.", "Hormigagigante.", "Abejareina.",
				"Moscacojonera.", "Cucarachasurfer.", "Pulpoabrazo.", "Despistadototal.", "Aburridisimo.",
				"Furiacolosal.", "Ruidosilencioso.", "Brillantinaoscura.", "Bajitogigante.", "Valientecobarde.",
				"Rapidotortuga.", "Limpiomugre.", "Saladodulce.", "Peludocalvo.", "Flacogordillo.", "Ciegomiron.",
				"Zurdodiestro.", "Invisiblepresente.", "Dormilonalerta.", "Risatriste.", "Suertemaldita.",
				"Exitofrustrado.", "Genioidiota.", "Aitortilla.", "Pacomerlo.", "Elbalazo.", "Aquilesbailo.",
				"Benitocamela.", "Deboramelo.", "Elenanito.", "Elsapato.", "Inesesario.", "Lolamento.", "Marioneta.",
				"Peregil.", "Rosamelano.", "Susanoria.", "Estelagartija.", "Romangante.", "Armandobronca.",
				"Luzcuesta.", "Marcianoataca.", "Finisterre."));

		boolean nombreValido = false;
		String nombre = "";

		do {
			nombreValido = true;
			System.out.print("Inserta el nombre de tu equipo: ");
			nombre = e.nextLine().trim();
			if (nombre.isEmpty()) {
				System.out.println("❌ El nombre no puede estar vacío.");
				nombreValido = false;
				continue;
			} else if (nombre.endsWith(".")) {
				System.out.println("❌ El nombre no puede terminar en punto.");
				nombreValido = false;
				continue;
			}
		} while (!nombreValido);
		//Añadimos al usuario en el arraylist de equipos
		Planeta nuevoPlaneta = new PlanetaNormal();
		nuevoPlaneta.setNombre(nombre);
		equipos.add(nuevoPlaneta);

		//Añadimos los bots con nombres aleatorios
		for (int i = 1; i < numEquipos; i++) {
			nombre = "";
			int numRandom = numAleatorio(0, nomAleatorio.size() - 1);
			nombre = nomAleatorio.get(numRandom);
			nomAleatorio.remove(numRandom);

			nuevoPlaneta = new PlanetaNormal();
			nuevoPlaneta.setNombre(nombre);
			equipos.add(nuevoPlaneta);
		}
	}

	/**
	 * Saca un número aleatorio que esté entre el mínimo y el máximo que le pases. 
	 * Se usa para que los ataques de los bots sean impredecibles o para elegir nombres.
	 * * @param minimo El número más pequeño que quieres que pueda salir.
	 * @param maximo El número más grande que quieres que pueda salir.
	 * @return Un número al azar entre esos dos.
	 */
	public static int numAleatorio(int minimo, int maximo) {
		return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
	}

	/**
	 * Hace que los bots jueguen solos. Eligen a lo loco a qué enemigo atacar 
	 * (o si se curan) y también cuántos misiles gastar en su turno.
	 * * @param equipos          Todos los equipos de la partida para saber a quién apuntar.
	 * @param vidaInicialRonda Para que el bot no ataque a alguien que ya murió en este mismo turno.
	 * @param ronda            La ronda actual (para que no se curen en la primera).
	 * @param atacante         El planeta bot que está jugando ahora mismo.
	 * @return Un texto (String) con todo lo que ha hecho el bot en su turno para mostrarlo luego.
	 */
	public static String atacarBots(ArrayList<Planeta> equipos, int[] vidaInicialRonda, int ronda, Planeta atacante) {
		String logBot = "";

		while (atacante.getMisiles_ronda() > 0) {
			Planeta[] objetivos = new Planeta[equipos.size()];
			int opcionesDisponibles = mostrarObjetivos(atacante, equipos, objetivos, ronda, vidaInicialRonda);
			int opcion = -1;

			do {
				opcion = numAleatorio(0, opcionesDisponibles);
				if (opcion == 0 && ronda == 1) {
					opcion = -1; 
				}
			} while (opcion < 0 || opcion > opcionesDisponibles);

			if (opcion == 0) {

				int misilesDefensa = numAleatorio(1, atacante.getMisiles_ronda());
				logBot += atacante.curar(misilesDefensa);
			} else {
				int misilesLanzar = numAleatorio(1, atacante.getMisiles_ronda());
				Planeta objetivo = objetivos[opcion - 1];
				logBot += atacante.combate(misilesLanzar, objetivo);
			}
		}

		return logBot;
	}

	/**
	 * Lee el archivo de texto donde guardamos los ganadores (ganadores_coldwar.txt)
	 * y los muestra por pantalla como si fuera un Salón de la Fama.
	 */

}
