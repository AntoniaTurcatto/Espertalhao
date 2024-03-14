package com.example.espertalhao;

import android.content.Intent;
import android.os.Bundle;

import com.example.espertalhao.model.Pergunta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class VisualizaPerguntasActivity extends AppCompatActivity {

    RecyclerView recyclerViewVisualizaPerguntas;
    PerguntaAdapter perguntaAdapter;
    App app;
    DbHandler crud;
    List<Pergunta> listaPerguntas;

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
        app = (App)getApplicationContext();

        //cria a instância do controller do BD
        //crud = new DbHandler(VisualizaPerguntasActivity.this);
        crud = new DbHandler(VisualizaPerguntasActivity.this);


        //armazena na lista os dados cadastrados com um SELECT
        listaPerguntas = crud.carregaPerguntas();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(listaPerguntas != null){
            perguntaAdapter = new PerguntaAdapter(listaPerguntas, trataCliqueItem);
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
            Pergunta perguntaClicada = crud.getPerguntaWhereId(position+1);
            it.putExtra("pergunta", perguntaClicada);
            startActivity(it);
        }
    };

}
