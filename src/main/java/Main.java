

import Eventos.Arrival;
import Eventos.Event;
import Eventos.FutureEventList;
import Generadores.DistribucionEmpiricaDiscreta;
import Recursos.Pista;
import Eventos.Estadisticas;
public class Main {
    public static void main(String[] args) {
        double tiempoFinSimulacion = 40320;
        double clock = 0;

        Pista pista = new Pista(1);
        FutureEventList lef = new FutureEventList();

        Estadisticas.getInstancia().iniciarOcio(0);

        lef.insert(new Arrival(0));

        try {
            while (clock <= tiempoFinSimulacion) {
                Event eventoActual = lef.inminent();
                clock = eventoActual.clock();
                //if (clock > tiempoFinSimulacion) break;
                eventoActual.execute(pista, lef);

               /*System.out.println(String.format("[%.0f] Ejecutando: %-15s | Cola en pista: %d",
                        clock,
                        eventoActual.getClass().getSimpleName(),
                        pista.getMaxTamanoCola()));*/
            }
            
            if (pista.isOcupada()) {
                Estadisticas.getInstancia().finalizarOcio(clock);
            }

            Estadisticas.getInstancia().mostrarReporte(clock, pista.getMaxTamanoCola(), pista.getMinTamanoCola());


        } catch (Exception e) {
            System.err.println("Error en la ejecución de la lista de eventos: " + e.getMessage());
        }


        //Prueba de  CFC con tabla 2
        double[] valores = {8.00,10.00,13.00,15.00};
        double[] prob = {0.38,0.32,0.1,0.2};
        DistribucionEmpiricaDiscreta D = new DistribucionEmpiricaDiscreta(valores, prob);

        for (int i = 0; i < 4; i++) {
            System.out.println(D.generar());
        }



    }


        //Prueba de  CFC con tabla 2
        double[] valores = {8.00,10.00,13.00,15.00};
        double[] prob = {0.38,0.32,0.1,0.2};
        DistribucionEmpiricaDiscreta D = new DistribucionEmpiricaDiscreta(valores, prob);

        for (int i = 0; i < 4; i++) {
            System.out.println(D.generar());
        }




    }
}
