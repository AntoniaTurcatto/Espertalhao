package com.example.espertalhao;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class VisualizacaoDetalhadaActivity extends AppCompatActivity {

    TextView textViewConteudoVisualizacaoDetalhada, textViewPerguntaVisualizacaoDetalhada,
            textViewOpcaoAVisualizacaoDetalhada,
            textViewOpcaoBVisualizacaoDetalhada,
            textViewOpcaoCVisualizacaoDetalhada,
            textViewOpcaoDVisualizacaoDetalhada,
            textViewOpcaoEVisualizacaoDetalhada,
            textViewOpcaoCorretaVisualizacaoDetalhada;

    android.content.res.Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewConteudoVisualizacaoDetalhada = findViewById(R.id.textViewConteudoVisualizacaoDetalhada);
        textViewPerguntaVisualizacaoDetalhada = findViewById(R.id.textViewPerguntaVisualizacaoDetalhada);

        textViewOpcaoAVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoAVisualizacaoDetalhada);
        textViewOpcaoBVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoBVisualizacaoDetalhada);
        textViewOpcaoCVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoCVisualizacaoDetalhada);
        textViewOpcaoDVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoDVisualizacaoDetalhada);
        textViewOpcaoEVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoEVisualizacaoDetalhada);

        textViewOpcaoCorretaVisualizacaoDetalhada = findViewById(R.id.textViewOpcaoCorretaVisualizacaoDetalhada);
        res = getResources();

        Intent it = getIntent();
        if(it != null && it.hasExtra("pergunta")){
            Pergunta pergunta = (Pergunta)it.getSerializableExtra("pergunta");
            textViewConteudoVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getConteudo().getNomeConteudo()));
            textViewPerguntaVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getEnunciado()));

            textViewOpcaoAVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getOpcaoA()));
            textViewOpcaoBVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getOpcaoB()));
            textViewOpcaoCVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getOpcaoC()));
            textViewOpcaoDVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getOpcaoD()));
            textViewOpcaoEVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView,pergunta.getOpcaoE()));

            textViewOpcaoCorretaVisualizacaoDetalhada.setText(res.getString(R.string.schoolSubjectCardView, pergunta.getOpcaoCorreta()));
        }
    }

}
