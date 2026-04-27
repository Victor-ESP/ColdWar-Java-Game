package Hijos;
import Padre.Planeta;
public class PlanetaGigante extends Planeta {

	public PlanetaGigante() {
		super(400,10);
	}
	public void rellenarMisiles(int ronda) {
		misilesRonda =  10 * ronda;
	}
	public String combate(int misilesLanzados, Planeta atacado) {
		int misilesImpactados = 0;
		String evento = "";

		// Simulación de esquiva (el Enano tiene un 50% de probabilidad de esquivar cada misil)
		if (atacado instanceof PlanetaEnano) {
			for (int i = 0; i < misilesLanzados; i++) {
				if (Math.random() >= 0.5) {
					misilesImpactados++; 
				}
			}
		} else {
			misilesImpactados = misilesLanzados;
		}

		// Aplicamos el daño final al pobrecito atacado
		atacado.setVidas(atacado.getVidas() - misilesImpactados);
		this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles

		evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "]. ";

		if (atacado instanceof PlanetaEnano) {
			evento += "💨 El Enano esquivó " + (misilesLanzados-misilesImpactados) + " misiles. ";
		}
		evento += "(- " + misilesImpactados + " HP).\n";
		return evento;
	}




}
