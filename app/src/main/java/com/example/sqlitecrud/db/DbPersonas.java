package com.example.sqlitecrud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqlitecrud.entidades.Personas;

import java.util.ArrayList;

public class DbPersonas extends DbHelper {

    Context context;

    public DbPersonas(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long insertarPersona(String nombre, String telefono, String correo_electronico){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

             id = db.insert(TABLE_PERSONAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;

    }

    public ArrayList<Personas> mostrarPersonas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Personas> listaPersonas = new ArrayList<>();
        Personas persona = null;
        Cursor cursorPersonas = null;

        cursorPersonas = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS, null);

        if (cursorPersonas.moveToFirst()){
            do {
                persona = new Personas();
                persona.setId(cursorPersonas.getInt(0));
                persona.setNombre(cursorPersonas.getString(1));
                persona.setTelefono(cursorPersonas.getString(2));
                persona.setCorreo_electronico(cursorPersonas.getString(3));
                listaPersonas.add(persona);
            } while (cursorPersonas.moveToNext());
        }
        cursorPersonas.close();

        return listaPersonas;


    }
    public Personas verPersona(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Personas persona = null;
        Cursor cursorPersonas;

        cursorPersonas = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPersonas.moveToFirst()){
                persona = new Personas();
                persona.setId(cursorPersonas.getInt(0));
                persona.setNombre(cursorPersonas.getString(1));
                persona.setTelefono(cursorPersonas.getString(2));
                persona.setCorreo_electronico(cursorPersonas.getString(3));
        }
        cursorPersonas.close();

        return persona;


    }
    public boolean editarPersona(int id, String nombre, String telefono, String correo_electronico){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_PERSONAS + " SET nombre = '"+nombre+"', telefono = '"+telefono+"', correo_electronico = '"+correo_electronico+"' WHERE id='"+ id +"' ");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;

    }
    public boolean eliminarPersona(int id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_PERSONAS + " WHERE id = '"+id+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;

    }


}
