package com.tanuz.inmobiliariatanuz.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.FragmentPerfilBinding;
import com.tanuz.inmobiliariatanuz.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private FragmentPerfilBinding binding;
    private EditText id,dni,nombre, apellido, email, clave, telefono;
    private ImageView foto;
    private Button editar ,guardar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar(root);
        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                id.setText(propietario.getId()+"");
                dni.setText(propietario.getDni().toString());
                nombre.setText(propietario.getNombre());
                apellido.setText(propietario.getApellido());
                email.setText(propietario.getEmail());
                clave.setText(propietario.getContraseña());
                telefono.setText(propietario.getTelefono());
                foto.setImageResource(propietario.getAvatar());
            }
        });
        perfilViewModel.getEstadoEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // id.setEnabled(aBoolean);
                dni.setEnabled(aBoolean);
                nombre.setEnabled(aBoolean);
                apellido.setEnabled(aBoolean);
                email.setEnabled(aBoolean);
                clave.setEnabled(aBoolean);
                telefono.setEnabled(aBoolean);
            }
        });
        perfilViewModel.getVisibleEditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editar.setVisibility(integer);
            }
        });
        perfilViewModel.getVisibleGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                guardar.setVisibility(integer);
            }
        });
        perfilViewModel.ObtenerUsusario();


        return root;
    }
    private void inicializar(View v){

        id= v.findViewById(R.id.etId);
        dni=v.findViewById(R.id.etDni);
        nombre=v.findViewById(R.id.etNombre);
        apellido=v.findViewById(R.id.etApellido);
        email=v.findViewById(R.id.etEmail);
        clave=v.findViewById(R.id.etClave);
        telefono= v.findViewById(R.id.etTelefono);
        foto= v.findViewById(R.id.ivAvatar);
        editar= v.findViewById(R.id.btEditar);
        guardar= v.findViewById(R.id.btGuardar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perfilViewModel.cambiarEstado();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p = new Propietario();
                p.setId(Integer.parseInt(id.getText().toString()));
                p.setDni(Long.parseLong(dni.getText().toString()));
                p.setNombre(nombre.getText().toString());
                p.setApellido(apellido.getText().toString());
                p.setEmail(email.getText().toString());
                p.setContraseña(clave.getText().toString());
                p.setTelefono(telefono.getText().toString());

                perfilViewModel.modificarDatos(p);


            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}