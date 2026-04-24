package Hijos;
import Padre.Planeta;
public class PlanetaVerde extends Planeta{

	public PlanetaVerde() {
		super(200, 50);
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
		if (atacado instanceof PlanetaAzul) { 
			misilesImpactados *= 2;
		}
		else if (atacado instanceof PlanetaRojo) {
			misilesImpactados /= 2;
		}
		//	 	Aplicamos el daño final al pobrecito atacado
		atacado.setVidas(atacado.getVidas() - misilesImpactados);

		this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles
		System.out.println("ATENCION CHIVATO:::--->>"+"A "+atacado.getNombre()+" le quedan "+atacado.getVidas()+"hp" );

		evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "]. ";

		if (atacado instanceof PlanetaEnano) {
			evento += "💨 El Enano esquivó " + (misilesLanzados-misilesImpactados) + " misiles. ";
		}
		evento += "(- " + misilesImpactados + " HP).\n";
		return evento;
	}
}
