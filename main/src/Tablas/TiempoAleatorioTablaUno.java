package Tablas;

import java.util.Random;
public class TiempoAleatorioTablaUno implements Runnable{

    private volatile int resultado;
    private final Random random = new Random();

    @Override
    public void run() {
        double prob = random.nextDouble();

        if (prob <= 0.35) {
            resultado = 10;
        } else if (prob <= 0.35 + 0.45) {
            resultado = 15;
        } else {
            resultado = 17;
        }
    }

    public int getResultado() {
        return resultado;
    }
}
