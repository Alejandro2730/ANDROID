package com.example.semana10.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana10.Clases.Anime;
import com.example.semana10.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter {

    private List<Anime> items;

    public NameAdapter(List<Anime> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cont, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Anime item = items.get(position);
        View view = holder.itemView;

        TextView tvName = view.findViewById(R.id.TXTnombre);
        TextView tvdescipcion = view.findViewById(R.id.TXTdescripcion);
        ImageView imageView = view.findViewById(R.id.imageView);
        tvName.setText(item.getNombre());
        tvdescipcion.setText(item.getDescripcion());

        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();//para el numero de objetos en la lista
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
