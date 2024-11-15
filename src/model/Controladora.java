package model;

import java.util.Random;

public class Controladora {

    private String[][] tableroTresEnRaya;

    public Controladora() {
        tableroTresEnRaya = new String[3][3];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroTresEnRaya[i][j] = " ";
            }
        }
    }

    public String obtenerTableroComoString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(tableroTresEnRaya[i][j]);
                if (j < 2) sb.append("|");
            }
            sb.append("\n");
            if (i < 2) sb.append("-----\n");
        }
        return sb.toString();
    }

    public void jugadaAleatoria() {
        Random rand = new Random();
        int i, j;
        do {
            i = rand.nextInt(3);
            j = rand.nextInt(3);
        } while (!tableroTresEnRaya[i][j].equals(" "));
        tableroTresEnRaya[i][j] = "X";  
    }

    public boolean realizarJugadaHumana(int i, int j) {
        // Verificar si la casilla está vacía
        if (i >= 0 && i < 3 && j >= 0 && j < 3 && tableroTresEnRaya[i][j].equals(" ")) {
            tableroTresEnRaya[i][j] = "O"; // Coloca la "O" en la casilla
            System.out.println("La casilla (" + i + ", " + j + ") ha sido marcada con 'O'.");
            return true; // Jugada realizada con éxito
        } else {
            return false; // Jugada no realizada
        }
    }

    public String validarGanador() {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tableroTresEnRaya[i][0].equals(tableroTresEnRaya[i][1]) &&
                tableroTresEnRaya[i][1].equals(tableroTresEnRaya[i][2]) &&
                !tableroTresEnRaya[i][0].equals(" ")) {
                return tableroTresEnRaya[i][0]; // Hay un ganador en esta fila
            }
        }

        // Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (tableroTresEnRaya[0][j].equals(tableroTresEnRaya[1][j]) &&
                tableroTresEnRaya[1][j].equals(tableroTresEnRaya[2][j]) &&
                !tableroTresEnRaya[0][j].equals(" ")) {
                return tableroTresEnRaya[0][j]; // Hay un ganador en esta columna
            }
        }

        // Verificar diagonales
        if (tableroTresEnRaya[0][0].equals(tableroTresEnRaya[1][1]) &&
            tableroTresEnRaya[1][1].equals(tableroTresEnRaya[2][2]) &&
            !tableroTresEnRaya[0][0].equals(" ")) {
            return tableroTresEnRaya[0][0]; // Diagonal principal
        }
        if (tableroTresEnRaya[0][2].equals(tableroTresEnRaya[1][1]) &&
            tableroTresEnRaya[1][1].equals(tableroTresEnRaya[2][0]) &&
            !tableroTresEnRaya[0][2].equals(" ")) {
            return tableroTresEnRaya[0][2]; // Diagonal secundaria
        }

        // Verificar empate
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroTresEnRaya[i][j].equals(" ")) {
                    empate = false;
                    break;
                }
            }
            if (!empate) {
                break;
            }
        }
        if (empate) {
            return "Empate";
        }

        // Si no hay ganador ni empate, el juego continúa
        return null;
    }
}
