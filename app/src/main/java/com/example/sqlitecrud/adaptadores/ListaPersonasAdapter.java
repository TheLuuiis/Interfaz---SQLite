package com.example.sqlitecrud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitecrud.R;
import com.example.sqlitecrud.VerActivity;
import com.example.sqlitecrud.entidades.Personas;

import java.util.ArrayList;

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonaViewHolder> {

    ArrayList<Personas> listaPersonas;

    public ListaPersonasAdapter(ArrayList<Personas> listaPersonas){
        this.listaPersonas = listaPersonas;

    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_persona, null, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        holder.viewNombre.setText(listaPersonas.get(position).getNombre());
        holder.viewTelefono.setText(listaPersonas.get(position).getTelefono());
        holder.viewCorreo.setText(listaPersonas.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo;


        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPersonas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
