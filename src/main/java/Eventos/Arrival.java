package Eventos;

import Entidades.Avion;
import Recursos.Pista;
import Tablas.TiempoAleatorioTablaDos;
//esto es de la etapa1
//import Tablas.TiempoAleatorioTablaUno;
import Generadores.DistribucionExponencial;
import Generadores.DistribucionUniforme;

public class Arrival implements Event {
	private double clock;
	private int order = 100;
	private static int contadorAviones = 1;
    private TiempoAleatorioTablaDos tabla2 = new TiempoAleatorioTablaDos();
    //esto es de la etapa1
    //private  TiempoAleatorioTablaUno tabla1 = new TiempoAleatorioTablaUno();
    private DistribucionUniforme distribucionUniforme = new DistribucionUniforme(10,25);
    private DistribucionExponencial distribucionExponencial_15 = new DistribucionExponencial(15);
    private DistribucionExponencial distribucionExponencial_9 = new DistribucionExponencial(9);
	public Arrival(double clock){
		this.clock = clock;
	}

    @Override
    public void execute(Pista pista, FutureEventList fel)  {
        execute(pista, fel, null);
    }

    public void execute(Pista pista, FutureEventList fel, java.util.List<Pista> todasLasPistas) {
        Estadisticas.getInstancia().registrarArribo();
        Avion nuevoAvion = new Avion(contadorAviones++, this.clock);

        Pista pistaSeleccionada = null;

        if (todasLasPistas != null) {
            pistaSeleccionada = buscarPistaLibre(todasLasPistas);
            if (pistaSeleccionada == null) {
                pistaSeleccionada = buscarPistaColaMasCorta(todasLasPistas);
            }
        } else {
            pistaSeleccionada = pista;
        }

        if (pistaSeleccionada == null || pistaSeleccionada.isOcupada()) {
            if (todasLasPistas != null) {
                pistaSeleccionada = buscarPistaColaMasCorta(todasLasPistas);
            } else {
                pistaSeleccionada = pista;
            }
        }

        if (pistaSeleccionada.isOcupada()) {
            pistaSeleccionada.agregarACola(nuevoAvion);
        } else {
            Estadisticas.getInstancia().finalizarOcio(this.clock);
            pistaSeleccionada.setOcupada(true);
            nuevoAvion.setTiempoInicioAterrizaje(this.clock);
            nuevoAvion.setPistaAsignada(pistaSeleccionada);
            
            double tiempoAterrizaje;
            tiempoAterrizaje = distribucionUniforme.generar();
            fel.insert(new EndOfService(this.clock + tiempoAterrizaje, nuevoAvion));
        }

        double tiempoParaElProximo;
        int hora = (int)((clock / 60) % 24);
        if ((hora >= 9 && hora < 13) || (hora >= 20 && hora < 23)) {
            tiempoParaElProximo = distribucionExponencial_9.generar();
            
        } else{
            tiempoParaElProximo = distribucionExponencial_15.generar();
        }
        //esto es de la etapa 1
        //tiempoParaElProximo = tabla1.delta();

        //pistas.getTamanoCola().min

        fel.insert(new Arrival(this.clock + tiempoParaElProximo));
    }

    @Override
    public double clock() {return this.clock;}

    @Override
    public int order() {return this.order;}

    private Pista buscarPistaLibre(java.util.List<Pista> pistas) {
        for (Pista pista : pistas) {
            if (!pista.isOcupada()) {
                return pista;
            }
        }
        return null;
    }

    private Pista buscarPistaColaMasCorta(java.util.List<Pista> pistas) {
        Pista pistaMinCola = pistas.get(0);
        int minCola = pistaMinCola.getTamanoCola();

        for (Pista pista : pistas) {
            int tamCola = pista.getTamanoCola();
            if (tamCola < minCola) {
                minCola = tamCola;
                pistaMinCola = pista;
            }
        }
        return pistaMinCola;
    }

}