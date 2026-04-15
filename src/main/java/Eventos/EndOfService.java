package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaDos;

public class EndOfService implements Event {
	private double clock;
	private int order = 200;
    private Avion avionSaliendo;
    private TiempoAleatorioTablaDos tabla2 = new TiempoAleatorioTablaDos();

	public EndOfService(double clock, Avion avion) {
        this.clock = clock;
        this.avionSaliendo = avion;
    }

    @Override
    public void execute(Pista pista, FutureEventList fel)  {
        // (mandar estos datos a una clase "Estadisticas" global)
        double tiempoEspera = avionSaliendo.getTiempoInicioAterrizaje() - avionSaliendo.getTiempoArribo();
        double tiempoTransito = this.clock - avionSaliendo.getTiempoArribo();
        Estadisticas.getInstancia().registrarAterrizaje(tiempoEspera, tiempoTransito);


        if (pista.hayCola()) {
            Avion proximoAvion = pista.sacarDeCola();

            proximoAvion.setTiempoInicioAterrizaje(this.clock);
            
            double tiempoAterrizaje;
            tiempoAterrizaje = tabla2.delta();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, proximoAvion));
        } else {
            pista.setOcupada(false);
            Estadisticas.getInstancia().iniciarOcio(this.clock);
        }
    }

    @Override
    public double clock() {return this.clock;}

    @Override
    public int order() {return this.order;}
}
