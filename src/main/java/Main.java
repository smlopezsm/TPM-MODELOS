import Eventos.FutureEventList;
import Recursos.Pista;
import Tablas.motorDeEjecucion.Engine;

public class Main {
    public static void main(String[] args) {
        
        // new Engine(tiempoFinSimulacion, cantServer, distribuciones, aleatorizador).run();
        
        Pista pista = new Pista(1);
        FutureEventList lef = new FutureEventList();
        //int cantServer = 1;

    Engine engine = new Engine(40320,0,pista,lef/*,cantServer,distribuciones, aleatorizador*/);
        
        engine.run();
    }
}
