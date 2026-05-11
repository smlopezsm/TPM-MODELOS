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

    Engine engine = new Engine(403,0,
        pistas,lef/*,cantServer,distribuciones, aleatorizador*/);
        
        engine.run();
    }
}
/*
* Comentarios del profe danilo
* Por que hay instanof para decidir que tipo de evento voy a ejecutar? se supone que uso polimorfismo, y no lo estan haciendo, vuelvan a prog 2
* Sera que el metodo execute de la insterface evento estaba mal hecho? Se cambio a lista de pistas
*
* para seleccionar pistas quedo acoplado en la clase arrivo. Que pasa si quiero cambiar la politica de asignacion!? Tengo que cambiar codigo cierto? Desacoplar esto
* Resolver lo del null: "El profe dice que si dise;an bien el simulador el problema se va a ir solo".
*
* Y dice que vengan a una consulta. El profe dice: "Dejen de usar chat porque tira cualquier cosa, y no reutilicen codigos de otros equipos para hacer el simulador, porque no se complementa con el dise;o del simulador"
*
* */