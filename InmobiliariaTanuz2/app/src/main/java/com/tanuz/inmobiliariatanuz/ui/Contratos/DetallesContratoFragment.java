package com.tanuz.inmobiliariatanuz.ui.Contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.DetallesContratoFragmentBinding;
import com.tanuz.inmobiliariatanuz.modelo.Contrato;
import com.tanuz.inmobiliariatanuz.modelo.Pago;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Objects;

public class DetallesContratoFragment extends Fragment {

    private DetallesContratoViewModel mViewModel;
    private DetallesContratoFragmentBinding binding;
    private PagosAdapter pa;

    @NonNull

    public static DetallesContratoFragment newInstance() {
        return new DetallesContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DetallesContratoViewModel.class);
        binding = DetallesContratoFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
           binding.tvCodigoCont.setText("Codigo: "+contrato.getIdContrato());
           binding.tvFechInicio.setText("Fecha de inicial: "+contrato.getFechaInicio());
           binding.tvFechFin.setText("Fecha final: "+contrato.getFechaFin());
           binding.tvPrecioAlq.setText("Precio: "+ contrato.getMontoAlquiler());
           binding.tvInquilino.setText("Nombre del inquilino: "+contrato.getInquilino().getNombre() + " " +contrato.getInquilino().getApellido());
           binding.tvInmueble.setText("Dirección del inmueble: "+contrato.getInmueble().getDireccion());
           binding.btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato",contrato);
                        Navigation.findNavController((Activity) getContext(), R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.pagosFragment,bundle);



                            }
                        });
                    }
                });



        mViewModel.ObtenerContrato(getArguments());

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallesContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}