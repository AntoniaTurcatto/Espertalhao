package com.example.espertalhao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.espertalhao.model.Conteudo;
import com.example.espertalhao.model.Pergunta;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bancoPerguntas.db";
    private static final int VERSAO = 12;

    private static final String TABELA = "perguntas";
    private static final String ID = "id";
    private static final String ENUNCIADO = "enunciado";
    private static final String OPCAO_A = "opcao_a";
    private static final String OPCAO_B = "opcao_b";
    private static final String OPCAO_C = "opcao_c";
    private static final String OPCAO_D = "opcao_d";
    private static final String OPCAO_E = "opcao_e";
    private static final String OPCAO_CORRETA = "opcao_correta";
    private static final String FK_CONTEUDO = "fk_conteudo";

    private static final String COLUMN_TABELA_CONTEUDOS = "conteudos";
    private static final String COLUMN_ID_CONTEUDO = "id";
    private static final String COLUMN_NOME_CONTEUDO = "nome";
    private static final String COLUMN_TIPO_CONTEUDO = "tipo";

    public DbHandler(Context context){
        super(context, NOME_BANCO, null, VERSAO);
        Log.d("Database Operations", "Database created");
    }

    //this is called the first time a DB is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTableConteudos = "CREATE TABLE " + COLUMN_TABELA_CONTEUDOS + " ("
                + COLUMN_ID_CONTEUDO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME_CONTEUDO + " text, "
                + COLUMN_TIPO_CONTEUDO + " INTEGER"
                +");";

        String sqlCreateTablePerguntas = "CREATE TABLE " + TABELA + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ENUNCIADO + " text, "
                + OPCAO_A + " text, "
                + OPCAO_B + " text, "
                + OPCAO_C + " text, "
                + OPCAO_D + " text, "
                + OPCAO_E + " text, "
                + OPCAO_CORRETA + " text, "
                + FK_CONTEUDO + " INTEGER, "
                + " CONSTRAINT fkConteudoId FOREIGN KEY (" + FK_CONTEUDO+") REFERENCES "+ COLUMN_TABELA_CONTEUDOS + " ("+COLUMN_ID_CONTEUDO +"));";

        //executar o código sql
        db.execSQL(sqlCreateTableConteudos);
        db.execSQL(sqlCreateTablePerguntas);
        Log.d("Database Operations","Tables createds");
    }

    public String inserePergunta(Pergunta pergunta){
        ContentValues valores;
        SQLiteDatabase db;
        long resultado;

        db = this.getWritableDatabase();
        valores = new ContentValues();

        //não colocar o ID pois é autoincrement
        valores.put(ENUNCIADO, pergunta.getEnunciado());
        valores.put(OPCAO_A, pergunta.getOpcaoA());
        valores.put(OPCAO_B, pergunta.getOpcaoB());
        valores.put(OPCAO_C, pergunta.getOpcaoC());
        valores.put(OPCAO_D, pergunta.getOpcaoD());
        valores.put(OPCAO_E, pergunta.getOpcaoE());
        valores.put(OPCAO_CORRETA, String.valueOf(pergunta.getOpcaoCorreta()));

        Conteudo conteudo = pergunta.getConteudo();
        valores.put(FK_CONTEUDO, conteudo.getIdConteudo());

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir no banco";
        else
            return "Registro inserido com sucesso";
    }

    public List<Pergunta> carregaPerguntas(){

        SQLiteDatabase db;
        Cursor cursor;
        List<Pergunta> listaPerguntas = new ArrayList<>();
        //intancia o BD "legível"
        db = this.getReadableDatabase();

//        String[] campos = {ID, ENUNCIADO, OPCAO_A,
//                OPCAO_B,
//                OPCAO_C,
//                OPCAO_D,
//                OPCAO_E,
//                OPCAO_CORRETA,
//                FK_CONTEUDO};

//        cursor = db.query(DbHandler.TABELA, campos, null,
//                null, null, null, null, null);

        cursor = db.rawQuery("SELECT * FROM " + TABELA, null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);

                String enunciado = cursor.getString(cursor.getColumnIndex(ENUNCIADO));
                String opcao_a = cursor.getString(cursor.getColumnIndex(OPCAO_A));
                String opcao_b = cursor.getString(cursor.getColumnIndex(OPCAO_B));
                String opcao_c = cursor.getString(cursor.getColumnIndex(OPCAO_C));
                String opcao_d = cursor.getString(cursor.getColumnIndex(OPCAO_D));
                String opcao_e = cursor.getString(cursor.getColumnIndex(OPCAO_E));
                char opcao_correta = cursor.getString(cursor.getColumnIndex(OPCAO_CORRETA)).charAt(0);

                int idConteudo = cursor.getInt(cursor.getColumnIndex(FK_CONTEUDO));

                Conteudo conteudo = getConteudoWhereId(idConteudo);

                Pergunta perguntaAtual = new Pergunta(id,enunciado,opcao_a,opcao_b,opcao_c,opcao_d,opcao_e,opcao_correta,conteudo);
                listaPerguntas.add(perguntaAtual);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaPerguntas;
    }

    public int getAvailableIdForPerguntas(){
        SQLiteDatabase db;
        Cursor cursor;
        db = this.getReadableDatabase();
        int resultado;

        cursor = db.rawQuery("SELECT "
                + ID +" FROM "
                + TABELA
                + " ORDER BY " + ID + " DESC "
                + " LIMIT 1,0;",
                null);
        //se o cursor é nulo (nenhum registro) retornará 0, caso contrário pegará o numero do maior id na BD +1
        if (cursor.moveToFirst()){
            //resultado = cursor.getInt(0) + 1;
            resultado = cursor.getInt(cursor.getColumnIndex(ID));
            cursor.close();
        } else {
            resultado = 0;
        }
        db.close();
        return resultado;
    }

    public Pergunta getPerguntaWhereId(int id){
        SQLiteDatabase db;
        Cursor cursor;
        Pergunta pergunta;
        //String[] camposConteudoSplit;

        db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT *"
                + " FROM "+ TABELA
                + " WHERE " + ID + " = " + id + ";"
                ,null);

        if(cursor.moveToFirst()){
            int idResult = cursor.getInt(0);
            String enunciado = cursor.getString(cursor.getColumnIndex(ENUNCIADO));
            String opcao_a = cursor.getString(cursor.getColumnIndex(OPCAO_A));
            String opcao_b = cursor.getString(cursor.getColumnIndex(OPCAO_B));
            String opcao_c = cursor.getString(cursor.getColumnIndex(OPCAO_C));
            String opcao_d = cursor.getString(cursor.getColumnIndex(OPCAO_D));
            String opcao_e = cursor.getString(cursor.getColumnIndex(OPCAO_E));
            char opcao_correta = cursor.getString(cursor.getColumnIndex(OPCAO_CORRETA)).charAt(0);

            int idConteudo = cursor.getInt(cursor.getColumnIndex(FK_CONTEUDO));

            Conteudo conteudo = getConteudoWhereId(idConteudo);
            pergunta = new Pergunta(idResult,enunciado,opcao_a,opcao_b,opcao_c,opcao_d,opcao_e,opcao_correta,conteudo);

            cursor.close();
            db.close();
            return pergunta;
        } else {
            db.close();
            return null;
        }

    }


    //-------METODOS PARA ACESSAR A TABELA DE CONTEUDOS-------------

    public String insereConteudo(Conteudo conteudo){
        ContentValues valores;
        SQLiteDatabase db;
        long resultado;

        db = this.getWritableDatabase();
        valores = new ContentValues();

        valores.put(COLUMN_ID_CONTEUDO, conteudo.getIdConteudo());
        valores.put(COLUMN_NOME_CONTEUDO, conteudo.getNomeConteudo());
        valores.put(COLUMN_TIPO_CONTEUDO, conteudo.getTipoConteudo());

        resultado = db.insert(COLUMN_TABELA_CONTEUDOS, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir no banco";
        else
            return "Registro inserido com sucesso";
    }

    public List<Conteudo> carregaConteudos(){
        SQLiteDatabase db;
        Cursor cursor;
        List<Conteudo> listaConteudos = new ArrayList<>();

        //intancia o BD "legível"
        db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + COLUMN_TABELA_CONTEUDOS, null);

        if(cursor.moveToFirst()){
            do{
                int idConteudo = cursor.getInt(0);
                String nomeConteudo = cursor.getString(cursor.getColumnIndex(COLUMN_NOME_CONTEUDO));
                int tipoConteudo = cursor.getInt(cursor.getColumnIndex(COLUMN_TIPO_CONTEUDO));
                Conteudo conteudo = new Conteudo(idConteudo, nomeConteudo, tipoConteudo);
                listaConteudos.add(conteudo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaConteudos;
    }

    public int getAvailableIdForConteudos(){
        SQLiteDatabase db;
        Cursor cursor;
        db = this.getReadableDatabase();
        int resultado;

        cursor = db.rawQuery("SELECT "
                        + COLUMN_ID_CONTEUDO +" FROM "
                        + COLUMN_TABELA_CONTEUDOS
                        + " ORDER BY " + COLUMN_ID_CONTEUDO + " DESC "
                //      o limite é um registro, começando no registro n0
                        + " LIMIT 1,0;",
                null);
        //se o cursor é nulo (nenhum registro) retornará 0, caso contrário pegará o numero do maior id na BD +1
        if (cursor.moveToFirst()){
            resultado = cursor.getInt(0) + 1;
            cursor.close();
        } else {
            resultado = 0;
        }
        db.close();
        return resultado;
    }

    public Conteudo getConteudoWhereId(int id){
        SQLiteDatabase db;
        Cursor cursor;
        Conteudo conteudo;

        db = this.getReadableDatabase();

        cursor = db.rawQuery("SELECT *"
                        + " FROM "+ COLUMN_TABELA_CONTEUDOS
                        + " WHERE " + COLUMN_ID_CONTEUDO + " = " + id + ";"
                ,null);

        if (cursor.moveToFirst()){

            //extraindo os resultados
            int idResult = cursor.getInt(0);
            String nomeConteudo = cursor.getString(cursor.getColumnIndex(COLUMN_NOME_CONTEUDO));
            int tipoConteudo = cursor.getInt(cursor.getColumnIndex(COLUMN_TIPO_CONTEUDO));

            //instanciando o conteudo
            conteudo = new Conteudo(idResult,nomeConteudo,tipoConteudo);

            cursor.close();
            db.close();
            return conteudo;
        } else {
            db.close();
            return null;
        }
    }

    //this is called if the database version number changes. It prevents previous user apps from breaking when you change the DB design
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);//tabela perguntas
        db.execSQL("DROP TABLE IF EXISTS " + COLUMN_TABELA_CONTEUDOS); //tabela conteudos
        onCreate(db);
    }
}
