package edu.fiuba.algo3.modelo.Preguntas;

import edu.fiuba.algo3.modelo.Preguntas.ModosPreguntas.ModoPregunta;
import edu.fiuba.algo3.modelo.Opciones.OpcionCorrecta;
import edu.fiuba.algo3.modelo.Respuesta;

public class VoF extends Pregunta {

    public VoF (String unEnunciado, ModoPregunta unModo, OpcionCorrecta opcionCorrecta) {
        // [opcionCorrecta.esCorrecta() == false ] lanzo excepción

        opciones.agregarOpcion(opcionCorrecta);
        cantidadCorrectas = opciones.cantidadOpcionesCorrectas();
        modo = unModo;
        enunciado = unEnunciado;
    }


    @Override
    protected void evaluarRespuesta(Respuesta respuesta) {
        int aciertos = respuesta.cantidadOpcionesCorrectas();
        int errores = respuesta.cantidadOpcionesIncorrectas();

        // Si aciertos o errores son nulos, lanzo excepción; necesariamente 1 respuesta correcta o incorrecta

        modo.modificarPuntos(respuesta.responsable(), cantidadCorrectas, aciertos, errores);
    }
}
