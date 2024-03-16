package com.example.espertalhao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaBanco extends SQLiteOpenHelper {

    //DADOS BANCO
    private static final String NOME_BANCO = "bancoPerguntas.db";
    private static final int VERSAO = 18;
    //TABELA PERGUNTAS
    private static final String COLUMN_TABELA_PERGUNTAS = "perguntas";
    private static final String COLUMN_ID_PERGUNTAS = "id";
    private static final String COLUMN_ENUNCIADO_PERGUNTAS = "enunciado";
    private static final String COLUMN_OPCAO_A_PERGUNTAS = "opcao_a";
    private static final String COLUMN_OPCAO_B_PERGUNTAS = "opcao_b";
    private static final String COLUMN_OPCAO_C_PERGUNTAS = "opcao_c";
    private static final String COLUMN_OPCAO_D_PERGUNTAS = "opcao_d";
    private static final String COLUMN_OPCAO_E_PERGUNTAS = "opcao_e";
    private static final String COLUMN_OPCAO_CORRETA_PERGUNTAS = "opcao_correta";
    private static final String COLUMN_FK_CONTEUDO_PERGUNTAS = "fk_conteudo";
    //TABELA CONTEUDOS
    private static final String COLUMN_TABELA_CONTEUDOS = "conteudos";
    private static final String COLUMN_ID_CONTEUDO = "id";
    private static final String COLUMN_NOME_CONTEUDO = "nome";
    private static final String COLUMN_TIPO_CONTEUDO = "tipo";



    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        Log.d("Database Operations", "Database created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableConteudos = "CREATE TABLE " + COLUMN_TABELA_CONTEUDOS + " ("
                + COLUMN_ID_CONTEUDO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME_CONTEUDO + " text, "
                + COLUMN_TIPO_CONTEUDO + " INTEGER"
                + ");";

        String sqlCreateTablePerguntas = "CREATE TABLE " + COLUMN_TABELA_PERGUNTAS + " ("
                + COLUMN_ID_PERGUNTAS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ENUNCIADO_PERGUNTAS + " text, "
                + COLUMN_OPCAO_A_PERGUNTAS + " text, "
                + COLUMN_OPCAO_B_PERGUNTAS + " text, "
                + COLUMN_OPCAO_C_PERGUNTAS + " text, "
                + COLUMN_OPCAO_D_PERGUNTAS + " text, "
                + COLUMN_OPCAO_E_PERGUNTAS + " text, "
                + COLUMN_OPCAO_CORRETA_PERGUNTAS + " text, "
                + COLUMN_FK_CONTEUDO_PERGUNTAS + " INTEGER, "
                + " CONSTRAINT fkConteudoId FOREIGN KEY (" + COLUMN_FK_CONTEUDO_PERGUNTAS + ") REFERENCES " + COLUMN_TABELA_CONTEUDOS + "(" + COLUMN_ID_CONTEUDO + "));";

        //executar o código sql
        db.execSQL(sqlCreateTableConteudos);
        db.execSQL(sqlCreateTablePerguntas);
        Log.d("Database Operations", "Tables createds");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + COLUMN_TABELA_PERGUNTAS);//tabela perguntas
        db.execSQL("DROP TABLE IF EXISTS " + COLUMN_TABELA_CONTEUDOS); //tabela conteudos
        onCreate(db);
    }


    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static int getVERSAO() {
        return VERSAO;
    }

    public static String getColumnTabelaPerguntas() {
        return COLUMN_TABELA_PERGUNTAS;
    }

    public static String getColumnIdPerguntas() {
        return COLUMN_ID_PERGUNTAS;
    }

    public static String getColumnEnunciadoPerguntas() {
        return COLUMN_ENUNCIADO_PERGUNTAS;
    }

    public static String getColumnOpcaoAPerguntas() {
        return COLUMN_OPCAO_A_PERGUNTAS;
    }

    public static String getColumnOpcaoBPerguntas() {
        return COLUMN_OPCAO_B_PERGUNTAS;
    }

    public static String getColumnOpcaoCPerguntas() {
        return COLUMN_OPCAO_C_PERGUNTAS;
    }

    public static String getColumnOpcaoDPerguntas() {
        return COLUMN_OPCAO_D_PERGUNTAS;
    }

    public static String getColumnOpcaoEPerguntas() {
        return COLUMN_OPCAO_E_PERGUNTAS;
    }

    public static String getColumnOpcaoCorretaPerguntas() {
        return COLUMN_OPCAO_CORRETA_PERGUNTAS;
    }

    public static String getColumnFkConteudoPerguntas() {
        return COLUMN_FK_CONTEUDO_PERGUNTAS;
    }

    public static String getColumnTabelaConteudos() {
        return COLUMN_TABELA_CONTEUDOS;
    }

    public static String getColumnIdConteudo() {
        return COLUMN_ID_CONTEUDO;
    }

    public static String getColumnNomeConteudo() {
        return COLUMN_NOME_CONTEUDO;
    }

    public static String getColumnTipoConteudo() {
        return COLUMN_TIPO_CONTEUDO;
    }
}
