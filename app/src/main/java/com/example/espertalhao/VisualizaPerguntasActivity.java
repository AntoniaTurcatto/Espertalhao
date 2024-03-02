package com.example.espertalhao;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class VisualizaPerguntasActivity extends AppCompatActivity {

    RecyclerView recyclerViewVisualizaPerguntas;
    PerguntaAdapter perguntaAdapter;
    App app;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(app.getListaPergunta() != null){
            perguntaAdapter = new PerguntaAdapter(app.getListaPergunta(), trataCliqueItem);
            recyclerViewVisualizaPerguntas.setLayoutManager(new LinearLayoutManager(VisualizaPerguntasActivity.this));
            recyclerViewVisualizaPerguntas.setItemAnimator(new DefaultItemAnimator());
            recyclerViewVisualizaPerguntas.setAdapter(perguntaAdapter);
        }
    }

    PerguntaAdapter.PerguntaOnClickListener trataCliqueItem = new PerguntaAdapter.PerguntaOnClickListener() {
        @Override
        public void onClickPergunta(View view, int position) {
            Intent it = new Intent(VisualizaPerguntasActivity.this, VisualizacaoDetalhadaActivity.class);
            it.putExtra("pergunta",app.getListaPergunta().get(position));
            startActivity(it);
        }
    };

}
