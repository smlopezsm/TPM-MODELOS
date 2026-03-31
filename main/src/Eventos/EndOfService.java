package Eventos;

public class EndOfService implements Event {
	private double clock;
	private int order;
	
	public EndOfService(double clock){
		this.clock = clock;
        this.order = 200;
	}

    @Override
    public double clock() {
        return this.clock;
    }

    @Override
    public int order() {
        return this.order;
    }
}
