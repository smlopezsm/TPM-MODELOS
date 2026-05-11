import java.util.LinkedList;
import java.util.List;

import generators.DistribucionEmpiricaDiscreta;
import policies.OneServer;
import resources.Server;
import statistics.TerminalReportManager;

import java.util.ArrayList;

public class App {

    private static final double SIMULATION_LENGTH = 40320;
    private static final int NUMBER_OF_SERVERS = 1;

    public static void main(String[] args) {

        List<Server> servers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SERVERS; i++) {
            servers.add(new Server(i, new LinkedList<>()));
        }

        Engine engine = new Engine(
                SIMULATION_LENGTH,
                servers,
                new DistribucionEmpiricaDiscreta(new double[] { 10d, 15d, 17d }, new double[] { .35d, .45d, .2d }),
                new DistribucionEmpiricaDiscreta(new double[] { 8d, 10d, 13d, 15d },
                        new double[] {.38d, .32d, .1d, .2d }),
                new OneServer(),
                new TerminalReportManager());

        engine.run();
    }
}
/*
 * Comentarios del profe danilo
 * Por que hay instanof para decidir que tipo de evento voy a ejecutar? se
 * supone que uso polimorfismo, y no lo estan haciendo, vuelvan a prog 2
 * Sera que el metodo execute de la insterface evento estaba mal hecho? Se
 * cambio a lista de pistas
 *
 * para seleccionar pistas quedo acoplado en la clase arrivo. Que pasa si quiero
 * cambiar la politica de asignacion!? Tengo que cambiar codigo cierto?
 * Desacoplar esto
 * Resolver lo del null:
 * "El profe dice que si dise;an bien el simulador el problema se va a ir solo".
 *
 * Y dice que vengan a una consulta. El profe dice:
 * "Dejen de usar chat porque tira cualquier cosa, y no reutilicen codigos de otros equipos para hacer el simulador, porque no se complementa con el dise;o del simulador"
 *
 */