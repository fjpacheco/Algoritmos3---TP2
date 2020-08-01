package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Opciones.ListaOpciones;
import edu.fiuba.algo3.modelo.Opciones.OpcionCorrecta;
import edu.fiuba.algo3.modelo.Opciones.OpcionIncorrecta;
import edu.fiuba.algo3.modelo.Preguntas.ModosPreguntas.Clasico;
import edu.fiuba.algo3.modelo.Preguntas.ModosPreguntas.Penalidad;
import edu.fiuba.algo3.modelo.Preguntas.VoF;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerdaderoFalsoTest {

    @Test
    public void test01SeCreaUnaPreguntaVoFClasicoConRespuestaFalseYEvaluaCorrectamente() {
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = VoF.conModoClasico("¿2 + 2 = 4?", listaOpcionesPregunta);
        Jugador jugador = new Jugador("LeoProgramador");
        Respuesta respuestaJugador = new Respuesta(jugador);

        respuestaJugador.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador.puntos(), 0);
    }

    @Test
    public void test02SeCreaUnaPreguntaVoFClasicoConRespuestaTrueYEvaluaCorrectamente() {
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Clasico(), listaOpcionesPregunta);
        Jugador jugador = new Jugador("LeoProgramador");
        Respuesta respuestaJugador = new Respuesta(jugador);

        respuestaJugador.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador.puntos(), 1);
    }

    @Test
    public void test03RecibeUnaListaDeRespuestasTodasIncorrectasYNingunoSumaPuntos(){
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Clasico(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Joaco");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(0, jugador1.puntos());
        assertEquals(0, jugador2.puntos());
    }

    @Test
    public void test04RecibeUnaListaDeRespuestasTodasCorrectasYAmbosSumanPuntos(){
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Clasico(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Fede");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador1.puntos(), 1);
        assertEquals(jugador2.puntos(), 1);
    }

    @Test
    public void test05RecibeUnaListaDeRespuestasDistintasYSoloElQueRespondioBienSumaPuntos() {
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Clasico(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Julian");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador1.puntos(), 0);
        assertEquals(jugador2.puntos(), 1);
    }

    @Test
    public void test06SeCreaUnaPreguntaVoFConPenalidadConRespuestaFalseYEvaluaCorrectamente() {
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Penalidad(), listaOpcionesPregunta);
        Jugador jugador = new Jugador("LeoProgramador");
        Respuesta respuestaJugador = new Respuesta(jugador);

        respuestaJugador.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(-1, jugador.puntos());
    }

    @Test
    public void test07SeCreaUnaPreguntaVoFConPenalidadConRespuestaTrueYEvaluaCorrectamente() {
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Penalidad(), listaOpcionesPregunta);
        Jugador jugador = new Jugador("LeoProgramador");
        Respuesta respuestaJugador = new Respuesta(jugador);

        respuestaJugador.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(1, jugador.puntos());
    }

    @Test
    public void test08RecibeUnaListaDeRespuestasTodasIncorrectasYNingunoSumaPuntos(){
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Penalidad(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Joaco");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador1.puntos(), -1);
        assertEquals(jugador2.puntos(), -1);
    }

    @Test
    public void test09RecibeUnaListaDeRespuestasTodasCorrectasYAmbosSumanPuntos(){
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Penalidad(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Tomas");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador1.puntos(), 1);
        assertEquals(jugador2.puntos(), 1);
    }

    @Test
    public void test10RecibeUnaListaDeRespuestasDistintasYSoloElQueRespondioBienSumaPuntos(){
        ListaOpciones listaOpcionesPregunta = new ListaOpciones();
        listaOpcionesPregunta.agregarOpcion(new OpcionCorrecta("Verdadero"));
        listaOpcionesPregunta.agregarOpcion(new OpcionIncorrecta("Falso"));
        VoF pregunta = new VoF("¿2 + 2 = 4?", new Penalidad(), listaOpcionesPregunta);
        Jugador jugador1 = new Jugador("LeoProgramador");
        Respuesta respuestaJugador1 = new Respuesta(jugador1);
        Jugador jugador2 = new Jugador("Julian");
        Respuesta respuestaJugador2 = new Respuesta(jugador2);

        respuestaJugador1.agregarOpcion(listaOpcionesPregunta.obtener(1)); //Elijo incorrecta
        respuestaJugador2.agregarOpcion(listaOpcionesPregunta.obtener(0)); //Elijo correcta
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);
        pregunta.evaluarRespuestas(respuestas);

        assertEquals(jugador1.puntos(), -1);
        assertEquals(jugador2.puntos(), 1);
    }


}
