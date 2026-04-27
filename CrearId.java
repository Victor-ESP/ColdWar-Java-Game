package coldWar_Utils;

import java.util.Scanner;

public class CrearId {
    
    /**
     * Método para el JUGADOR. Pide el ID por teclado y valida que sea correcto.
     * @param teclado El objeto Scanner activo para leer la entrada.
     * @return El String con el ID validado.
     */
    public static String crearIdManual(Scanner teclado) {
        
        String idFinal = "";
        boolean idCorrecto = false;

        System.out.println("\n\n--- Configuración de Id ---");
        System.out.println("El ID debe tener 4 números y 3 letras mayúsculas (ej: 2023XYZ).");

        // Bucle que se repite hasta que el usuario introduzca un ID válido
        do {
            System.out.print("Introduce el ID del planeta: ");
            String entradaUsuario = teclado.nextLine().trim();

            // Evitar que el campo esté vacío
            if (entradaUsuario.isEmpty()) {
                System.out.println("El ID no puede estar vacío.");
                continue;
            }

            // Usamos Validaciones para verificar formato y unicidad
            if (Validaciones.validarYGuardarId(entradaUsuario)) {
                idFinal = entradaUsuario;
                idCorrecto = true;
                System.out.println("ID válido guardado: " + idFinal);
            }

        } while (!idCorrecto);

        return idFinal;
    }
    
    /**
     * Método para los BOTS. Genera un ID aleatorio automáticamente.
     * Se asegura de que el formato sea correcto y que no esté repetido.
     * @return El String con el ID aleatorio generado.
     */
    public static String crearIdBot() {
        String idGenerado;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean idUnico = false;

        // Bucle por seguridad estadística (generar hasta encontrar uno que no esté en uso)
        do {
            // Generar 4 números aleatorios (0000-9999)
            String numeros = "";
            for (int i = 0; i < 4; i++) {
                numeros += (int) (Math.random() * 10);
            }
            
            // Generar 3 letras aleatorias (A-Z)
            String parteLetras = "";
            for (int i = 0; i < 3; i++) {
                int indiceAleatorio = (int) (Math.random() * letras.length());
                parteLetras += letras.charAt(indiceAleatorio);
            }
            
            idGenerado = numeros + parteLetras;
            
            // Validamos usando el método de Validaciones
            // (Esto comprueba unicidad y lo guarda en la lista automáticamente)
            if (Validaciones.validarYGuardarId(idGenerado)) {
                idUnico = true;
            }
            
        } while (!idUnico);
        
        return idGenerado;
    }
}
