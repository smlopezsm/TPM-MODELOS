package Tablas;

public class Tabla_2 extends TiempoAleatorioTablaDos implements I2_Tabla {
    @Override
    public int tiempo_despeje() {
        return getResultado();
    }
}
