package Tablas.motorDeEjecucion;

import Recursos.Pista;
import Eventos.Arrival;
import Eventos.Event;
import Eventos.FutureEventList;
import Eventos.Estadisticas;
import Generadores.DistribucionEmpiricaDiscreta;
import java.util.List;
import java.util.ArrayList;


public class Engine {

    private double clock;
    private double tiempoFinSimulacion;
    private List<Pista> pistas;
    private FutureEventList lef;
    //private int cantServer;
    //private DistribucionEmpiricaDiscreta[] distribuciones;
    //private Aleatorizador aleatorizador;
    
    public Engine(double tiempoFinSimulacion, double clock, List<Pista> pistas, FutureEventList lef/*, int cantServer, DistribucionEmpiricaDiscreta[] distribuciones ,Aleatorizador aleatorizador*/) {
        this.tiempoFinSimulacion = tiempoFinSimulacion;
        this.clock = clock;
        this.pistas = pistas;
        this.lef = lef;
        //this.cantServer = cantServer;
        //this.distribuciones = distribuciones;
        //this.aleatorizador = aleatorizador;
        
    }

    public void run() {

        Estadisticas.getInstancia().inicializarPistas(pistas.size());
        for (Pista p:pistas) {Estadisticas.getInstancia().iniciarOcio(p.getId(),clock);}

        lef.insert(new Arrival(0));

        try {
            while (clock <= tiempoFinSimulacion) {

                Event eventoActual = lef.inminent();
                clock = eventoActual.clock();
                //if (clock > tiempoFinSimulacion) break;
                if (eventoActual instanceof Arrival) {
                    ((Arrival) eventoActual).execute(null, lef, pistas);
                } else {
                    eventoActual.execute(null, lef);
                }

               /*System.out.println(String.format("[%.0f] Ejecutando: %-15s | Cola en pista: %d",
                        clock,
                        eventoActual.getClass().getSimpleName(),
                        pista.getMaxTamanoCola()));*/

                  
            }
            for (Pista p : pistas) {
                if (!p.isOcupada()) {
                    Estadisticas.getInstancia().finalizarOcio(p.getId(),clock);
                }
            }
           /* if (pistas.get(0).isOcupada()) {
                Estadisticas.getInstancia().finalizarOcio(clock);
            }*/

            //etapa1
            Estadisticas.getInstancia().mostrarReporte(clock, pistas);


        } catch (Exception e) {
            System.err.println("Error en la ejecución de la lista de eventos: " + e.getMessage());
        }
        
    }

}
