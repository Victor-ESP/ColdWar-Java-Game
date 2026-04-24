package Hijos;
import Padre.Planeta;
public class PlanetaEnano extends Planeta {

	public PlanetaEnano() {
		super(100,50);
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
		// Aplicamos el daño final al planeta atacado
		atacado.setVidas(atacado.getVidas() - misilesImpactados);
		this.misilesRonda -= misilesLanzados; // Gastamos nuestros misiles
		System.out.println("ATENCION CHIVATO:::--->>"+"A "+atacado.getNombre()+" le quedan "+atacado.getVidas()+"hp" );

		evento = "🚀 [" + nombre.replace('.', ' ') + "] lanzó " + misilesLanzados + " misiles a [" + atacado.getNombre().replace('.', ' ') + "], impactaron "+misilesImpactados+".";

		evento += "(- " + misilesImpactados + " HP).\n";
		return evento;
	}
}
