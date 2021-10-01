package com.tanuz.inmobiliariatanuz.ui.Inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {
    private ArrayList<Inmueble> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public InmueblesAdapter(ArrayList<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= layoutInflater.inflate(R.layout.item_inmuebles,parent,false);

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Inmueble items = lista.get(position);
        Glide.with(context)//contexto
                .load(items.getImagen()) //url de la imagen
                .diskCacheStrategy(DiskCacheStrategy.ALL) //guarda en el cache
                .into(holder.ivInmuebles); // setea la imagen

        holder.tvDireccion.setText(items.getDireccion());
        holder.tvPrecio.setText(items.getPrecio()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("i",items);
                Navigation.findNavController((Activity)context, R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.detalleInmuebleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccion, tvPrecio;
        private ImageView ivInmuebles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio =  itemView.findViewById(R.id.tvPrecio);
            ivInmuebles = itemView.findViewById(R.id.ivInmueble);

        }
    }
}
