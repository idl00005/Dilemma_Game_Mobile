package com.igndl.juegodecisiones.db;

public class Pregunta {
    int id;
    String question;
    String tipo;
    String respuesta1;
    String respuesta2;
    int porcentaje1;
    int porcentaje2;

    public Pregunta(int id, String question, String tipo, String respuesta1, String respuesta2, int porcentaje1, int porcentaje2) {
        this.id = id;
        this.question = question;
        this.tipo = tipo;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.porcentaje1 = porcentaje1;
        this.porcentaje2 = porcentaje2;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public int getPorcentaje1() {
        return porcentaje1;
    }

    public void setPorcentaje1(int porcentaje1) {
        this.porcentaje1 = porcentaje1;
    }

    public int getPorcentaje2() {
        return porcentaje2;
    }

    public void setPorcentaje2(int porcentaje2) {
        this.porcentaje2 = porcentaje2;
    }
}
