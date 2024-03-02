package com.example.espertalhao;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    Button buttonCadastraConteudosMainMenu;
    Button buttonCadastraPerguntasMainMenu;
    Button buttonVisualizaPerguntasMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonCadastraConteudosMainMenu = findViewById(R.id.buttonCadastraConteudosMainMenu);
        buttonCadastraPerguntasMainMenu = findViewById(R.id.buttonCadastraPerguntasMainMenu);
        buttonVisualizaPerguntasMainMenu = findViewById(R.id.buttonVisualizaPerguntasMainMenu);

        buttonCadastraConteudosMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainMenuActivity.this, CadastroConteudoActivity.class);
                startActivity(it);
            }
        });

        buttonCadastraPerguntasMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainMenuActivity.this, CadastroPerguntaActivity.class);
                startActivity(it);
            }
        });

        buttonVisualizaPerguntasMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainMenuActivity.this, VisualizaPerguntasActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
