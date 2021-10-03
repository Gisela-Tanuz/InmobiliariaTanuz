package com.tanuz.inmobiliariatanuz.ui.Contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.PagosFragmentBinding;
import com.tanuz.inmobiliariatanuz.modelo.Contrato;
import com.tanuz.inmobiliariatanuz.modelo.Pago;

import java.util.ArrayList;

public class PagosFragment extends Fragment {

    private PagosViewModel mViewModel;
    private PagosFragmentBinding binding;
    private RecyclerView rv;
    private PagosAdapter pa;


    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.pagos_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
      binding = PagosFragmentBinding.inflate(inflater,container,false);
      View root = binding.getRoot();
      rv= binding.rvListaPagos;
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        mViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
             rv.setLayoutManager(gridLayoutManager);
             pa = new PagosAdapter(pagos,getContext(),getLayoutInflater());
             rv.setAdapter(pa);

            }
        });
        mViewModel.getTvNoPagos().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvNoPagos.setVisibility(integer);
            }
        });
        mViewModel.ObtenerPagos(getArguments());
                return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        // TODO: Use the ViewModel
    }

}