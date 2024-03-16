package com.example.espertalhao;

import android.os.Bundle;

import com.example.espertalhao.model.Conteudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroConteudoActivity extends AppCompatActivity {

    String[] tipoConteudos;
    EditText editTextNomeCadastroConteudo;
    Spinner spinnerTipoConteudoCadastroConteudo;
    Button buttonCadastrarCadastrarConteudo;
    android.content.res.Resources res;

    DbHandler crud;

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

        //INSTANCIANDO O BD
        crud = new DbHandler(CadastroConteudoActivity.this);



        buttonCadastrarCadastrarConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerTipoConteudoCadastroConteudo.getSelectedItemPosition() > 0 && !editTextNomeCadastroConteudo.getText().toString().isEmpty()){ //se não estiver selecionado o "--Selecionar--" E se não estiver sem nome

                    Conteudo conteudo = new Conteudo(editTextNomeCadastroConteudo.getText().toString(),
                            spinnerTipoConteudoCadastroConteudo.getSelectedItemPosition());

                    //app.getListaConteudo().add(conteudo);
                    try {
                        String consulta = crud.insereConteudo(conteudo);
                        Toast.makeText(CadastroConteudoActivity.this, consulta, Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CadastroConteudoActivity.this, res.getString(R.string.alert), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
