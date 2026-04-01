package Eventos;

import Entidades.Avion;
import Eventos.FutureEventList;
import Recursos.Pista;

public class Arrival implements Event {
	private double clock;
	private int order = 100;
	private static int contadorAviones = 1;

	public Arrival(double clock){
		this.clock = clock;
	}

    @Override
    public void execute(Pista pista, FutureEventList fel){
        Avion nuevoAvion = new Avion(contadorAviones++, this.clock);

        if (pista.isOcupada()) {
            pista.agregarACola(nuevoAvion);
        } else {
            pista.setOcupada(true);
            nuevoAvion.setTiempoInicioAterrizaje(this.clock);
            
            //calculamos cuánto tarda en aterrizar (aca usar generador con la tabla 2)
            double tiempoAterrizaje = generarTiempoAterrizaje(); 
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, nuevoAvion));
        }

        //BOOTSTRAPPING (planificar el próximo arribo) calculamos cuanto falta para que llegue el sig avion usando la tabla 1
        double tiempoParaElProximo = generarTiempoEntreArribos();
        fel.insert(new Arrival(this.clock + tiempoParaElProximo));
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