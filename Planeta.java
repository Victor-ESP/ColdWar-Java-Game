package Padre;
import Interface.interfaceCurar;

/**
 * Clase que hace de "ficha de personaje" para los planetas o equipos del juego. 
 * Aquí se guarda cuánta vida tienen, cuántos misiles les quedan y cómo funcionan 
 * sus habilidades (si hacen el doble de daño, si esquivan, o si deciden curarse).
 * @author Aarón Martíns Vilas
 * @author Víctor Mosquera Puchades
 * @author Liliana Ortega Arteaga
 * @author Óscar Rama Blanco
 * @author Gabriela Salazar Franco
 * @author Fabián Sanchez Rodriguez
 * @version 1.0
 * @since 1.0
 */

public abstract class Planeta implements interfaceCurar {
	// Variables
	protected int vidas;
	protected int vidaMaxima;
	protected String nombre;
	protected int misilesRonda;
	protected int numEquipos;
	protected String id;

	public Planeta() {
		this.vidaMaxima=200;
		this.vidas=200;
		this.misilesRonda=50;
	}
	public Planeta(int vida, int misiles) {
		this.vidaMaxima = vida;
		this.vidas = vida;
		this.misilesRonda = misiles;
	}

	// Getters y Setters

	/**
	 * Te chiva cuánta vida le queda al planeta ahora mismo.
	 * @return La vida actual.
	 */
	public int getVidas() { return vidas; }

	/**
	 * Cambia la vida actual del planeta por el número que le pases.
	 * @param vidas La nueva cantidad de vida.
	 */
	public void setVidas(int vidas) { this.vidas = vidas; }

	/**
	 * Te dice cómo se llama este equipo o planeta.
	 * @return El nombre del planeta.
	 */
	public String getNombre() { return nombre; }

	/**
	 * Le pone el nombre que tú quieras al planeta.
	 * @param nombre El texto con el nombre.
	 */
	public void setNombre(String nombre) { this.nombre = nombre; }

	/**
	 * Te dice cuántos misiles tiene listos para disparar (o curarse) en este turno.
	 * @return Los misiles que le sobran.
	 */
	public int getMisilesRonda() { return misilesRonda; }

	/**
	 * Actualiza los misiles que tiene el planeta en la recámara.
	 * @param misiles_ronda El nuevo número de misiles.
	 */
	public void setMisiles_ronda(int misiles_ronda) { this.misilesRonda = misiles_ronda; }

	/**
	 * Te dice cuántos equipos hay en total (si lo necesitas para alguna lógica extra).
	 * @return El número de equipos.
	 */
	public int getNumEquipos() { return numEquipos; }

	/**
	 * Guarda cuántos equipos están participando en la partida.
	 * @param numEquipos El total de equipos.
	 */
	public void setNumEquipos(int numEquipos) { this.numEquipos = numEquipos; }

	/**
	 * Gasta tus misiles para curarte en vez de atacar. Por cada 2 misiles que gastes, 
	 * recuperas 1 punto de vida. Además, el método vigila que no te cures por encima de tu tope de vida.
	 * @param cantidad Los misiles que vas a sacrificar para curarte.
	 * @return Un texto contando qué ha pasado para mostrarlo luego en el registro de la ronda.
	 */
	public String curar(int cantidad) {
		String atacante = this.nombre.replace('.', ' ');		
		// Sumamos la curación
		this.vidas += cantidad / 2;

		// Evitamos que la vida actual supere el límite de vida máxima del planeta
		if (this.vidas > this.vidaMaxima) {
			this.vidas = this.vidaMaxima;
		}

		// El planeta se resta a sí mismo los misiles gastados
		this.misilesRonda -= cantidad;

		return "🛡️ [" + atacante + "] gastó " + cantidad + " misiles en defensa (+ " + cantidad / 2 + " HP al final).\n";
	}

	/**
	 * Aquí pasa la magia de los ataques. Primero tira los dados por si el enemigo es Enano 
	 * y esquiva los misiles. Luego mira si hay ventajas de tipo (estilo Pokémon: Rojo gana a Verde, etc.) 
	 * para hacer el doble o la mitad de daño, y finalmente le resta la vida al enemigo.
	 * @param misilesLanzados Todos los misiles que le has tirado al rival.
	 * @param atacado         El planeta pobrecito que se está comiendo el ataque.
	 * @return Un texto contando el salseo: cuántos impactaron, si hubo esquivas y si el ataque fue súper eficaz.
	 */
	public abstract String combate(int misilesLanzados, Planeta atacado);

	public void rellenarMisiles(int misiles) {
		misilesRonda = misiles;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public String getId(){
		return id;
	}


}
