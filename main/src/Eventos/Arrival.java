package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaDos;
import Tablas.TiempoAleatorioTablaUno;

public class Arrival implements Event {
	private double clock;
	private int order = 100;
	private static int contadorAviones = 1;
    /*private TiempoAleatorioTablaDos tabla2 = new TiempoAleatorioTablaDos();
    private Thread t = new Thread(tabla2);
    private  TiempoAleatorioTablaUno tabla1 = new TiempoAleatorioTablaUno();
    private Thread t0 = new Thread(tabla1);*/
    private TiempoAleatorioTablaUno tabla1 = new TiempoAleatorioTablaUno();
    private TiempoAleatorioTablaDos tabla2 = new TiempoAleatorioTablaDos();
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
           /* Thread t = new Thread(tabla2);
            t.start();
            t.join();

            tiempoAterrizaje = tabla2.getResultado();*/
            double tiempoAterrizaje= tabla2.delta();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, nuevoAvion));
        }

        //BOOTSTRAPPING (planificar el próximo arribo) calculamos cuanto falta para que llegue el sig avion usando la tabla 1
        /*Thread t0 = new Thread(tabla1);
        t0.start();
        t0.join();
        tiempoParaElProximo = tabla1.getResultado();*/
        double tiempoParaElProximo=tabla1.delta();
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