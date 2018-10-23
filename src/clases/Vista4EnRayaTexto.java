package clases;


import interfaces.Estado4EnRaya;
import interfaces.Vista4EnRaya;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista4EnRayaTexto implements Vista4EnRaya {
    private Scanner opcion;
    public Vista4EnRayaTexto(){
        opcion = new Scanner(System.in);
    }

    @Override
    public void mostrar(Estado4EnRaya estado) {
        char[][] board = estado.getTablero();
        System.out.println();
        for (int r = Juego4EnRaya.FILAS -1; r>=0; r--) {
            System.out.println(board[r][0] + " " + board[r][1] + " " + board[r][2] + " " + board[r][3] + " " + board[r][4] + " " + board[r][5] + " " + board[r][6]);
        }
    }

    @Override
    public int getMovimientoDelUsuario(Estado4EnRaya estado) {
        int col;
        char [][] board = estado.getTablero();
        System.out.println();
        col = getMensaje(estado.getJugadorAJugar().getNombre() + " ingrese una columna: ");
        while ((col < 1) || (col > Juego4EnRaya.COLS) ||
                (board[5][col-1] != '.'))
        {
            System.out.println("Movimiento invalido. Intentelo otra vez.");
            col = getMensaje("ingrese una columna: ");
        }
        return col;
    }

    @Override
    public void reportarMovimiento(int columna, String nombre) {
        System.out.println("\n" + nombre + " selecciono la columna " + columna);
    }

    @Override
    public int getMensaje(String mensaje) {
        int aux = 0;
        boolean esValido = false;
        System.out.print(mensaje + " ");
        while(!esValido) {
            try {
                aux = opcion.nextInt();
                esValido = true;
            }
            catch(InputMismatchException ex) {
                reportarAJugador("No es una columna valida");
                esValido = false;
                opcion.nextLine();
                System.out.print(mensaje + " ");
            }
        }
        opcion.nextLine();
        return aux;
    }

    @Override
    public void reportarAJugador(String mensaje) {
        System.out.println(mensaje);
    }
}
