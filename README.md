# Simulador de Sistema de Aterrizaje

Simulación de eventos discretos de un sistema de aterrizaje de aviones con una pista.

## Estructura del Proyecto

```
main/src/
├── Main.java                      # Punto de entrada (simula hasta t=100)
├── Entidades/
│   └── Avion.java                 # Entidad avión con tiempos de arribo/aterrizaje
├── Eventos/
│   ├── Event.java                 # Interfaz para eventos
│   ├── Arrival.java               # Evento de llegada de avión
│   ├── EndOfService.java          # Evento de fin de servicio (aterrizaje)
│   └── FutureEventList.java       # Lista de eventos futuros (FEL)
├── Recursos/
│   └── Pista.java                 # Pista con cola de espera
└── Tablas/
    ├── DiscreteveProbability.java       # Interfaz funcional
    ├── TiempoAleatorioTablaUno.java       # Tiempos entre llegadas
    └── TiempoAleatorioTablaDos.java       # Tiempos de aterrizaje
```

## Distribución de Tiempos

**Tabla 1 - Tiempos entre llegadas:**
| Probabilidad | Tiempo |
|--------------|--------|
| 35% | 10 min |
| 45% | 15 min |
| 20% | 17 min |

**Tabla 2 - Tiempos de aterrizaje:**
| Probabilidad | Tiempo |
|--------------|--------|
| 38% | 8 min |
| 32% | 10 min |
| 10% | 13 min |
| 20% | 15 min |

## Compilación y Ejecución

```bash
cd main/src
javac Main.java
java Main
```

## Modelo

- **Recursos**: 1 pista de aterrizaje
- **Cola**: FIFO (primero en llegar, primero en aterrizar)
- **Eventos**: Arrival (llegada) y EndOfService (fin de servicio)
- **Bootstrap**: El primer evento es una llegada en t=0
