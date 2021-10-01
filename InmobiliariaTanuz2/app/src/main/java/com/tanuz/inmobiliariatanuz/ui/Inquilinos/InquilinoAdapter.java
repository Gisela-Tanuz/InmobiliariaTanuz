package com.tanuz.inmobiliariatanuz.ui.Inquilinos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.modelo.Inmueble;

import java.util.ArrayList;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {
    private ArrayList<Inmueble> lista;
    private Context context;
    private LayoutInflater inflater;

    public InquilinoAdapter(ArrayList<Inmueble> lista, Context context, LayoutInflater inflater) {
        this.lista = lista;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inquilino,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     final Inmueble items = lista.get(position);
    holder.tvDireccion.setText(items.getDireccion());
        Glide.with(context)
                .load(items.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivPropAlq);
        holder.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", items);
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.detallesInquilinoFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccion;
        private ImageView ivPropAlq;
        private Button btVer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            ivPropAlq = itemView.findViewById(R.id.ivPropAlq);
            btVer = itemView.findViewById(R.id.btVer);
        }
    }
}
