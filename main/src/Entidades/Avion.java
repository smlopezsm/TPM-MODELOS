package Entidades;

import Recursos.Pista;

public class Avion {
    private int id;
    private double tiempoArribo;
    private double tiempoInicioAterrizaje;
    private Pista pistaAsignada;

    public Avion(int id, double tiempoArribo) {
        this.id = id;
        this.tiempoArribo = tiempoArribo;
    }

    public int getId() {
        return id;
    }

    public double getTiempoArribo() {
        return tiempoArribo;
    }

    public void setTiempoArribo(double tiempoArribo) {
        this.tiempoArribo = tiempoArribo;
    }

    public double getTiempoInicioAterrizaje() {
        return tiempoInicioAterrizaje;
    }

    public void setTiempoInicioAterrizaje(double tiempoInicioAterrizaje) {
        this.tiempoInicioAterrizaje = tiempoInicioAterrizaje;
    }

    public Pista getPistaAsignada() {
        return pistaAsignada;
    }

    public void setPistaAsignada(Pista pistaAsignada) {
        this.pistaAsignada = pistaAsignada;
    }

}
