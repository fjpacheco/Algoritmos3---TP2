package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controlador.ControladorEnviar;

import edu.fiuba.algo3.controlador.ControladorExclusividad;
import edu.fiuba.algo3.controlador.ControladorMultiplicadorDoble;
import edu.fiuba.algo3.controlador.ControladorMultiplicadorTriple;
import edu.fiuba.algo3.modelo.Entidades.Juego;
import edu.fiuba.algo3.modelo.Entidades.Preguntas.*;
import edu.fiuba.algo3.vista.Preguntas.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class VistaPrincipal extends BorderPane{
    private Juego juego;
    VistaPregunta vistaPregunta;
    private AudioClip sonido;

    private final Integer startTime = 20;
    private Integer secondsPassed = startTime;
    private Timeline tiempo;
    Label contador = new Label(String.valueOf(startTime));

    Button botonEnviar = new Button();

    public VistaPrincipal(Stage stage, Juego juego, Pregunta pregunta){
        this.juego = juego;

        ArrayList<String> canciones = new ArrayList<String>();
        canciones.add("File:src/resources/sonidos/CountDown/SND_KahootCountDown1.mp3");
        canciones.add("File:src/resources/sonidos/CountDown/SND_KahootCountDown2.mp3");
        canciones.add("File:src/resources/sonidos/CountDown/SND_KahootCountDown3.mp3");
        canciones.add("File:src/resources/sonidos/CountDown/SND_KahootCountDown4.mp3");
        Random rand = new Random();
        sonido = new AudioClip(canciones.get(rand.nextInt(canciones.size())));
        sonido.play();

        crearVistaPregunta(pregunta);
        mostrarEnunciadoPregunta(pregunta);
        mostrarContador();
        inicializarBotonEnviar(stage,juego,tiempo);

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(botonEnviar, vistaPregunta);
        vBox.setAlignment(Pos.CENTER);
        this.setBottom(vBox);
    }

    public void contar(){
        if(secondsPassed > 0){
            secondsPassed--;
            contador.setText(String.valueOf(secondsPassed));
        }
        else {
            tiempo.pause();
            botonEnviar.fire();
        }
    }

    private void mostrarEnunciadoPregunta(Pregunta pregunta) {
        StackPane stackPane = new StackPane();

        ImageView fondoPregunta = new ImageView("File:src\\resources\\imagenes\\fondoPregunta.png");
        fondoPregunta.fitWidthProperty().bind(this.widthProperty());

        Label enunciadoPregunta = new Label(pregunta.enunciado());
        enunciadoPregunta.setFont(Font.font("Core Mellow", FontWeight.BOLD,55));

        stackPane.getChildren().addAll(fondoPregunta,enunciadoPregunta);

        Label jugadorResponde = new Label(juego.turnoDe().nombre());
        jugadorResponde.setStyle("-fx-background-color: #575757;"
                                + "-fx-background-radius: 5px;");
        jugadorResponde.setAlignment(Pos.CENTER);
        jugadorResponde.setFont(Font.font("Core Mellow", FontWeight.BOLD,40));
        jugadorResponde.setTextFill(Color.WHITE);
        jugadorResponde.setPadding(new Insets(5,30,5,30));

        VBox vBox = new VBox(5,stackPane,jugadorResponde);
        vBox.setAlignment(Pos.CENTER);

        this.setTop(vBox);
    }

    private void mostrarContador(){
        StackPane stackPane = new StackPane();
        Circle circle = new Circle(80,Color.valueOf("#844cbe"));
        contador.setTextFill(Color.WHITE);
        contador.setFont(Font.font("Core Mellow", FontWeight.BOLD,60));

        stackPane.getChildren().addAll(circle,contador);

        tiempo = new Timeline(new KeyFrame(Duration.seconds(1),e -> contar()));
        tiempo.setCycleCount(Timeline.INDEFINITE);
        tiempo.play();

        HBox bonificaciones = new HBox(20);
        bonificaciones.setAlignment(Pos.CENTER);

        if(juego.obtenerRondaActual().obtenerPregunta().aceptaMultiplicador()) {
            if(juego.turnoDe().tieneMultiplicadorDoble()) {
                Button multiplicadorDoble = new Button("x 2");
                multiplicadorDoble.setPrefSize(120, 120);
                multiplicadorDoble.setStyle("-fx-background-color: #d88800;"
                                            + "-fx-background-radius: 5px");
                multiplicadorDoble.setFont(Font.font("Core Mellow", FontWeight.BOLD,40));
                multiplicadorDoble.setTextFill(Color.WHITE);
                multiplicadorDoble.setOnAction(new ControladorMultiplicadorDoble(juego.turnoDe(), vistaPregunta, multiplicadorDoble));
                multiplicadorDoble.setOnMouseClicked(e -> multiplicadorDoble.setStyle("-fx-background-color: #be6d00"));
                bonificaciones.getChildren().addAll(multiplicadorDoble);
            }
            if(juego.turnoDe().tieneMultiplicadorTriple()) {
                Button multiplicadorTriple = new Button("x 3");
                multiplicadorTriple.setPrefSize(120, 120);
                multiplicadorTriple.setStyle("-fx-background-color: #d88800;"
                                            + "-fx-background-radius: 5px");
                multiplicadorTriple.setFont(Font.font("Core Mellow", FontWeight.BOLD,40));
                multiplicadorTriple.setTextFill(Color.WHITE);
                multiplicadorTriple.setOnAction(new ControladorMultiplicadorTriple(juego.turnoDe(), vistaPregunta, multiplicadorTriple));
                multiplicadorTriple.setOnMouseClicked(e -> multiplicadorTriple.setStyle("-fx-background-color: #be6d00"));
                bonificaciones.getChildren().addAll(multiplicadorTriple);
            }
        } else if (juego.turnoDe().tieneExclusividad()){
            Button exclusivad = new Button("Exclusividad");
            exclusivad.setStyle("-fx-background-color: #00a2be;"
                                 + "-fx-background-radius: 5px");
            exclusivad.setFont(Font.font("Core Mellow", FontWeight.BOLD,40));
            exclusivad.setTextFill(Color.WHITE);
            exclusivad.setOnAction(new ControladorExclusividad(juego.turnoDe(), vistaPregunta, exclusivad));
            exclusivad.setOnMouseClicked(e -> exclusivad.setStyle("-fx-background-color: #00809c"));
            bonificaciones.getChildren().addAll(exclusivad);
        }

        bonificaciones.setPadding(new Insets(0,20,0,0));
        stackPane.setPadding(new Insets(0,0,0,20));
        this.setRight(bonificaciones);
        this.setLeft(stackPane);
    }

    public void inicializarBotonEnviar(Stage stage, Juego juego, Timeline tiempo){
        botonEnviar.setAlignment(Pos.CENTER);
        botonEnviar.setPrefSize(150,80);
        botonEnviar.setStyle("-fx-background-color: #26890c");

        Label label = new Label("Enviar");
        label.setFont(Font.font("Montserrat", FontWeight.BOLD,25));
        label.setTextFill(Color.WHITE);
        botonEnviar.setGraphic(label);

        ControladorEnviar enviarRespuesta = new ControladorEnviar(stage,juego,tiempo,sonido,vistaPregunta);
        botonEnviar.setOnAction(enviarRespuesta);
    }

    public void crearVistaPregunta(Pregunta pregunta){
        if(pregunta instanceof VoF){
            vistaPregunta = new VistaVoF((VoF) pregunta,juego.turnoDe());
        } else if(pregunta instanceof MultipleChoice){
            vistaPregunta = new VistaMultipleChoice((MultipleChoice) pregunta,juego.turnoDe());
        } else if(pregunta instanceof OrderedChoice) {
            vistaPregunta = new VistaOrderedChoice((OrderedChoice) pregunta,juego.turnoDe());
        } else if(pregunta instanceof GroupChoice){
            vistaPregunta = new VistaGroupChoice((GroupChoice) pregunta,juego.turnoDe());
        }
        else return; //Agregar exepcion de pregunta no reconocida
    }
}