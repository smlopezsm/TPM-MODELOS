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
		fel.insert(new Arrival (567));
		fel.insert(new Arrival (234));
		fel.insert(new EndOfService (2523, new Avion(1111, 2)));
		fel.insert(new Arrival (0));
		fel.insert(new Arrival (23456));

		Pista p1= new Pista(1);
		double clock = 0;
        try {
            while (clock < 40) {
                Event e = fel.inminent();
                e.execute(p1, fel);
                clock = e.clock();
                System.out.println(e);
                System.out.println(clock);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println("La simulacion fue interrumpida: " + ex.getMessage());
        }
	}
}
