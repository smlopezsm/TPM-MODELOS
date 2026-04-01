package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Eventos.FutureEventList;
import Tablas.TiempoAleatorioTablaUno;

public class EndOfService implements Event {
	private double clock;
	private int order = 200;
    private Avion avionSaliendo;
    private TiempoAleatorioTablaUno t1 = new TiempoAleatorioTablaUno();
    private Thread t0 = new Thread(t1);
	public EndOfService(double clock, Avion avion) {
        this.clock = clock;
        this.avionSaliendo = avion;
    }

    @Override
    public void execute(Pista pista, FutureEventList fel) throws InterruptedException {
        // (mandar estos datos a una clase "Estadisticas" global)
        double tiempoEspera = avionSaliendo.getTiempoInicioAterrizaje() - avionSaliendo.getTiempoArribo();
        double tiempoTransito = this.clock - avionSaliendo.getTiempoArribo();

        if (pista.hayCola()) {
            Avion proximoAvion = pista.sacarDeCola();

            proximoAvion.setTiempoInicioAterrizaje(this.clock);
            
            //el metodo generarTiempoAterrizaje() devuelve un número aleatorio según la Tabla 2
            Thread t0 = new Thread(t1);
            t0.start();
            t0.join();
            double tiempoAterrizaje;
            tiempoAterrizaje = t1.getResultado();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, proximoAvion));
        } else {
            pista.setOcupada(false);
        }
    }

    @Override
    public double clock() {
        return this.clock;
    }

    @Override
    public int order() {
        return this.order;
    }
}
