package com.tanuz.inmobiliariatanuz.ui.Contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.modelo.Contrato;
import com.tanuz.inmobiliariatanuz.modelo.Pago;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
   private ArrayList<Pago> lista;
   private Context context;
   private LayoutInflater inflater;

    public PagosAdapter(ArrayList<Pago> lista, Context context, LayoutInflater inflater) {
        this.lista = lista;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagos,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        final Pago p = lista.get(position);
        holder.tvCodigoPago.setText("Codigo de pago: "+p.getIdPago());
        holder.tvNumero.setText("Numero de pago: "+p.getNumero());
        holder.tvCodigoContrato.setText("Cogido de contrato: "+ p.getContrato().getIdContrato());
        holder.tvImporte.setText("Importe: "+p.getImporte());
        holder.tvFechaPago.setText("Fecha de Pago: "+p.getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigoPago, tvNumero,tvCodigoContrato,tvImporte,tvFechaPago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvCodigoContrato= itemView.findViewById(R.id.tvCodigoContrato);
            tvImporte= itemView.findViewById(R.id.tvImporte);
            tvFechaPago= itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
