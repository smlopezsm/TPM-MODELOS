import java.util.List;

import entities.Entity;
import events.Arrival;
import events.Event;
import events.FutureEventList;
import generators.Distribution;
import resources.Server;
import resources.ServerSelectionPolicy;
import statistics.ReportManager;

public class Engine {

    private final double simulationLenght;
    private final List<Server> servers;
    private final FutureEventList fel;
    private final ReportManager reportManager;
    // private int cantServer;
    // private DistribucionEmpiricaDiscreta[] distribuciones;
    // private Aleatorizador aleatorizador;

    public Engine(
            double simulationLenght,
            List<Server> servers,
            Distribution arrivalBehavior,
            Distribution serviceBehavior,
            ServerSelectionPolicy serverSelectionPolicy,
            ReportManager reportManager) {

        this.simulationLenght = simulationLenght;
        this.servers = servers;
        this.reportManager = reportManager;

        this.fel = new FutureEventList();

        this.fel.insert(
                new Arrival(0, new Entity(0, 0), arrivalBehavior, serviceBehavior, serverSelectionPolicy));
    }

    public void run() {

        Event e = this.fel.imminent();
        while (e.clock() <= simulationLenght) {
            e.planificate(this.fel, this.servers);
            e = this.fel.imminent();
        }
    }
}
