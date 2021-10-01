package com.tanuz.inmobiliariatanuz.ui.Contratos;

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
import com.tanuz.inmobiliariatanuz.modelo.Contrato;
import com.tanuz.inmobiliariatanuz.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
  private ArrayList<Inmueble> lista;
  private Context context;
  private LayoutInflater inflater;

    public ContratoAdapter(ArrayList<Inmueble> lista, Context context, LayoutInflater inflater) {
        this.lista = lista;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.item_contratos,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {
      final Inmueble items = lista.get(position);
      holder.tvDirecciones.setText(items.getDireccion());
        Glide.with(context)
                .load(items.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivPropAlqContrato);
        holder.btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Inmueble",items);
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.detallesContratoFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvDirecciones;
       ImageView ivPropAlqContrato;
       Button btMostrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDirecciones = itemView.findViewById(R.id.tvDirecciones);
            ivPropAlqContrato = itemView.findViewById(R.id.ivPropAlqContrato);
            btMostrar = itemView.findViewById(R.id.btMostrar);
        }
    }
}
