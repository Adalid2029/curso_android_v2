package com.cursos.posgrado.cursoandroidv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursos.posgrado.cursoandroidv2.basedatos.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    private List<Persona> personas = new ArrayList<>();

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = personas.get(position);
        holder.nombre.setText(persona.getNombre());
        holder.edad.setText(String.valueOf(persona.getEdad()));
        holder.direccion.setText(persona.getDireccion());
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, direccion, edad;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.text_view_nombre);
            edad = itemView.findViewById(R.id.text_view_edad);
            direccion = itemView.findViewById(R.id.text_view_direccion);
        }
    }
}

