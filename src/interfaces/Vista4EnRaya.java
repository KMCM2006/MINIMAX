package interfaces;

public interface Vista4EnRaya {
    void mostrar(Estado4EnRaya estado);
    int getMovimientoDelUsuario(Estado4EnRaya estado);
    void reportarMovimiento(int columna, String nombre);
    int getMensaje(String mensaje);
    void reportarAJugador(String mensaje);
}
