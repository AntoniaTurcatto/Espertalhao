package com.example.espertalhao.model;

import java.io.Serializable;

public class Pergunta implements Serializable {

    private int idPergunta;
    private String enunciado;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private String opcaoD;
    private String opcaoE;
    private char opcaoCorreta;
    private Conteudo conteudo;

    public Pergunta(int idPergunta, String enunciado, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String opcaoE, char opcaoCorreta, Conteudo conteudo) {
        this.idPergunta = idPergunta;
        this.enunciado = enunciado;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.opcaoE = opcaoE;
        this.opcaoCorreta = opcaoCorreta;
        this.conteudo = conteudo;
    }

    public Pergunta(String enunciado, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String opcaoE, char opcaoCorreta, Conteudo conteudo) {
        this.enunciado = enunciado;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.opcaoE = opcaoE;
        this.opcaoCorreta = opcaoCorreta;
        this.conteudo = conteudo;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpcaoA() {
        return opcaoA;
    }

    public void setOpcaoA(String opcaoA) {
        this.opcaoA = opcaoA;
    }

    public String getOpcaoB() {
        return opcaoB;
    }

    public void setOpcaoB(String opcaoB) {
        this.opcaoB = opcaoB;
    }

    public String getOpcaoC() {
        return opcaoC;
    }

    public void setOpcaoC(String opcaoC) {
        this.opcaoC = opcaoC;
    }

    public String getOpcaoD() {
        return opcaoD;
    }

    public void setOpcaoD(String opcaoD) {
        this.opcaoD = opcaoD;
    }

    public String getOpcaoE() {
        return opcaoE;
    }

    public void setOpcaoE(String opcaoE) {
        this.opcaoE = opcaoE;
    }

    public char getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public void setOpcaoCorreta(char opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "enunciado= " + enunciado + "\n" +
                ", opcaoA= " + opcaoA + "\n" +
                ", opcaoB= " + opcaoB + "\n" +
                ", opcaoC= " + opcaoC + "\n" +
                ", opcaoD= " + opcaoD + "\n" +
                ", opcaoE= " + opcaoE + "\n" +
                ", opcaoCorreta= " + opcaoCorreta +
                ", conteudo= " + conteudo.getNomeConteudo();
    }
}
