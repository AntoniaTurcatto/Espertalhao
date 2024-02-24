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

import java.sql.SQLOutput;

public class CadastroConteudoActivity extends AppCompatActivity {

    App app;
    String[] tipoConteudos;
    EditText editTextNomeCadastroConteudo;
    Spinner spinnerTipoConteudoCadastroConteudo;
    Button buttonCadastrarCadastrarConteudo;
    android.content.res.Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextNomeCadastroConteudo = findViewById(R.id.editTextNomeCadastroConteudo);
        spinnerTipoConteudoCadastroConteudo = findViewById(R.id.spinnerTipoConteudoCadastroConteudo);
        buttonCadastrarCadastrarConteudo = findViewById(R.id.buttonCadastrarCadastrarConteudo);

        res = getResources(); //instancia os resources para usar as strings
        app = (App)getApplicationContext();
        //+1 por causa do -- selecionar --
        tipoConteudos = new String[4];
        tipoConteudos[0] = res.getString(R.string.select);
        tipoConteudos[1] = res.getString(R.string.exactSciences);
        tipoConteudos[2] = res.getString(R.string.humanSciences);
        tipoConteudos[3] = res.getString(R.string.languages);

        spinnerTipoConteudoCadastroConteudo.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                tipoConteudos));

        spinnerTipoConteudoCadastroConteudo.setSelection(0); //começa no -- Selecionar --
                buttonCadastrarCadastrarConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerTipoConteudoCadastroConteudo.getSelectedItemPosition() > 0 && !editTextNomeCadastroConteudo.getText().toString().isEmpty()){ //se não estiver selecionado o "--Selecionar--" E se não estiver sem nome
                    Conteudo conteudo = new Conteudo(app.getListaConteudo().size(), //o id vai ser o mesmo numero que o seu index, não levou -1 pois ainda não foi adicionado na lista, então tbm vai ser 0
                            editTextNomeCadastroConteudo.getText().toString(),
                            spinnerTipoConteudoCadastroConteudo.getSelectedItemPosition());
                    app.getListaConteudo().add(conteudo);
                    Toast.makeText(app, res.getString(R.string.registerSuccess, conteudo.getNomeConteudo(), conteudo.getTipoConteudo(), conteudo.getIdConteudo()), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(app, res.getString(R.string.alert), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
