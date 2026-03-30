package Tablas;

public class Tabla_1 extends TiempoAleatorioTablaUno implements I1_Tabla {
    @Override
    public int tiempo_llegada() {
        return getResultado();
    }
}
