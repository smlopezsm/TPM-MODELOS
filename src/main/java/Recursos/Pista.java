package Recursos;

import java.util.LinkedList;
import java.util.Queue;
import Entidades.Avion;

public class Pista {
    //private int id;
    private boolean ocupada;
    private Queue<Avion> colaDeEspera;
    private int maxTamCola;

    public Pista(int id) {
        //this.id = id;
        this.ocupada = false;
        this.colaDeEspera = new LinkedList<>();
        this.maxTamCola = 0;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void agregarACola(Avion avion) {
        colaDeEspera.add(avion);
        int tamActual = colaDeEspera.size();

        if (tamActual > maxTamCola) {
            maxTamCola = tamActual;
        }
    }

    public int getMaxTamanoCola() {
        return maxTamCola;
    }

    public Avion sacarDeCola() {
        return colaDeEspera.poll();
    }

    public boolean hayCola() {
        return !colaDeEspera.isEmpty();
    }
    
    public int getTamanoCola() {
        return colaDeEspera.size();
    }
}
