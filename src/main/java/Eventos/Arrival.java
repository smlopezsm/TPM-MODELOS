package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaDos;
import Tablas.TiempoAleatorioTablaUno;

public class Arrival implements Event {
	private double clock;
	private int order = 100;
	private static int contadorAviones = 1;
    private TiempoAleatorioTablaDos tabla2 = new TiempoAleatorioTablaDos();
    private  TiempoAleatorioTablaUno tabla1 = new TiempoAleatorioTablaUno();
	public Arrival(double clock){
		this.clock = clock;
	}

    @Override
    public void execute(Pista pista, FutureEventList fel)  {
        Estadisticas.getInstancia().registrarArribo();
        Avion nuevoAvion = new Avion(contadorAviones++, this.clock);

        if (pista.isOcupada()) {
            pista.agregarACola(nuevoAvion);
        } else {
            Estadisticas.getInstancia().finalizarOcio(this.clock);
            pista.setOcupada(true);
            nuevoAvion.setTiempoInicioAterrizaje(this.clock);
            
            double tiempoAterrizaje;
            tiempoAterrizaje = tabla2.delta();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, nuevoAvion));
        }

        double tiempoParaElProximo;
        tiempoParaElProximo = tabla1.delta();
        fel.insert(new Arrival(this.clock + tiempoParaElProximo));
    }

    @Override
    public double clock() {return this.clock;}

    @Override
    public int order() {return this.order;}

}