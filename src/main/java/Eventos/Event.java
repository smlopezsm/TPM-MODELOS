package Eventos;

import Recursos.Pista;

import java.util.List;

public interface Event{
	double clock();
	int order();
    void execute(List<Pista> pistas, FutureEventList fel) ;
}