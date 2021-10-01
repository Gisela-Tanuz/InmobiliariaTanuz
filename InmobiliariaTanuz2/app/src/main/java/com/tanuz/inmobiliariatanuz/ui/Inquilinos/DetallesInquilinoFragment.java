package com.tanuz.inmobiliariatanuz.ui.Inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.DetalleInmuebleFragmentBinding;
import com.tanuz.inmobiliariatanuz.databinding.DetallesInquilinoFragmentBinding;
import com.tanuz.inmobiliariatanuz.modelo.Inquilino;

public class DetallesInquilinoFragment extends Fragment {

    private DetallesInquilinoViewModel mViewModel;
    private DetallesInquilinoFragmentBinding binding;

    public static DetallesInquilinoFragment newInstance() {
        return new DetallesInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     //   return inflater.inflate(R.layout.detalles_inquilino_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(DetallesInquilinoViewModel.class);
         binding = DetallesInquilinoFragmentBinding.inflate(inflater,container,false);
         View root = binding.getRoot();
mViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
    @Override
    public void onChanged(Inquilino inquilino) {
        binding.tvCodigoInq.setText("Codigo:  " +inquilino.getIdInquilino());
        binding.tvNombreInq.setText("Nombre y apellido:  "+ inquilino.getNombre()+" "+ inquilino.getApellido());
        binding.tvDniInq.setText("D.N.I:  "+ inquilino.getDNI());
        binding.tvEmailInq.setText("E-mail:  "+ inquilino.getEmail() );
        binding.tvTelInquilino.setText("Telefono : "+inquilino.getTelefono());
        binding.tvLugarTrabajo.setText("Lugar de trabajo: "+inquilino.getLugarDeTrabajo());
        binding.tvGarante.setText("Garante: "+inquilino.getNombreGarante());
        binding.tvTelGarante.setText("Telefono del garante: "+inquilino.getTelefonoGarante());
    }
});

mViewModel.obtenerInquilino(getArguments());
return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallesInquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}