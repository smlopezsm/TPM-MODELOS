package Recursos;

import java.util.LinkedList;
import java.util.Queue;
import Entidades.Avion;

public class Pista {
    private int id;
    private boolean ocupada;
    private Queue<Avion> colaDeEspera;
    private int maxTamCola;
    private int minTamCola;

    public Pista(int id) {
        this.id = id;
        this.ocupada = false;
        this.colaDeEspera = new LinkedList<>();
        this.maxTamCola = 0;
        this.minTamCola = Integer.MAX_VALUE;
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

        if (tamActual > 0 && tamActual < minTamCola) {
            minTamCola = tamActual;
        }
    }

    public int getMaxTamañoCola() {
        return maxTamCola;
    }

    public int getMinTamañoCola() {
        return (minTamCola == Integer.MAX_VALUE) ? 0 : minTamCola; 
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
