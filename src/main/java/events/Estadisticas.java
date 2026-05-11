package events;

import java.util.List;

import resources.Pista;

public class Estadisticas {
    
    private static Estadisticas instancia; // Patron singleton

    private int totalArribos = 0;
    private int totalAterrizajes = 0;
    private double transitoTotal = 0;
    private double transitoMax = 0;
    private double esperaTotal = 0;
    private double esperaMax = 0;
   /* private double ocioTotal = 0;
    private double ocioMax = 0;
    private double inicioOcioActual = 0;*/
    private double[] ocioTotal;
    private double[] ocioMax;
    private double[] inicioOcioActual;
    private int maxColaGlobal = 0;

    private Estadisticas() {}

    public static Estadisticas getInstancia() {
        if (instancia == null) instancia = new Estadisticas();
        return instancia;
    }

    public void inicializarPistas(int cantidadPistas) {
        ocioTotal = new double[cantidadPistas];
        ocioMax = new double[cantidadPistas];
        inicioOcioActual = new double[cantidadPistas];
    }

    public void registrarArribo() {totalArribos++; }

    public void registrarAterrizaje(double espera, double transito) {
        totalAterrizajes++;
        esperaTotal += espera;
        if (espera > esperaMax) esperaMax = espera;
        transitoTotal += transito;
        if (transito > transitoMax) transitoMax = transito;
    }

    /*public void iniciarOcio(double clock) {
        inicioOcioActual = clock;
    }

    public void finalizarOcio(double clock) {
        double duracion = clock - inicioOcioActual;
        if (duracion > 0) {
            ocioTotal += duracion;
            if (duracion > ocioMax) ocioMax = duracion;
        }
    }*/
    public void iniciarOcio(int idPista, double clock) {
        if (idPista < inicioOcioActual.length) {
            inicioOcioActual[idPista] = clock;
        }}
    public void registrarTamanoCola(int tamano) {
        if (tamano > maxColaGlobal) maxColaGlobal = tamano;
    }
        public void finalizarOcio(int idPista, double clock) {
            if (idPista < ocioTotal.length) {
                double duracion = clock - inicioOcioActual[idPista];
                if (duracion > 0) {
                    ocioTotal[idPista] += duracion;
                    if (duracion > ocioMax[idPista]) ocioMax[idPista] = duracion;
                }
            }
        }

   /* public void mostrarReporte(double tiempoSimulacion, int maxCola) {
        System.out.println("\n================ REPORTES ETAPA 1 ================");
        System.out.println(String.format("Arribos Totales: %d | Aterrizajes Totales: %d", totalArribos, totalAterrizajes));

        System.out.println("\n--- TIEMPOS DE TRANSITO (SISTEMA) ---");
        System.out.println(String.format("Media: %.2f | Máx: %.2f",
                (totalAterrizajes > 0 ? transitoTotal/totalAterrizajes : 0), transitoMax));

        System.out.println("\n--- TIEMPOS DE ESPERA (COLA) ---");
        System.out.println(String.format("Media: %.2f | Máx: %.2f",
                (totalAterrizajes > 0 ? esperaTotal/totalAterrizajes : 0), esperaMax));

        System.out.println("\n--- TIEMPOS DE OCIO (PISTA) ---");
        double proporcionOcio = (ocioTotal / tiempoSimulacion) * 100;
        System.out.println(String.format("Total: %.2f (%.2f%% del tiempo) | Máx: %.2f",
                ocioTotal, proporcionOcio, ocioMax));

        System.out.println("\n--- ESTADO DE LA COLA ---");
        System.out.println(String.format("Tamaño Máximo: %d", maxCola));
        System.out.println("==================================================");
    }*/
        public void mostrarReporte(double tiempoSimulacion, List<Pista> pistas) {
            System.out.println("\n================ REPORTES DE SIMULACIÓN ================");
            System.out.println(String.format("Arribos Totales: %d | Aterrizajes Totales: %d", totalArribos, totalAterrizajes));

            System.out.println("\n--- TIEMPOS DE TRÁNSITO (SISTEMA) ---");
            System.out.println(String.format("Media: %.2f | Máximo: %.2f",
                    (totalAterrizajes > 0 ? transitoTotal / totalAterrizajes : 0), transitoMax));

            System.out.println("\n--- TIEMPOS DE ESPERA (COLA) ---");
            System.out.println(String.format("Media: %.2f | Máximo: %.2f",
                    (totalAterrizajes > 0 ? esperaTotal / totalAterrizajes : 0), esperaMax));

            System.out.println("\n--- ESTADÍSTICAS POR PISTA ---");
            for (int i = 0; i < pistas.size(); i++) {
                Pista p = pistas.get(i);
                double propOcio = (tiempoSimulacion > 0) ? (ocioTotal[i] / tiempoSimulacion) * 100 : 0;

                System.out.println(String.format("Pista %d:", i + 1));
                System.out.println(String.format("  - Tiempo de Ocio Total: %.2f (%.2f%% del tiempo total)", ocioTotal[i], propOcio));
                System.out.println(String.format("  - Tiempo de Ocio Máximo: %.2f", ocioMax[i]));
                System.out.println(String.format("  - Tamaño Máximo de la Cola: %d", p.getMaxTamanoCola()));
                System.out.println(String.format("  - Durabilidad: %.2f", p.getDurabilidad()));
            }
            System.out.println("========================================================");
        }
}