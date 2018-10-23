package clases;

import interfaces.Estado4EnRaya;
import interfaces.Vista4EnRaya;

public class JugadorNormal extends Jugador {
    public JugadorNormal(String nombre) {
        super(nombre);
    }

    @Override
    public int getMovimiento(Estado4EnRaya estado, Vista4EnRaya vista) {
        return vista.getMovimientoDelUsuario(estado);
    }
}
