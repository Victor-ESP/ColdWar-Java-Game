package Hijos;
import Padre.Planeta;
public class PlanetaRojo extends Planeta {

	public PlanetaRojo(int vida, int misiles) {
		super(vida, misiles);
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

		// Cálculo de ventajas y desventajas (Piedra, Papel, Tijera)
		if (atacado instanceof PlanetaVerde) { 
			misilesLanzados *= 2;
			atacado.setVidas(getVidas() - misilesImpactados);
		} // Verde ataca a Azul
		else if (atacado instanceof PlanetaAzul) {
			misilesLanzados /= 2;
			atacado.setVidas(getVidas() - misilesImpactados);
		}	// Verde ataca contra Rojo
		else {
			atacado.setVidas(getVidas() - misilesImpactados);
		}	// Cualquier otro

	// Aplicamos el daño final al pobrecito atacado
	
	this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles

	evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "]. ";

	if (atacado instanceof PlanetaEnano) {
		evento += "💨 El Enano esquivó " + (misilesLanzados-misilesImpactados) + " misiles. ";
	}
	evento += "(- " + misilesImpactados + " HP).\n";
	return evento;
}
}
