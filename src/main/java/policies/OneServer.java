package policies;
import java.util.List;

import resources.Server;
import resources.ServerSelectionPolicy;

public class OneServer implements ServerSelectionPolicy {

    @Override
    public Server selectServer(List<Server> servers) {
        return servers.get(0);
    }
}
