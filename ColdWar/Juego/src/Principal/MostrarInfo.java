package Principal;
/**
 * @author Aarón Martíns Vilas
 * @author Víctor Mosquera Puchades
 * @author Liliana Ortega Arteaga
 * @author Óscar Rama Blanco
 * @author Gabriela Salazar Franco
 * @author Fabián Sanchez Rodriguez
 * 
 * @version 1.0
 * @since 1.0
 * 
 */
public class MostrarInfo {

    public static void mostrarLogo() {
		System.out.println("░█████╗░░█████╗░██╗░░░░░██████╗░░██╗░░░░░░░██╗░█████╗░██████╗░");
		System.out.println("██╔══██╗██╔══██╗██║░░░░░██╔══██╗░██║░░██╗░░██║██╔══██╗██╔══██╗");
		System.out.println("██║░░╚═╝██║░░██║██║░░░░░██║░░██║░╚██╗████╗██╔╝███████║██████╔╝");
		System.out.println("██║░░██╗██║░░██║██║░░░░░██║░░██║░░████╔═████║░██╔══██║██╔══██╗");
		System.out.println("╚█████╔╝╚█████╔╝███████╗██████╔╝░░╚██╔╝░╚██╔╝░██║░░██║██║░░██║");
		System.out.println("░╚════╝░░╚════╝░╚══════╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░░╚═╝╚═╝░░╚═╝");
	}
    
    // =================================================REGLAS=================================================
    /**
     * Método que muestra las reglas del juego.
     */
    public static void mostrarReglas() {
        System.out.println("1.-En la partida personalizada no se pueden insertar ciertos valores a los siguientes atributos");
        System.out.println("\n\t-Vida personalizada: Un valor negativo o menor a 3");
        System.out.println("\n\t-Misiles personalizados: Un valor negativo, menor a 2 o mayor a la vida");
        System.out.println("\n2.-Al momento de iniciar la partida ningún equipo puede defenderse en primera ronda");
        System.out.println("\n3.-Los equipos han de agotar todos sus misiles para terminar su turno ");
        System.out.println("\n4.-Al los equipos se curan la mitad de la cantidad de los misiles usados");
        System.out.println("\n5.-La partida acabará cuando quede 1 o 0 equipos restantes");
    }

    // =================================================INFORMACION=================================================
    /**
     * Método que muestra la información sobre el juego como la versión, los autores
     * e información de contacto.
     */
    public static void mostrarInformacion() {
        System.out.println("VERSION: 1.0");
        System.out.println("\nCONTACTOS: ");
        System.out.println("amartinsv25@fpcoruna.afundacion.org");
        System.out.println("vmosquerap25@fpcoruna.afundacion.org");
        System.out.println("lortegaa25@fpcoruna.afundacion.org");
        System.out.println("oramab25@fpcoruna.afundacion.org");
        System.out.println("gsalazarf25@fpcoruna.afundacion.org");
        System.out.println("fsanchezr25@fpcoruna.afundacion.org");
        System.out.println("\nAUTORES: ");
        System.out.println("Aarón Martíns Vilas");
        System.out.println("Víctor Mosquera Puchades");
        System.out.println("Liliana Ortega Arteaga");
        System.out.println("Óscar Rama Blanco");
        System.out.println("Gabriela Salazar Franco");
        System.out.println("Fabián Sánchez Rodríguez");
    }
    /**
     * Método que muestra la información del menú
     */
    public static void mostrarMenu() {
		System.out.println("\n\t\t=================================");
		System.out.println("\t\t||           Menu              ||");
		System.out.println("\t\t||        1-Jugar              ||");
		System.out.println("\t\t||        2-Reglas del juego   ||");
		System.out.println("\t\t||        3-Informacion        ||");
		System.out.println("\t\t||        4-Mostrar Ganadores  ||"); 
		System.out.println("\t\t||        0-Salir              ||");
		System.out.println("\t\t=================================");
    }
    /**
     * Método que fuestra el tipo de planetas
     */
    public static void mostrarTipoPlaneta() {
    	System.out.println("Selecciona el tipo de planeta:");
		System.out.println("1. Normal (Sin modificaciones)");
		System.out.println("2. Planeta Rojo (x2 a verde, /2 a azul)");
		System.out.println("3. Planeta Azul (x2 a rojo, /2 a verde)");
		System.out.println("4. Planeta Verde (x2 a azul, /2 a rojo)");
		System.out.println("5. Planeta Gigante (Doble vida, empieza con 10 misiles y escala)");
		System.out.println("6. Planeta Enano (Mitad vida, 50% de esquivar misiles)");

    }
    
    public static void mostrarRegistro (int ronda, String eventos) {
		System.out.println("\n==================================");
		System.out.println("   REGISTRO DE LA RONDA " + ronda);
		System.out.println("==================================");
		System.out.print(eventos); 
		System.out.println("==================================\n");
    }
}
