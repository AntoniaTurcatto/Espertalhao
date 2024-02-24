package com.example.espertalhao;

import java.io.Serializable;

public class Conteudo implements Serializable {
    private int idConteudo;
    private String nomeConteudo;
    private int tipoConteudo;
    //1 = EXATAS
    //2 = HUMANAS
    //3 = LINGUAGENS

    public Conteudo(String nomeConteudo, int tipoConteudo) {
        this.nomeConteudo = nomeConteudo;
        this.tipoConteudo = tipoConteudo;
    }

    public Conteudo(int idConteudo, String nomeConteudo, int tipoConteudo) {
        this.idConteudo = idConteudo;
        this.nomeConteudo = nomeConteudo;
        this.tipoConteudo = tipoConteudo;
    }

    public int getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(int idConteudo) {
        this.idConteudo = idConteudo;
    }

    public String getNomeConteudo() {
        return nomeConteudo;
    }

    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }

    public int getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(int tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }
}
