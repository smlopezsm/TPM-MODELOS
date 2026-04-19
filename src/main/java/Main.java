import Eventos.FutureEventList;
import Recursos.Pista;
import Tablas.motorDeEjecucion.Engine;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        // new Engine(tiempoFinSimulacion, cantServer, distribuciones, aleatorizador).run();
        
        Pista pista1 = new Pista(1);
        Pista pista2 = new Pista(2);
        Pista pista3 = new Pista(3);
        Pista pista4 = new Pista(4);
        Pista pista5 = new Pista(5);
        List<Pista> pistas = Arrays.asList(pista1, pista2, pista3, pista4, pista5);

        FutureEventList lef = new FutureEventList();
        //int cantServer = 1;

    Engine engine = new Engine(40320,0,
        pistas,lef/*,cantServer,distribuciones, aleatorizador*/);
        
        engine.run();
    }
}
