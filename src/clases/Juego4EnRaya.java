package clases;

import interfaces.Estado4EnRaya;

public class Juego4EnRaya implements Estado4EnRaya {
    private char [][] tablero;
    private int jugadorAJugar;
    private Jugador[] jugadores;
    final static int FILAS = 6;
    private final static int COLUMNAS = 7;
    private final static char VACIO = '.';
    private final static char FICHA_1 = 'X';
    private final static char FICHA_2 = 'O';
    private final static char [] FICHAS = {FICHA_1, FICHA_2};

    public Juego4EnRaya(int jugaroAJugar, Jugador[] jugadores) {
        char [][] tableroInicial = new char[FILAS][COLUMNAS];
        for (int j = 0; j < FILAS; j++) {
            for (int k = 0; k < COLUMNAS; k++) {
                tableroInicial[j][k] = '.';
            }
        }
        tablero = tableroInicial;
        this.jugadorAJugar = jugaroAJugar;
        this.jugadores = jugadores;
    }

    Juego4EnRaya(int jugadorAJugar, Jugador[] jugadores, char[][] tablero)   {
        this.tablero = new char[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            System.arraycopy(tablero[i], 0, this.tablero[i], 0, COLUMNAS);
        }
        this.jugadorAJugar = jugadorAJugar;
        this.jugadores = jugadores;
    }

    @Override
    public char [][] getTablero() {
        return tablero;
    }

    @Override
    public Jugador[] getJugadores() {
        return jugadores;
    }

    @Override
    public int getNumeroDeJugador() {
        return jugadorAJugar;
    }

    @Override
    public Jugador getJugadorAJugar() {
        return jugadores[jugadorAJugar];
    }

    @Override
    public boolean esMovimientoValido(int col) {
        return tablero[FILAS - 1][col - 1] == VACIO;
    }

    @Override
    public void realizarMovimiento(int col) {
        int r = 0;
        while (tablero[r][col-1] != VACIO && r < FILAS) {
            r++;
        }
        tablero[r][col-1] = FICHAS[jugadorAJugar];
        jugadorAJugar = 1 - jugadorAJugar;
    }

    @Override
    public boolean estaLleno() {
        boolean resultado = true;
        for (int c = 0; c < COLUMNAS; c++) {
            if (tablero[FILAS -1][c] == VACIO) {
                resultado = false;
            }
        }
        return resultado;
    }

    @Override
    public boolean elJuegoTermino() {
        boolean resultado = false;
        if (estaLleno()) {
            resultado = true;
            //contarFichas();
            //System.out.println("MAQUINA: " + jugadores[0].jugadas);
            //System.out.println("JUGADOR: " + jugadores[1].jugadas);
        } else if (jugar()) {
            resultado = true;
        }
        return resultado;
    }

    private void contarFichas() {
        boolean continuar = false;
        for (int r = 0; r < FILAS && !continuar; r++) {
            if (r <= FILAS -4) {
                for (int c = 0; c < COLUMNAS && !continuar; c++) {
                    continuar = esJuegoValido(r, c);
                    if (continuar && tablero[r][c] == FICHA_1) jugadores[0].jugadas++;
                    if (continuar && tablero[r][c] == FICHA_2) jugadores[1].jugadas++;
                }
            }
            else {
                for (int c = 0; c <= COLUMNAS-4 && !continuar; c++) {
                    continuar = esJuegoValido(r, c);
                    if (continuar && tablero[r][c] == FICHA_1) jugadores[0].jugadas++;
                    if (continuar && tablero[r][c] == FICHA_2) jugadores[1].jugadas++;
                }
            }
        }

    }

    int puntaje(){
        int total = 0;
        for (int r = 0; r < FILAS; r++) {
            if (r <= FILAS -4) {
                for (int c = 0; c < COLUMNAS; c++) {
                    total += puntaje(r, c);
                }
            }
            else {
                for (int c = 0; c <= COLUMNAS-4; c++) {
                    total += puntaje(r, c);
                }
            }
        }
        return total;
    }

