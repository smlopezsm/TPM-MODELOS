import Entidades.Avion;
import Eventos.Event;
import Eventos.FutureEventList;
import Eventos.Arrival;
import Eventos.EndOfService;
import Recursos.Pista;

import javax.xml.stream.util.EventReaderDelegate;

public class Main{
	public static void main(String[] args){
		FutureEventList fel = new FutureEventList();
        fel.insert(new Arrival(0));
		Pista p1= new Pista(1);
		double clock = 0;
        try {
            Event e = fel.inminent();
            clock = e.clock();
            while (clock < 100) {
                e.execute(p1,fel);
                clock = e.clock();
                System.out.println("Evento inminente: " + e);
                System.out.println("tiempo: " + clock);
                e = fel.inminent();
                clock = e.clock();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println("La simulacion fue interrumpida: " + ex.getMessage());
        }
	}
}
