import Eventos.FutureEventList;
import Eventos.Arrival;
import Eventos.EndOfService;

public class Main{
	public static void main(String[] args){
		FutureEventList fel = new FutureEventList();
		fel.insert(new Arrival (567));
		fel.insert(new Arrival (234));
		fel.insert(new EndOfService (2523));
		fel.insert(new Arrival (0));
		fel.insert(new Arrival (23456));
	}
}
