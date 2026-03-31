package Recursos;

import java.util.LinkedList;
import java.util.Queue;
import Entidades.Avion;

public class Pista {
    private int id;
    private boolean ocupada;
    private Queue<Avion> colaDeEspera;

    public Pista(int id) {
        this.id = id;
        this.ocupada = false;
        this.colaDeEspera = new LinkedList<>();
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void agregarACola(Avion avion) {
        colaDeEspera.add(avion);
    }

    public Avion sacarDeCola() {
        return colaDeEspera.poll();
    }

    public boolean hayCola() {
        return !colaDeEspera.isEmpty();
    }
    
    public int getTamañoCola() {
        return colaDeEspera.size();
    }
}
