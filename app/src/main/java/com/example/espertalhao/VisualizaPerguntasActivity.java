package com.example.espertalhao;

import android.content.Intent;
import android.os.Bundle;

import com.example.espertalhao.model.Pergunta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class VisualizaPerguntasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewVisualizaPerguntas;
    private PerguntaAdapter perguntaAdapter;
    private App app;
    private DbHandler crud;
    private List<Pergunta> listaPerguntas;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_perguntas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(VisualizaPerguntasActivity.this, CadastroPerguntaActivity.class);
                startActivity(it);
                finish();
            }
        });

        recyclerViewVisualizaPerguntas = findViewById(R.id.recyclerViewVisualizaPerguntas);
        app = (App) getApplicationContext();

        //cria a instância do controller do BD
        //crud = new DbHandler(VisualizaPerguntasActivity.this);
        crud = new DbHandler(VisualizaPerguntasActivity.this);


        //armazena na lista os dados cadastrados com um SELECT
        listaPerguntas = crud.carregaPerguntas();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (listaPerguntas != null) {
            perguntaAdapter = new PerguntaAdapter(listaPerguntas, trataCliqueItem, trataCliqueLongoItem);
            recyclerViewVisualizaPerguntas.setLayoutManager(new LinearLayoutManager(VisualizaPerguntasActivity.this));
            recyclerViewVisualizaPerguntas.setItemAnimator(new DefaultItemAnimator());
            recyclerViewVisualizaPerguntas.setAdapter(perguntaAdapter);
        }
    }

    PerguntaAdapter.PerguntaOnClickListener trataCliqueItem = new PerguntaAdapter.PerguntaOnClickListener() {
        @Override
        public void onClickPergunta(View view, int position) {
            Intent it = new Intent(VisualizaPerguntasActivity.this, VisualizacaoDetalhadaActivity.class);
            //foi necessário adicionar +1 no position para ele referenciar o id correto
            ///+2 pois a base de dados está começando a contar o ID no 1
            Pergunta perguntaClicada = listaPerguntas.get(position);
            it.putExtra("pergunta", perguntaClicada);
            startActivity(it);
        }
    };

    PerguntaAdapter.PerguntaOnLongClickListener trataCliqueLongoItem = new PerguntaAdapter.PerguntaOnLongClickListener() {
        @Override
        public void onLongClickPergunta(View view, final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(VisualizaPerguntasActivity.this, R.style.Theme_DialogCustomizada);
            Button buttonCancelAlertDialogDeletePergunta, buttonYesAlertDialogDeletePergunta;
            TextView titulo, dados;

            builder.setView(R.layout.alert_dialog_delete_layout);
            //setContentView(R.layout.alert_dialog_delete_layout);

            alertDialog = builder.create();
            alertDialog.show();

            buttonCancelAlertDialogDeletePergunta = alertDialog.findViewById(R.id.buttonCancelAlertDialogDeletePergunta);
            buttonYesAlertDialogDeletePergunta = alertDialog.findViewById(R.id.buttonYesAlertDialogDeletePergunta);


            titulo=alertDialog.findViewById(R.id.textViewTituloDelete);
            dados = alertDialog.findViewById(R.id.textViewDadosToStringDelete);



            List<Pergunta> perguntasTeste = crud.carregaPerguntas();
            System.out.println(perguntasTeste.size());
            for(Pergunta pergunta : perguntasTeste){
                System.out.println(pergunta.toString());
            }



            titulo.setText(getResources().getString(R.string.title_delete_alert_dialog, listaPerguntas.get(position).getEnunciado()));
            dados.setText(getResources().getString(R.string.string_input, listaPerguntas.get(position).toString()));

            //se clicar para cancelar
            buttonCancelAlertDialogDeletePergunta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            //se desejar prosseguir com o delete
            buttonYesAlertDialogDeletePergunta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pergunta perguntaSelecionada = listaPerguntas.get(position);
                    crud.deletaRegistroPergunta(perguntaSelecionada.getIdPergunta());
                    perguntaAdapter.notifyItemRemoved(position);
                    listaPerguntas.remove(position);
                    alertDialog.dismiss();
                }
            });
        }
    };
}

