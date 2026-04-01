package Eventos;

import Recursos.Pista;

public interface Event{
	double clock();
	int order();
    void execute(Pista pista, FutureEventList fel);
}