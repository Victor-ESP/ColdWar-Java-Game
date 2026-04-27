package Hijos;

import Padre.Planeta;

public class PlanetaJuegoPersonalizado extends Planeta{


	public PlanetaJuegoPersonalizado(int vidaPers, int misilesIniciales) {
		super(vidaPers,misilesIniciales);
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

		this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles
		atacado.setVidas(atacado.getVidas() - misilesImpactados);
		System.out.println("ATENCION CHIVATO:::--->>"+"A "+atacado.getNombre()+" le quedan "+atacado.getVidas()+"hp" );

		evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "]. ";

		if (atacado instanceof PlanetaEnano) {
			evento += "💨 El Enano esquivó " + (misilesLanzados-misilesImpactados) + " misiles. ";
		}
		evento += "(- " + misilesImpactados + " HP).\n";
		return evento;
	}
}
