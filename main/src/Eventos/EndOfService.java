package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaUno;

public class EndOfService implements Event {
	private double clock;
	private int order = 200;
    private Avion avionSaliendo;
    private TiempoAleatorioTablaUno tabla1 = new TiempoAleatorioTablaUno();
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

            double tiempoAterrizaje;
            tiempoAterrizaje = tabla1.delta();
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
