
import Eventos.Arrival;
import Eventos.Event;
import Eventos.FutureEventList;
import Recursos.Pista;
import Eventos.Estadisticas;
public class Main {
    public static void main(String[] args) {
        double tiempoFinSimulacion = 40320;
        double clock = 0;

        Pista pista = new Pista(1);
        FutureEventList lef = new FutureEventList();

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


        } catch (InterruptedException e) {
            // Manejamos la excepción que arrojan tus hilos de las tablas aleatorias
            System.err.println("La simulación fue interrumpida inesperadamente.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error en la ejecución de la lista de eventos: " + e.getMessage());
        }
    }
}
