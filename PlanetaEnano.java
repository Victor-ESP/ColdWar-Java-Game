package Hijos;
import Padre.Planeta;
public class PlanetaEnano extends Planeta {

	public PlanetaEnano(int vida, int misiles) {
		super(vida, misiles);
	}
	public String combate(int misilesLanzados, Planeta atacado) {
		String evento = "";
		
	// Aplicamos el daño final al pobrecito atacado
			atacado.setVidas(getVidas() - misilesLanzados);
			this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles

			evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "]. ";

			evento += "(- " + misilesLanzados + " HP).\n";
			return evento;
		}
}
