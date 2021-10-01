package com.tanuz.inmobiliariatanuz.ui.Inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.InquilinosFragmentBinding;
import com.tanuz.inmobiliariatanuz.modelo.Inmueble;

import java.util.ArrayList;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel mViewModel;
    private InquilinosFragmentBinding binding;
    private RecyclerView rv;
    private InquilinoAdapter ia;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.inquilinos_fragment, container, false);

        mViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = InquilinosFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        rv = binding.rvListaInquilino;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rv.setLayoutManager(gridLayoutManager);
                ia = new InquilinoAdapter(inmuebles,getContext(),getLayoutInflater());
                rv.setAdapter(ia);
            }
        });
        mViewModel.getNoInmueble().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvNoInquilinos.setVisibility(integer);
            }
        });
       mViewModel.obtenerPropAlquilada();
    return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        // TODO: Use the ViewModel
    }

}