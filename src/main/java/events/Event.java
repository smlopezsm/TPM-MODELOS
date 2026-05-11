package events;
import java.util.List;

import entities.Entity;
import generators.Distribution;
import resources.Server;

public interface Event {

	double clock();

	int order();

	Entity entity();

	Distribution distribution();

    void planificate(FutureEventList fel, List<Server> servers);

}
