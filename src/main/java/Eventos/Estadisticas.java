package Eventos;

public class Estadisticas {
    private static Estadisticas instancia;

    private int totalArribos = 0;
    private int totalAterrizajes = 0;
    private double transitoTotal = 0;
    private double transitoMax = 0;
    private double esperaTotal = 0;
    private double esperaMax = 0;
    private double ocioTotal = 0;
    private double ocioMax = 0;
    private double inicioOcioActual = 0;

    private Estadisticas() {}

    public static Estadisticas getInstancia() {
        if (instancia == null) instancia = new Estadisticas();
        return instancia;
    }

    public void registrarArribo() {totalArribos++; }

    public void registrarAterrizaje(double espera, double transito) {
        totalAterrizajes++;
        esperaTotal += espera;
        if (espera > esperaMax) esperaMax = espera;
        transitoTotal += transito;
        if (transito > transitoMax) transitoMax = transito;
    }

    public void iniciarOcio(double clock) {
        inicioOcioActual = clock;
    }

    public void finalizarOcio(double clock) {
        double duracion = clock - inicioOcioActual;
        if (duracion > 0) {
            ocioTotal += duracion;
            if (duracion > ocioMax) ocioMax = duracion;
        }
    }

    public void mostrarReporte(double tiempoSimulacion, int maxCola) {
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
        System.out.println(String.format("Total: %.2f (%.2f%% del tiempo) | Máx: %.2f | Mín (>0): %.2f",
                ocioTotal, proporcionOcio, ocioMax));

        System.out.println("\n--- ESTADO DE LA COLA ---");
        System.out.println(String.format("Tamaño Máximo: %d | Tamaño Mínimo (>0): %d", maxCola));
        System.out.println("==================================================");
    }
}