package juego;

import java.util.Random;

/**
 * Clase que administra la lógica principal del juego Adivina el Número.
 * Maneja el rango, el número secreto, el contador de intentos y el estado del juego.
 */
public class Juego {

    private int numeroSecreto;
    private int intentosRestantes;
    private int maxIntentos;
    private int rangoMin;
    private int rangoMax;
    private boolean juegoGanado;

    // Constructor por defecto (Dificultad Normal: 1 a 100, 7 intentos)
    public Juego() {
        this(1, 100, 7);
    }

    // Constructor configurable para distintas dificultades
    public Juego(int rangoMin, int rangoMax, int maxIntentos) {
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
        this.maxIntentos = maxIntentos;
        this.intentosRestantes = maxIntentos;
        this.juegoGanado = false;

        // Generación del número aleatorio en el rango solicitado
        Random random = new Random();
        this.numeroSecreto = random.nextInt(rangoMax - rangoMin + 1) + rangoMin;
    }

    /**
     * Evalúa el número ingresado por el jugador y descuenta un intento.
     *
     * @param numero El número arriesgado por el usuario.
     * @return 0 si acertó, 1 si es demasiado alto, -1 si es demasiado bajo.
     */
    public int evaluarIntento(int numero) {
        intentosRestantes--;
        if (numero == numeroSecreto) {
            juegoGanado = true;
            return 0;
        } else if (numero > numeroSecreto) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean esFinDelJuego() {
        return juegoGanado || intentosRestantes <= 0;
    }

    // Getters
    public int getNumeroSecreto() {
        return numeroSecreto;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public int getMaxIntentos() {
        return maxIntentos;
    }

    public int getRangoMin() {
        return rangoMin;
    }

    public int getRangoMax() {
        return rangoMax;
    }

    public boolean isJuegoGanado() {
        return juegoGanado;
    }
}