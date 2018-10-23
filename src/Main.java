import clases.*;
import interfaces.Vista4EnRaya;

class Main {

    private static final int PROFUNDIDAD = 7;
    private static final String MAQUINA = "BayMax";
    private static final String JUGADOR = "ElOtroDude";

    public static void main(String args[]) {
        Vista4EnRaya vista = new Vista4EnRayaTexto();
        Jugador[] jugadores = new Jugador[2];
        jugadores[0] = new JugadorMaquina(MAQUINA, PROFUNDIDAD);
        jugadores[1] = new JugadorNormal(JUGADOR);
        Juego4EnRaya juego = new Juego4EnRaya(1, jugadores);
        vista.mostrar(juego);
        while (!juego.elJuegoTermino()) {
            int movimiento = juego.getJugadorAJugar().getMovimiento(juego, vista);
            juego.realizarMovimiento(movimiento);
            vista.mostrar(juego);
        }
        if (juego.estaLleno()) vista.reportarAJugador("Es un empate");
        else vista.reportarAJugador(jugadores[1 - juego.getNumeroDeJugador()].getNombre() + " GANO!");
    }

}