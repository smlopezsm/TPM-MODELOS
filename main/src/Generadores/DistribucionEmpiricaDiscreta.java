package Generadores;
import java.util.Random;

/**
 * Distribución Empírica Discreta.
 * Dada una tabla de valores y sus probabilidades asociadas,
 * genera muestras mediante el método de la transformada inversa discreta.
 *
 * Ejemplo de uso:
 *   double[] valores = {1.0, 2.0, 3.0};
 *   double[] probs   = {0.2, 0.5, 0.3};
 *   IGeneradorAleatorio gen = new DistribucionEmpiricaDiscreta(valores, probs);
 */
public class DistribucionEmpiricaDiscreta implements IGeneradorAleatorio {

    private final double[] valores;
    private final double[] probAcumulada;  // tabla CDF
    private final Random random;

    public DistribucionEmpiricaDiscreta(double[] valores, double[] probabilidades) {
        if (valores.length != probabilidades.length)
            throw new IllegalArgumentException("Los arrays deben tener el mismo tamaño");

        this.valores = valores.clone();
        this.probAcumulada = construirCDF(probabilidades);
        this.random = new Random();
    }

    private double[] construirCDF(double[] probs) {
        double[] cdf = new double[probs.length];
        double suma = 0;
        for (int i = 0; i < probs.length; i++) {
            suma += probs[i];
            cdf[i] = suma;
        }
        // Normalizar en caso de error de punto flotante
        cdf[cdf.length - 1] = 1.0;

        double total = 0;
        for (double p : probs) total += p;
        if (Math.abs(total - 1.0) > 0.001)
            throw new IllegalArgumentException("Las probabilidades deben sumar 1 (suma actual: " + total + ")");

        return cdf;
    }

    @Override
    public double generar() {
        double u = random.nextDouble();
        for (int i = 0; i < probAcumulada.length; i++) {
            if (u <= probAcumulada[i]) return valores[i];
        }
        return valores[valores.length - 1]; // por seguridad
    }

    @Override
    public String toString() {
        return "EmpiricaDiscreta(" + valores.length + " valores)";
    }
}