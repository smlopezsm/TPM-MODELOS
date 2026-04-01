package Tablas;

import java.util.Random;

public class TiempoAleatorioTablaDos implements Runnable{
    private volatile int resultado;
    private final Random random = new Random();

    @Override
    public void run(){
        double prob = random.nextDouble();

        if(prob <= 0.38) {
            resultado = 8;
        } else if (prob <= 0.38 + 0.32) {
            resultado =10;
        }else if (prob <= (0.38 + 0.32 + 0.1)) {
            resultado = 13;
        }else {
            resultado =15;
        }
    }
    // ver las variables atomicas y las condiciones de cadenas
    public int getResultado(){
        return resultado;
    }
}
