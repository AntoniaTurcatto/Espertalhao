package com.example.espertalhao;

import android.app.Application;

import java.util.ArrayList;

public class App extends Application {

    private ArrayList<Conteudo> listaConteudo;
    private ArrayList<Pergunta> listaPergunta;

    @Override
    public void onCreate(){
        super.onCreate();
        listaConteudo = new ArrayList<Conteudo>();
        listaPergunta = new ArrayList<Pergunta>();
    }

    public ArrayList<Conteudo> getListaConteudo() {
        return listaConteudo;
    }

    public void setListaConteudo(ArrayList<Conteudo> listaConteudo) {
        this.listaConteudo = listaConteudo;
    }

    public ArrayList<Pergunta> getListaPergunta() {
        return listaPergunta;
    }

    public void setListaPergunta(ArrayList<Pergunta> listaPergunta) {
        this.listaPergunta = listaPergunta;
    }
}
