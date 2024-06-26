package com.cursos.posgrado.cursoandroidv2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursos.posgrado.cursoandroidv2.basedatos.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    private List<Persona> personas = new ArrayList<>();
    private OnEditClickListener editClickListener;

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
        notifyDataSetChanged();
    }

    public void setOnEditClickListener(OnEditClickListener listener) {
        this.editClickListener = listener;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas, parent, false);
        return new PersonaViewHolder(view, editClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = personas.get(position);
        holder.nombre.setText(persona.getNombre());
        holder.edad.setText(String.valueOf(persona.getEdad()));
        holder.direccion.setText(persona.getDireccion());
        holder.itemView.setTag(persona);
    }


    @Override
    public int getItemCount() {
        return personas.size();
    }

    public interface OnEditClickListener {
        void onEditClick(Persona persona);
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, direccion, edad;
        Button editButton;

        public PersonaViewHolder(@NonNull View itemView, OnEditClickListener listener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.text_view_nombre);
            edad = itemView.findViewById(R.id.text_view_edad);
            direccion = itemView.findViewById(R.id.text_view_direccion);
            editButton = itemView.findViewById(R.id.buttonEdit);

            editButton.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onEditClick((Persona) itemView.getTag());
                }
                Log.d("MainActivity", "Haciendo click en Editar");
            });
        }
    }
}