    private int puntaje(int filas, int columnas){
        int puntaje = 0;
        boolean desbloqueado;
        int total;
        if (filas < FILAS -3) {
            desbloqueado = true;
            total = 0;
            for (int r=filas; r<filas+4; r++) {
                if (tablero[r][columnas] == FICHAS[1- jugadorAJugar]) {
                    desbloqueado = false;
                }
                if (tablero[r][columnas] == FICHAS[jugadorAJugar]) {
                    total ++;
                }
            }
            if (desbloqueado) {
                puntaje = puntaje + (total*total*total*total);
            }
            if (columnas < COLUMNAS-3) {
                desbloqueado = true;
                total = 0;
                for (int r=filas, c=columnas; r<filas+4; r++, c++) {
                    if (tablero[r][c] == FICHAS[1- jugadorAJugar]) {
                        desbloqueado = false;
                    }
                    if (tablero[r][c] == FICHAS[jugadorAJugar]) {
                        total ++;
                    }
                }
                if (desbloqueado) {
                    puntaje = puntaje + (total*total*total*total);
                }
            }
        }
        if (columnas < COLUMNAS-3) {
            desbloqueado = true;
            total = 0;
            for (int c=columnas; c<columnas+4; c++) {
                if (tablero[filas][c] == FICHAS[1- jugadorAJugar]) {
                    desbloqueado = false;
                }
                if (tablero[filas][c] == FICHAS[jugadorAJugar]) {
                    total ++;
                }
            }
            if (desbloqueado) {
                puntaje = puntaje + (total*total*total*total);
            }
            if (filas > 2) {
                desbloqueado = true;
                total = 0;
                for (int r=filas, c=columnas; c<columnas+4; r--, c++) {
                    if (tablero[r][c] == FICHAS[1- jugadorAJugar]) {
                        desbloqueado = false;
                    }
                    if (tablero[r][c] == FICHAS[jugadorAJugar]) {
                        total ++;
                    }
                }
                if (desbloqueado) {
                    puntaje = puntaje + (total*total*total*total);
                }
            }
        }
        return puntaje;
    }

    private boolean jugar() {
        boolean continuar = false;
        for (int r = 0; r < FILAS && !continuar; r++) {
            if (r <= FILAS -4) {
                for (int c = 0; c < COLUMNAS && !continuar; c++) {
                    continuar = esJuegoValido(r, c);
                }
            }
            else {
                for (int c = 0; c <= COLUMNAS-4 && !continuar; c++) {
                    continuar = esJuegoValido(r, c);
                }
            }
        }
        return continuar;
    }

    private boolean esJuegoValido(int filas, int columnas) {
        boolean c4 = false;
        if (filas < FILAS -3) {
            c4 = true;
            for (int r=filas; r<filas+4; r++) {
                if (tablero[r][columnas] != FICHAS[1- jugadorAJugar]) {
                    c4 = false;
                }
            }
            if (columnas < COLUMNAS-3 && !c4) {
                c4 = true;
                for (int r=filas, c=columnas; r<filas+4; r++, c++) {
                    if (tablero[r][c] != FICHAS[1- jugadorAJugar]) {
                        c4 = false;
                    }
                }
            }
        }
        if (columnas < COLUMNAS-3 && !c4) {
            c4 = true;
            for (int c=columnas; c<columnas+4; c++) {
                if (tablero[filas][c] != FICHAS[1- jugadorAJugar]) {
                    c4 = false;
                }
            }
            if (filas > 2 && !c4) {
                c4 = true;
                for (int r=filas, c=columnas; c<columnas+4; r--, c++) {
                    if (tablero[r][c] != FICHAS[1- jugadorAJugar]) {
                        c4 = false;
                    }
                }
            }
        }
        return c4;
    }
}
