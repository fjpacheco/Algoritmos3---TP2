package edu.fiuba.algo3.vista.Opciones;

import edu.fiuba.algo3.controlador.ControladorOpcionBinaria;
import edu.fiuba.algo3.modelo.Entidades.Opciones.OpcionBinaria;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class VistaOpcionBinaria extends StackPane {
    ToggleButton botonEstado = new ToggleButton();

    public VistaOpcionBinaria(OpcionBinaria unaOpcion, Image imagen, ArrayList<OpcionBinaria> opcionesElegidas){
        ImageView imageView = new ImageView(imagen);
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.fitWidthProperty().bind(this.widthProperty());

        Label descripcionLabel = new Label();
        descripcionLabel.setText(unaOpcion.getDescripcion());
        descripcionLabel.setFont(Font.font("Montserrat", FontWeight.BOLD,30));
        descripcionLabel.setStyle("-fx-effect: dropshadow( one-pass-box , black , 5 , 0.0 , 1 , 0 )");
        descripcionLabel.setTextFill(Color.WHITE);
        descripcionLabel.setAlignment(Pos.CENTER_LEFT);

        Image estadoImagen = new Image("File:src/resources/imagenes/IMG_Botones/IMG_OpcionBinariaNoSeleccionada.png");
        ImageView estadoImagenView = new ImageView(estadoImagen);
        estadoImagenView.setFitWidth(60);
        estadoImagenView.setFitHeight(60);
        botonEstado.setGraphic(estadoImagenView);
        botonEstado.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,Insets.EMPTY)));
        botonEstado.setOnAction(new ControladorOpcionBinaria(opcionesElegidas,unaOpcion,estadoImagenView));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(descripcionLabel,botonEstado);
        hBox.setPrefHeight(70);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        this.setMaxHeight(50);
        this.getChildren().addAll(imageView,hBox);
    }
}
