package edu.fiuba.algo3.modelo.Entidades.Preguntas.ModosPreguntas;

import edu.fiuba.algo3.modelo.Entidades.Jugador;
import edu.fiuba.algo3.modelo.Entidades.Opciones.OpcionBinaria;
import edu.fiuba.algo3.modelo.Entidades.Preguntas.VoF;
import edu.fiuba.algo3.modelo.Entidades.Respuestas.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModoPreguntaTest {

    @Test
    public void test01CreoModoClasicoNoSumaPuntajeYVerificoPuntos() {
        Jugador jugador = new Jugador("Leito");
        Respuesta respuesta = new Respuesta(jugador, VoF.conModoClasico("Pregunta de Prueba", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta pa", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy incorrecta pa", false));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy incorrecta", false));

        Clasico modo = new Clasico();
        int puntos = modo.calcularPuntos(respuesta, 2);
        respuesta.modificarPuntosJugador(puntos);

        assertEquals(0, jugador.puntos());
    }

    @Test
    public void test02CreoModoClasicoSumaPuntajeYVerificoPuntos() {
        Jugador jugador = new Jugador("Leito");
        Respuesta respuesta = new Respuesta(jugador, VoF.conModoClasico("Pregunta de Prueba", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta pa", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta", true));

        Clasico modo = new Clasico();
        int puntos = modo.calcularPuntos(respuesta, 2);
        respuesta.modificarPuntosJugador(puntos);
        assertEquals(1, jugador.puntos());
    }

    @Test
    public void test03CreoModoPenalidaSumaDosRestaUnoYVerificoPuntos() {
        Jugador jugador = new Jugador("Leito");
        Respuesta respuesta = new Respuesta(jugador, VoF.conModoClasico("Pregunta de Prueba", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta pa", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy incorrecta", false));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta", true));

        Penalidad modo = new Penalidad();
        int puntos = modo.calcularPuntos(respuesta, 2);
        respuesta.modificarPuntosJugador(puntos);

        assertEquals(1, jugador.puntos());
    }

    @Test
    public void test04CreoModoPuntajeParcialNoSumaPuntajeYVerificoPuntos() {
        Jugador jugador = new Jugador("Leito");
        Respuesta respuesta = new Respuesta(jugador, VoF.conModoClasico("Pregunta de Prueba", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta pa", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy incorrecta", false));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy incorrecta pa", false));

        PuntajeParcial modo = new PuntajeParcial();
        int puntos = modo.calcularPuntos(respuesta, 2);
        respuesta.modificarPuntosJugador(puntos);

        assertEquals(0, jugador.puntos());
    }

    @Test
    public void test05CreoModoPuntajeParcialSumaPuntajeYVerificoPuntos() {
        Jugador jugador = new Jugador("Leito");
        Respuesta respuesta = new Respuesta(jugador, VoF.conModoClasico("Pregunta de Prueba", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta", true));
        respuesta.agregarOpcion(new OpcionBinaria("soy correcta pa", true));

        PuntajeParcial modo = new PuntajeParcial();
        int puntos = modo.calcularPuntos(respuesta, 2);
        respuesta.modificarPuntosJugador(puntos);

        assertEquals(2, jugador.puntos());
    }
}
