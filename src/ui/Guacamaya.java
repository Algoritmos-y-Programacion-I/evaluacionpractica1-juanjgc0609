package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    public static int referencias;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    /**
     * Descripción: Este método se encarga de iniciar las variables globales.
     * pre: El Scanner reader debe estar declarado.
     * pos: El Scanner reader queda inicializado.
     */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripción: Este método se encarga de desplegar el menú al usuario y mostrar los mensajes de resultado para cada funcionalidad.
     * pre: El Scanner reader debe estar inicializado.
     * pre: El arreglo precios debe estar inicializado.
     * pos: Se ejecutan las funcionalidades del menú según la elección del usuario.
     */
    public static void menu() {
        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el día");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el día");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el día");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el día");
            System.out.println("5. Consultar el número de referencias de productos que en el día han superado un límite mínimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opción a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el día fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el día fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el día fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el límite mínimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el día, " + consultarReferenciasSobreLimite(limite) + " superaron el límite mínimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (!salir);
    }

    /**
     * Descripción: Este método se encarga de preguntar al usuario el número de referencias de producto diferentes vendidas en el día e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades.
     * pre: El Scanner reader debe estar inicializado.
     * pre: Los arreglos precios y unidades deben estar declarados.
     * pos: Los arreglos precios y unidades quedan inicializados.
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el número de referencias de producto diferentes vendidas en el día ");
        referencias = reader.nextInt(); // Uso de la variable global 'referencias'

        precios = new double[referencias];
        unidades = new int[referencias];
    }

    /**
     * Descripción: Este método solicita los precios y cantidades de cada referencia.
     * pre: El número de referencias ha sido establecido.
     * pos: Los arreglos precios y unidades contienen los datos ingresados por el usuario.
     */
    public static void solicitarDatos() {
        for (int i = 0; i < referencias; i++) {
            System.out.print("Ingrese el precio de la referencia " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            System.out.print("Ingrese las unidades de la referencia " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Descripción: Este método calcula el total de unidades vendidas en el día.
     * pre: Los arreglos precios y unidades deben estar inicializados y contener los datos ingresados por el usuario.
     * pos: Devuelve la cantidad total de unidades vendidas.
     * @return El total de unidades vendidas en el día.
     */
    public static int calcularTotalUnidadesVendidas() {
        int totalUnidades = 0;
        for (int i = 0; i < referencias; i++) {
            totalUnidades += unidades[i];
        }
        return totalUnidades;
    }

    /**
     * Descripción: Este método calcula el precio promedio de las referencias de productos vendidas en el día.
     * pre: El arreglo precios debe estar inicializado.
     * pos: Devuelve el precio promedio de los productos vendidos.
     * @return El precio promedio de las referencias vendidas en el día.
     */
    public static double calcularPrecioPromedio() {
        double totalPrecio = 0;
        for (int i = 0; i < referencias; i++) {
            totalPrecio += precios[i];
        }
        return totalPrecio / referencias;
    }

    /**
     * Descripción: Este método calcula las ventas totales en el día.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * pos: Devuelve el total de ventas en dinero durante el día.
     * @return Las ventas totales (dinero recaudado) durante el día.
     */
    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < referencias; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    /**
     * Descripción: Este método consulta cuántas referencias superan un límite mínimo de ventas.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * @param limite El límite mínimo de ventas a analizar.
     * @return El número de referencias que superan el límite mínimo de ventas.
     */
    public static int consultarReferenciasSobreLimite(double limite) {
        int referenciasSobreLimite = 0;
        for (int i = 0; i < referencias; i++) {
            if (precios[i] * unidades[i] > limite) {
                referenciasSobreLimite++;
            }
        }
        return referenciasSobreLimite;
    }
}

