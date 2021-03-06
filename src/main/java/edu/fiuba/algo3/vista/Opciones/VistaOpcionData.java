package edu.fiuba.algo3.vista.Opciones;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class VistaOpcionData {
    private String urlImagen;
    private int fila;
    private int columna;

    public VistaOpcionData(int fila,int columna, String url){
        this.fila = fila;
        this.columna = columna;
        urlImagen = url;
    }

    public Image getImagen(){
        return new Image(urlImagen);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
