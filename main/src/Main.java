package main;
import Eventos.Arrival;
import Eventos.Event;
import Eventos.FutureEventList;
import Generadores.DistribucionEmpiricaDiscreta;
import Recursos.Pista;
import Eventos.Estadisticas;
public class Main {
    public static void main(String[] args) {
        
        // new Engine(tiempoFinSimulacion, cantServer, distribuciones, aleatorizador).run();
        
        Pista pista = new Pista(1);
         FutureEventList lef = new FutureEventList();

        Engine engine = new Engine(40320,0,pista,lef,cantServer,distribuciones, aleatorizadoro);
        
        engine.run();
    }
}
