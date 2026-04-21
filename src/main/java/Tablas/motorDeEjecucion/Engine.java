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
        Estadisticas.getInstancia().iniciarOcio(0);

        lef.insert(new Arrival(0));

        try {
            while (clock <= tiempoFinSimulacion) {
                Event eventoActual = lef.inminent();
                clock = eventoActual.clock();
                //if (clock > tiempoFinSimulacion) break;
                if (eventoActual instanceof Arrival) {
                    ((Arrival) eventoActual).execute(pistas.get(0), lef, pistas);
                } else {
                    eventoActual.execute(pistas.get(0), lef);
                }

               /*System.out.println(String.format("[%.0f] Ejecutando: %-15s | Cola en pista: %d",
                        clock,
                        eventoActual.getClass().getSimpleName(),
                        pista.getMaxTamanoCola()));*/

                  
            }
            
            if (pistas.get(0).isOcupada()) {
                Estadisticas.getInstancia().finalizarOcio(clock);
            }

            //etapa1
            //Estadisticas.getInstancia().mostrarReporte(clock, pistas.get(0).getMaxTamanoCola());


        } catch (Exception e) {
            System.err.println("Error en la ejecución de la lista de eventos: " + e.getMessage());
        }


        //Prueba de  CFC con tabla 2
        // double[] valores = {8.00,10.00,13.00,15.00};
        // double[] prob = {0.38,0.32,0.1,0.2};
        // DistribucionEmpiricaDiscreta D = new DistribucionEmpiricaDiscreta(valores, prob);

        // for (int i = 0; i < 4; i++) {
        //     System.out.println(D.generar());
        // }
    }
}
