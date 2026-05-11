package events;

import java.util.List;

import entities.Entity;
import resources.Server;
import generators.Distribution;

public class EndOfService implements Event {

    private final double clock;
    private final int order = 200;
    private final Entity entity;
    private final Distribution distribution;

    public EndOfService(double clock, Entity entity, Distribution distribution) {
        this.clock = clock;
        this.entity = entity;
        this.distribution = distribution;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers) {

        Server server = this.entity.server();

        if (server.queue().size() > 0) {

            Entity nextEntity = server.queue().poll();
            server.entity(nextEntity);

            double serviceTime = this.distribution.sample();

            fel.insert(new EndOfService(this.clock + serviceTime, nextEntity, this.distribution));

        } else {
            server.entity(null);
        }
    }

    @Override
    public double clock() {
        return this.clock;
    }

    @Override
    public int order() {
        return this.order;
    }

    @Override
    public Entity entity() {
        return this.entity;
    }

    @Override
    public Distribution distribution() {
        return this.distribution;
    }
}
