package interfaces;

import clases.Jugador;

public interface Estado4EnRaya {
    int ROWS = 6;
    int COLS = 7;
    char EMPTY = '.';
    char CHECKER0 = 'X';
    char CHECKER1 = 'O';
    char [] CHECKERS = {CHECKER0, CHECKER1};

    char [][] getTablero();
    Jugador[] getJugadores();
    int getNumeroDeJugador();
    Jugador getJugadorAJugar();
    boolean esMovimientoValido(int col);
    void realizarMovimiento(int col);
    boolean estaLleno();
    boolean elJuegoTermino();

}
