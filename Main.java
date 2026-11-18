package juego;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal del juego Adivina el Número (Examen de Promoción).
 * Administra el menú de consola, la interacción con el usuario,
 * el flujo de las partidas y la validación de excepciones.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    
    // Dificultad por defecto (Normal: 1 a 100, 7 intentos)
    private static int rangoMin = 1;
    private static int rangoMax = 100;
    private static int maxIntentos = 7;

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción (1-4): ");
            
            switch (opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    configurarDificultad();
                    break;
                case 3:
                    mostrarInstrucciones();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por jugar! Hasta la próxima.");
                    break;
                default:
                    System.out.println("\n⚠️ Opción no válida. Ingrese un número del 1 al 4.");
            }
        } while (opcion != 4);
        
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("   JUEGO: ADIVINA EL NÚMERO (PROMOCIÓN)   ");
        System.out.println("==========================================");
        System.out.println("1. Jugar nueva partida");
        System.out.println("2. Configurar Dificultad (Actual: " + rangoMin + "-" + rangoMax + ", " + maxIntentos + " intentos)");
        System.out.println("3. Ver Instrucciones");
        System.out.println("4. Salir");
        System.out.println("------------------------------------------");
    }

    private static void jugar() {
        Juego juego = new Juego(rangoMin, rangoMax, maxIntentos);
        
        System.out.println("\n--- ¡PARTIDA INICIADA! ---");
        System.out.println("He pensado un número entre " + juego.getRangoMin() + " y " + juego.getRangoMax() + ".");
        System.out.println("Tienes " + juego.getMaxIntentos() + " intentos para adivinarlo.\n");

        while (!juego.esFinDelJuego()) {
            System.out.println("Intentos restantes: " + juego.getIntentosRestantes());
            int intento = leerEntero("Ingrese su número: ");

            // Validación de rango
            if (intento < juego.getRangoMin() || intento > juego.getRangoMax()) {
                System.out.println("⚠️ ATENCIÓN: El número debe estar entre " + juego.getRangoMin() + " y " + juego.getRangoMax() + ".\n");
                continue;
            }

            int resultado = juego.evaluarIntento(intento);

            if (resultado == 0) {
                System.out.println("\n🎉 ¡FELICITACIONES! ¡Has acertado el número secreto: " + juego.getNumeroSecreto() + "!");
                System.out.println("Te quedaban " + juego.getIntentosRestantes() + " intentos.");
                break;
            } else if (resultado == 1) {
                System.out.println("📉 Pista: El número secreto es MÁS BAJO.\n");
            } else {
                System.out.println("📈 Pista: El número secreto es MÁS ALTO.\n");
            }
        }

        if (!juego.isJuegoGanado()) {
            System.out.println("\n💥 ¡GAME OVER! Se agotaron tus intentos.");
            System.out.println("El número secreto era: " + juego.getNumeroSecreto());
        }
    }

    private static void configurarDificultad() {
        System.out.println("\n--- CONFIGURACIÓN DE DIFICULTAD ---");
        System.out.println("1. Fácil (Rango: 1 a 50 | 10 Intentos)");
        System.out.println("2. Normal (Rango: 1 a 100 | 7 Intentos)");
        System.out.println("3. Difícil (Rango: 1 a 200 | 5 Intentos)");

        int opcionDiff = leerEntero("Seleccione el nivel (1-3): ");

        switch (opcionDiff) {
            case 1:
                rangoMin = 1;
                rangoMax = 50;
                maxIntentos = 10;
                System.out.println("✅ Dificultad configurada a FÁCIL.");
                break;
            case 2:
                rangoMin = 1;
                rangoMax = 100;
                maxIntentos = 7;
                System.out.println("✅ Dificultad configurada a NORMAL.");
                break;
            case 3:
                rangoMin = 1;
                rangoMax = 200;
                maxIntentos = 5;
                System.out.println("✅ Dificultad configurada a DIFÍCIL.");
                break;
            default:
                System.out.println("⚠️ Opción no válida. Se mantiene la dificultad actual.");
        }
    }

    private static void mostrarInstrucciones() {
        System.out.println("\n--- INSTRUCCIONES DEL JUEGO ---");
        System.out.println("1. El programa genera un número aleatorio dentro del rango seleccionado.");
        System.out.println("2. Ingresa un número por consola en cada turno.");
        System.out.println("3. El sistema te dará pistas de si es más alto o más bajo.");
        System.out.println("4. Ganas si lo adivinas antes de agotar tus intentos.");
        System.out.println("5. Si ingresas letras o caracteres inválidos, el try-catch lo captura sin cerrar la aplicación.");
    }

    /**
     * Método auxiliar para capturar enteros por consola previniendo cierres por InputMismatchException.
     */
    private static int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                numero = scanner.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("❌ ERROR: Debe ingresar únicamente un número entero válido.");
                scanner.nextLine(); // Limpieza del buffer del scanner
            }
        }
        return numero;
    }
}