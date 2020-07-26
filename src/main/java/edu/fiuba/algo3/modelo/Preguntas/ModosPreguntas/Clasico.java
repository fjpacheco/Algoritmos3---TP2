package edu.fiuba.algo3.modelo.Preguntas.ModosPreguntas;

import edu.fiuba.algo3.modelo.Jugador;

public class Clasico implements ModoPregunta {

    static final int PUNTAJE = 1;

    @Override
    public void modificarPuntos(Jugador jugador, int cantidadCorrectas, int aciertos, int errores){
        if(aciertos == cantidadCorrectas && errores == 0)
            jugador.sumarPuntos(PUNTAJE);
    }
}

