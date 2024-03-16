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

public class DbHandler {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public DbHandler(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereConteudo(Conteudo meuConteudo) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.getColumnNomeConteudo(), meuConteudo.getNomeConteudo());
        valores.put(CriaBanco.getColumnTipoConteudo(), meuConteudo.getTipoConteudo());

        resultado = db.insert(CriaBanco.getColumnTabelaConteudos(), null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return carregaConteudos().toString();
        }
    }

    public ArrayList<Conteudo> carregaConteudos() {
        // criando a lista para onde serão carregadas as informações
        ArrayList<Conteudo> listaConteudos = new ArrayList<>();
        // obtendo a instância do banco
        this.db = this.banco.getWritableDatabase();
        // realizando a consulta sem nenhum filtro -> select * from Usuarios
        Cursor cursor = this.db.query(CriaBanco.getColumnTabelaConteudos(), null, null, null, null, null, null);
        // segundo parametro é para definir as colunas que desejo
        // terceiro parametro é para definir as clausulas para selecionar/filtrar
        if (cursor.moveToFirst()) {
            // navegando no retorno dos dados do banco
            do {
                // obtendo informação por informação
                int id = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnIdConteudo()));
                String nomeConteudo = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnNomeConteudo()));
                int tipoConteudo = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnTipoConteudo()));

                // criando o objeto da classe
                Conteudo meuConteudo = new Conteudo(id, nomeConteudo, tipoConteudo);
                // adicionando na lista
                listaConteudos.add(meuConteudo);
            } while (cursor.moveToNext());
        }
        int tamanho = listaConteudos.size();
        cursor.close();
        db.close();
        return listaConteudos;
    }

    public Conteudo carregaConteudoById(int id) {
        Cursor cursor;
        Conteudo meuConteudo = null;
        String[] campos = {CriaBanco.getColumnIdConteudo(), CriaBanco.getColumnNomeConteudo(), CriaBanco.getColumnTipoConteudo()};
        String where = CriaBanco.getColumnIdConteudo() + " = " + id;
        db = banco.getReadableDatabase();
        //cursor = db.query(CriaBanco.getColumnTabelaConteudos(), campos, where, null, null, null, null, null);
        cursor = db.rawQuery("SELECT * FROM " + CriaBanco.getColumnTabelaConteudos()
                        + " WHERE " + CriaBanco.getColumnIdConteudo() + "=" + id + ";"
                , null);
        System.out.println(cursor.getCount());

        if (cursor.moveToFirst()) {
            // obtendo informação por informação
            id = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnIdConteudo()));
            String nomeConteudo = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnNomeConteudo()));
            int tipoConteudo = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnTipoConteudo()));

            // criando o objeto da classe
            meuConteudo = new Conteudo(id, nomeConteudo, tipoConteudo);
        }
        cursor.close();
        //db.close();
        return meuConteudo;
    }

    public void alteraRegistroConteudo(Conteudo meuConteudo) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.getColumnIdConteudo() + "=" + meuConteudo.getIdConteudo();

        valores = new ContentValues();
        valores.put(CriaBanco.getColumnNomeConteudo(), meuConteudo.getNomeConteudo());
        valores.put(CriaBanco.getColumnTipoConteudo(), meuConteudo.getTipoConteudo());

        db.update(CriaBanco.getColumnTabelaConteudos(), valores, where, null);
        db.close();
    }

    public void deletaRegistroConteudo(int id) {
        String where = CriaBanco.getColumnIdConteudo() + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.getColumnTabelaConteudos(), where, null);
        db.close();
    }

    //------------métodos para acessar tabela PERGUNTAS-----------

    public String inserePergunta(Pergunta pergunta) {
        ContentValues valores;
        db = banco.getWritableDatabase();
        long resultado;
        valores = new ContentValues();

        valores.put(CriaBanco.getColumnEnunciadoPerguntas(), pergunta.getEnunciado());
        valores.put(CriaBanco.getColumnOpcaoAPerguntas(), pergunta.getOpcaoA());
        valores.put(CriaBanco.getColumnOpcaoBPerguntas(), pergunta.getOpcaoB());
        valores.put(CriaBanco.getColumnOpcaoCPerguntas(), pergunta.getOpcaoC());
        valores.put(CriaBanco.getColumnOpcaoDPerguntas(), pergunta.getOpcaoD());
        valores.put(CriaBanco.getColumnOpcaoEPerguntas(), pergunta.getOpcaoE());
        valores.put(CriaBanco.getColumnOpcaoCorretaPerguntas(), String.valueOf(pergunta.getOpcaoCorreta()));

        int idConteudo = pergunta.getConteudo().getIdConteudo();
        Conteudo conteudoEncontrado = carregaConteudoById(idConteudo);

        valores.put(CriaBanco.getColumnFkConteudoPerguntas(), conteudoEncontrado.getIdConteudo());
        System.out.println("\n\n\n" + conteudoEncontrado.toString());

        resultado = db.insert(CriaBanco.getColumnTabelaPerguntas(), null, valores);
        db.close();

        if (resultado == -1) {
            return "erro ao inserir no banco";
        } else {
            return "pergunta inserida com sucesso";
        }
    }

    public ArrayList<Pergunta> carregaPerguntas() {
        ArrayList<Pergunta> listaPerguntas;
        db = banco.getReadableDatabase();

        listaPerguntas = new ArrayList<>();
        Cursor cursor = db.query(CriaBanco.getColumnTabelaPerguntas(), null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idPergunta = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnIdPerguntas()));
                String enunciado = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnEnunciadoPerguntas()));
                String opcaoA = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoAPerguntas()));
                String opcaoB = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoBPerguntas()));
                String opcaoC = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoCPerguntas()));
                String opcaoD = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoDPerguntas()));
                String opcaoE = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoEPerguntas()));
                char opcaoCorreta = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoCorretaPerguntas())).charAt(0);
                int fkIdConteudoDireto = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnFkConteudoPerguntas()));
                //pesquisando o id do FK_CONTEUDO_ID da pergunta atual
