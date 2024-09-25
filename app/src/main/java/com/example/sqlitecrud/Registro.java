package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitecrud.db.DbUsuario;
import com.example.sqlitecrud.entidades.Usuario;

public class Registro extends AppCompatActivity implements View.OnClickListener {
EditText us,pas;
Button reg, can;
DbUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        us=(EditText)findViewById(R.id.txtUs);
        pas=(EditText)findViewById(R.id.txtPas);
        reg=(Button)findViewById(R.id.btnReg);
        can=(Button)findViewById(R.id.btnCan);

        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        db= new DbUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReg:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if (db.insertUsuario(u)){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(Registro.this, Login.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnCan:
                Intent i=new Intent(Registro.this, Login.class);
                startActivity(i);
                finish();
                break;
        }
    }
}