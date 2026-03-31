package Eventos;

public class Arrival implements Event {
	private double clock;
	private int order;
	
	public Arrival(double clock){
		this.clock = clock;
		this.order = 100;
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