package com.example.espertalhao;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroPerguntaActivity extends AppCompatActivity {

    EditText editTextEnunciadoCadastrarPerguntas, editTextOptionACadastrarPerguntas, editTextOptionBCadastrarPerguntas,
            editTextOptionCCadastrarPerguntas, editTextOptionDCadastrarPerguntas, editTextOptionECadastrarPerguntas;

    Spinner spinnerConteudoCadastroPerguntas, spinnerOpcaoCorretaCadastroPerguntas;

    Button buttonCadastrarCadastrarPergunta;

    String[] listaConteudos;
    String[] opcoes;

    App app;
    android.content.res.Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pergunta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEnunciadoCadastrarPerguntas = findViewById(R.id.editTextEnunciadoCadastrarPerguntas);

        editTextOptionACadastrarPerguntas = findViewById(R.id.editTextOptionACadastrarPerguntas);
        editTextOptionBCadastrarPerguntas = findViewById(R.id.editTextOptionBCadastrarPerguntas);
        editTextOptionCCadastrarPerguntas = findViewById(R.id.editTextOptionCCadastrarPerguntas);
        editTextOptionDCadastrarPerguntas = findViewById(R.id.editTextOptionDCadastrarPerguntas);
        editTextOptionECadastrarPerguntas = findViewById(R.id.editTextOptionECadastrarPerguntas);

        spinnerConteudoCadastroPerguntas = findViewById(R.id.spinnerConteudoCadastroPerguntas);
        spinnerOpcaoCorretaCadastroPerguntas = findViewById(R.id.spinnerOpcaoCorretaCadastroPerguntas);

        buttonCadastrarCadastrarPergunta = findViewById(R.id.buttonCadastrarCadastrarPergunta);

        app = (App)getApplicationContext();
        res = getResources();

        listaConteudos = new String[app.getListaConteudo().size()+1];
        opcoes = new String[6];
        listaConteudos[0]= res.getString(R.string.select);
        opcoes[0] = res.getString(R.string.select);
        opcoes[1] = "A";
        opcoes[2] = "B";
        opcoes[3] = "C";
        opcoes[4] = "D";
        opcoes[5] = "E";

        //listaConteudos == strings para o spinner
        //listaConteudo == conteudos cadastrados
        for(int i = 0; i < app.getListaConteudo().size(); i++){
            Conteudo conteudo = app.getListaConteudo().get(i);
            listaConteudos[i+1] = conteudo.getNomeConteudo();
        }

        spinnerConteudoCadastroPerguntas.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listaConteudos));

        spinnerOpcaoCorretaCadastroPerguntas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcoes));

        spinnerConteudoCadastroPerguntas.setSelection(0); //começar no --Selecionar--
        spinnerOpcaoCorretaCadastroPerguntas.setSelection(0); //começar no --Selecionar--


        //configuração do clique no botão
        buttonCadastrarCadastrarPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CRIAR O IF PARA TESTAR SE TUDO FOI PREENCHIDO
                if(!editTextEnunciadoCadastrarPerguntas.getText().toString().isEmpty() &&
                        !editTextOptionACadastrarPerguntas.getText().toString().isEmpty() &&
                        !editTextOptionBCadastrarPerguntas.getText().toString().isEmpty() &&
                        !editTextOptionCCadastrarPerguntas.getText().toString().isEmpty() &&
                        !editTextOptionDCadastrarPerguntas.getText().toString().isEmpty() &&
                        !editTextOptionECadastrarPerguntas.getText().toString().isEmpty() &&
                        spinnerOpcaoCorretaCadastroPerguntas.getSelectedItemPosition() > 0 &&
                        spinnerConteudoCadastroPerguntas.getSelectedItemPosition() > 0){

                    int idPergunta = app.getListaPergunta().size(); // não faz -1 pois não foi adicionado na lista ainda, portanto será 0
                    String enunciado = editTextEnunciadoCadastrarPerguntas.getText().toString();
                    String opcaoA = editTextOptionACadastrarPerguntas.getText().toString();
                    String opcaoB = editTextOptionBCadastrarPerguntas.getText().toString();
                    String opcaoC = editTextOptionCCadastrarPerguntas.getText().toString();
                    String opcaoD = editTextOptionDCadastrarPerguntas.getText().toString();
                    String opcaoE = editTextOptionECadastrarPerguntas.getText().toString();
                    char opcaoCorreta = spinnerOpcaoCorretaCadastroPerguntas.getSelectedItem().toString().charAt(0);
                    Conteudo conteudo = app.getListaConteudo().get(spinnerConteudoCadastroPerguntas.getSelectedItemPosition() - 1); // -1 por conta do --Selecionar--

                    Pergunta pergunta = new Pergunta(idPergunta, enunciado, opcaoA, opcaoB, opcaoC, opcaoD, opcaoE,opcaoCorreta, conteudo);
                    app.getListaPergunta().add(pergunta);
                    Toast.makeText(app, res.getString(R.string.registerQuestionSuccess, pergunta.getEnunciado()), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(app, res.getString(R.string.alert), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