//                Cursor cursor2 = db.rawQuery("SELECT " + CriaBanco.getColumnFkConteudoPerguntas() + " FROM " + CriaBanco.getColumnTabelaPerguntas()
//                                + " WHERE " + CriaBanco.getColumnIdPerguntas() + " = " + idPergunta + ";"
//                        , null);
//                int fkIdConteudo =  cursor2.getInt(cursor2.getColumnIndex(CriaBanco.getColumnFkConteudoPerguntas()));
//                cursor2.close();

                Pergunta perguntaAtual = new Pergunta(idPergunta,
                        enunciado,
                        opcaoA,
                        opcaoB,
                        opcaoC,
                        opcaoD,
                        opcaoE,
                        opcaoCorreta,
                        carregaConteudoById(fkIdConteudoDireto));
                listaPerguntas.add(perguntaAtual);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return listaPerguntas;
    }

    public Pergunta carregaPerguntaById(int id) {
        Cursor cursor;
        Pergunta pergunta = null;
//        String[] campos = {CriaBanco.getColumnIdPerguntas()
//                , CriaBanco.getColumnEnunciadoPerguntas()
//                , CriaBanco.getColumnOpcaoAPerguntas()
//                , CriaBanco.getColumnOpcaoBPerguntas()
//                , CriaBanco.getColumnOpcaoCPerguntas()
//                , CriaBanco.getColumnOpcaoDPerguntas()
//                , CriaBanco.getColumnOpcaoEPerguntas()
//                , CriaBanco.getColumnOpcaoCorretaPerguntas()
//                , CriaBanco.getColumnFkConteudoPerguntas()
//        };
        //String where = CriaBanco.getColumnIdConteudo() + " = " + id;
        db = banco.getReadableDatabase();

        //cursor = db.query(CriaBanco.getColumnTabelaPerguntas(), campos, where, null, null, null, null, null);

        cursor = db.rawQuery("SELECT * FROM " + CriaBanco.getColumnTabelaPerguntas()
                       + " WHERE " + CriaBanco.getColumnIdPerguntas() + "=" + id
                , null);
        System.out.println(cursor.getCount());
        if (cursor.moveToFirst()) {
            int idPergunta = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnIdPerguntas()));
            //int idPergunta = cursor.getInt(0); TESTE
            String enunciado = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnEnunciadoPerguntas()));
            String opcaoA = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoAPerguntas()));
            String opcaoB = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoBPerguntas()));
            String opcaoC = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoCPerguntas()));
            String opcaoD = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoDPerguntas()));
            String opcaoE = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoEPerguntas()));
            char opcaoCorreta = cursor.getString(cursor.getColumnIndex(CriaBanco.getColumnOpcaoCorretaPerguntas())).charAt(0);
            int fkIdConteudo = cursor.getInt(cursor.getColumnIndex(CriaBanco.getColumnFkConteudoPerguntas()));

            pergunta = new Pergunta(idPergunta,
                    enunciado,
                    opcaoA,
                    opcaoB,
                    opcaoC,
                    opcaoD,
                    opcaoE,
                    opcaoCorreta,
                    carregaConteudoById(fkIdConteudo));
        }

        cursor.close();
        db.close();
        return pergunta;

    }

    public void deletaRegistroPergunta(int id) {
        String where = CriaBanco.getColumnIdPerguntas() + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.getColumnTabelaPerguntas(), where, null);
        db.close();
    }

}
