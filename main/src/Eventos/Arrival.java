package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaDos;
import Tablas.TiempoAleatorioTablaUno;

public class Arrival implements Event {
	private double clock;
	private int order = 100;
	private static int contadorAviones = 1;
    private TiempoAleatorioTablaDos t2 = new TiempoAleatorioTablaDos();
    private Thread t = new Thread(t2);
    private  TiempoAleatorioTablaUno t1 = new TiempoAleatorioTablaUno();
    private Thread t0 = new Thread(t1);
	public Arrival(double clock){
		this.clock = clock;
	}

    @Override
    public synchronized void execute(Pista pista, FutureEventList fel) throws InterruptedException {
        Avion nuevoAvion = new Avion(contadorAviones++, this.clock);

        if (pista.isOcupada()) {
            pista.agregarACola(nuevoAvion);
        } else {
            pista.setOcupada(true);
            nuevoAvion.setTiempoInicioAterrizaje(this.clock);
            
            //calculamos cuánto tarda en aterrizar (aca usar generador con la tabla 2)
            Thread t = new Thread(t2);
            t.start();
            t.join();
            double tiempoAterrizaje;
            tiempoAterrizaje = t2.getResultado();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, nuevoAvion));
        }

        //BOOTSTRAPPING (planificar el próximo arribo) calculamos cuanto falta para que llegue el sig avion usando la tabla 1
        Thread t0 = new Thread(t1);
        t0.start();
        t0.join();
        double tiempoParaElProximo;
        tiempoParaElProximo = t1.getResultado();
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