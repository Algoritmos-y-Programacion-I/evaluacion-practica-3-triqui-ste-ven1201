package ui;

import java.util.Scanner;
import model.Controladora;

public class Executable {

    private Scanner reader;
    private Controladora cont;
    private static boolean flag;

    // Constructor
    private Executable() {
        reader = new Scanner(System.in);
        cont = new Controladora();
    }

    // Método principal para ejecutar el juego
    public void run() {
        flag = false;

        while (!flag) {
            System.out.println("\n\nBienvenido al menú:\n");
            System.out.println("Opciones:\n" + 
                               "1. Imprimir tablero \n" + 
                               "2. Jugada de la máquina \n" +
                               "3. Jugada de humano \n" + 
                               "4. Verificar ganador \n" + 
                               "5. Salir del programa \n");

            int option = reader.nextInt();
            reader.nextLine();  // Limpiar el buffer de entrada

            System.out.println("Opción seleccionada: " + option); // Mensaje de depuración

            switch (option) {
                case 1:
                    imprimirTablero();
                    break;
                case 2:
                    jugadaMaquina();
                    break;
                case 3:
                    jugadaHumano();
                    break;
                case 4:
                    verificarGanador();  // Moved to a separate method to check the winner
                    break;
                case 5:
                    flag = true;
                    System.exit(0);  // Salir del programa
                    break;
                default:
                    System.out.print("Por favor ingrese una opción válida.");
                    continue;
            }
        }
    }

    // Método principal de ejecución
    public static void main(String[] args) {
        Executable mainApp = new Executable();
        mainApp.run();
    }

    // Método para imprimir el tablero
    private void imprimirTablero() {
        System.out.println(cont.obtenerTableroComoString());
    }

    // Método para la jugada de la máquina
    private void jugadaMaquina() {
        cont.jugadaAleatoria();
        System.out.println("La máquina ha realizado su jugada.");
        imprimirTablero();
        verificarGanador();  // Verificar si la máquina ha ganado
    }

    // Método para la jugada del humano
    private void jugadaHumano() {
        boolean jugadaValida = true;
        System.out.println("Opción seleccionada: 3");

        while (jugadaValida) {
            System.out.println("Tu turno. Ingresa las coordenadas (fila y columna, de 0 a 2):");
            try {
                int i = reader.nextInt();
                int j = reader.nextInt();
                reader.nextLine();  // Limpiar el buffer de entrada

                // Verificar si las coordenadas son válidas
                if (i < 0 || i > 2 || j < 0 || j > 2) {
                    System.out.println("¡Coordenadas fuera de rango! Ingresa valores entre 0 y 2.");
                } else {
                    // Intentamos realizar la jugada
                    boolean jugadaRealizada = cont.realizarJugadaHumana(i, j);

                    // Si la jugada fue exitosa
                    if (jugadaRealizada) {
                        System.out.println("¡Jugada realizada con éxito!");
                        imprimirTablero();

                        // Verificar si hay un ganador
                        String ganador = cont.validarGanador();
                        if (ganador != null) {
                            if (ganador.equals("Empate")) {
                                System.out.println("¡Es un empate!");
                            } else {
                                System.out.println("¡El ganador es: " + ganador + "!");
                            }
                            flag = true;  // Terminar el juego
                            break; // Salir del bucle
                        }

                        jugadaValida = false;  // Jugada válida, salimos del bucle
                    } else {
                        // Si la jugada no es válida (la casilla está ocupada o es incorrecta)
                        System.out.println("Movimiento inválido. La casilla está ocupada o las coordenadas son incorrectas. Intenta de nuevo.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asegúrate de ingresar dos números entre 0 y 2.");
                reader.nextLine();  // Limpiar el buffer de entrada
            }
        }
    }

    
    private void verificarGanador() {
        String ganador = cont.validarGanador();
        if (ganador != null) {
            if (ganador.equals("Empate")) {
                System.out.println("¡Es un empate!");
            } else {
                System.out.println("¡El ganador es: " + ganador + "!");
            }
            flag = true;  
        }
    }
}
