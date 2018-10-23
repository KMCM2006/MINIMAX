package clases;

import interfaces.Estado4EnRaya;
import interfaces.Vista4EnRaya;


public class JugadorMaquina extends Jugador {

    private int depth;
    public JugadorMaquina(String nombre, int maxDepth) {
        super(nombre);
        depth = maxDepth;
    }
    @Override
    public int getMovimiento(Estado4EnRaya estado, Vista4EnRaya vista) {
        int movimiento = calcularMovimiento(estado, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE).movimiento;
        vista.reportarMovimiento(movimiento, estado.getJugadorAJugar().getNombre());
        return movimiento;
    }

    ///MINIMAX
    private Movimientos4EnRaya calcularMovimiento(Estado4EnRaya estado, int depth, int limInferior, int limSuperior) {
        Movimientos4EnRaya movimientoActual;
        Movimientos4EnRaya mejorMovimiento;
        char[][] tablero = estado.getTablero();
        int numeroDeJugador = estado.getNumeroDeJugador();
        mejorMovimiento = new Movimientos4EnRaya(Integer.MIN_VALUE, 0);
        for (int c = 1; c <= Juego4EnRaya.COLS; c++) {
            if (estado.esMovimientoValido(c)) {
                Juego4EnRaya copia = new Juego4EnRaya(numeroDeJugador, estado.getJugadores(), tablero);
                copia.realizarMovimiento(c);
                if (copia.elJuegoTermino()) {
                    movimientoActual = new Movimientos4EnRaya(Integer.MAX_VALUE, c);
                }
                else if (numeroDeJugador == copia.getNumeroDeJugador()) {
                    movimientoActual = calcularMovimiento(copia, depth, limInferior, limSuperior);
                    movimientoActual.movimiento = c;
                }
                else if (depth > 0) {
                    movimientoActual = calcularMovimiento(copia, depth - 1, -limSuperior, -limInferior);
                    movimientoActual.valor = -movimientoActual.valor;
                    movimientoActual.movimiento = c;
                }
                else movimientoActual = new Movimientos4EnRaya(copia.puntaje(), c);
                if (movimientoActual.valor > mejorMovimiento.valor)  {
                    mejorMovimiento = movimientoActual;
                    limInferior = Math.max(limInferior, mejorMovimiento.valor);
                }
            }
        }
        return mejorMovimiento;
    }
}
