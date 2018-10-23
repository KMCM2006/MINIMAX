package clases;

import interfaces.Estado4EnRaya;
import interfaces.Vista4EnRaya;

public abstract class Jugador {
    private String nombre;
    public int jugadas = 0;

    Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract int getMovimiento(Estado4EnRaya state, Vista4EnRaya view);
}