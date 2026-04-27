package coldWar_Utils;

import java.util.ArrayList;

public class Validaciones {

    // Patrón del id: 4 números + 3 letras mayúsculas
    private static final String patronId = "\\d{4}[A-Z]{3}";
    
    // ArrayList donde se van a guardar los IDs para asegurar que sean únicos
    private static ArrayList<String> identificadores = new ArrayList<>();

    /**
     * Valida si un String cumple el formato de ID y si no está repetido.
     * Si es correcto, lo guarda en la lista de utilizados.
     * @param id El String a validar.
     * @return true si es válido y único, false en caso contrario.
     */
    public static boolean validarYGuardarId(String id) {
        
        // 1. COMPROBACIÓN DE FORMATO (Regex)
        if (!id.matches(patronId)) {
            System.out.println("❌ Error: El formato del ID es incorrecto. Debe ser 4 números seguidos de 3 letras mayúsculas (ej: 1234ABC).");
            return false; 
        }
        
        // 2. COMPROBACIÓN DE UNICIDAD
        if (identificadores.contains(id)) {
            System.out.println("❌ Error: El ID " + id + " ya está en uso.");
            return false;
        }
        
        // Si es válido, lo añadimos a la lista de usados
        identificadores.add(id);
        
        return true;
    }
}
