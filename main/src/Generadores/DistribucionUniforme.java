package Generadores;

import java.util.Random;

public class DistribucionUniforme implements IGeneradorAleatorio {

    private final double min;
    private final double max;
    private final Random random;

    public DistribucionUniforme(double min, double max) {
        if (min >= max) throw new IllegalArgumentException("min debe ser menor que max");
        this.min = min;
        this.max = max;
        this.random = new Random();
    }

    /** Constructor con semilla (útil para pruebas reproducibles). */
    public DistribucionUniforme(double min, double max, long semilla) {
        this(min, max);
        this.random.setSeed(semilla);
    }

    @Override
    public double generar() {
        // U(a,b) = a + (b - a) * U(0,1)
        return min + (max - min) * random.nextDouble();
    }

    @Override
    public String toString() {
        return String.format("Uniforme(%.2f, %.2f)", min, max);
    }
}