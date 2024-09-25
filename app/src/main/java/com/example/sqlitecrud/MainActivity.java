package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlitecrud.adaptadores.ListaPersonasAdapter;
import com.example.sqlitecrud.db.DbHelper;
import com.example.sqlitecrud.db.DbPersonas;
import com.example.sqlitecrud.entidades.Personas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listapersonas;
    ArrayList<Personas> listaArrayPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listapersonas = findViewById(R.id.listaContactos);
        listapersonas.setLayoutManager(new LinearLayoutManager(this));

        DbPersonas dbPersonas = new DbPersonas(MainActivity.this);

        listaArrayPersonas = new ArrayList<>();

        ListaPersonasAdapter adapter = new ListaPersonasAdapter(dbPersonas.mostrarPersonas());
        listapersonas.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}