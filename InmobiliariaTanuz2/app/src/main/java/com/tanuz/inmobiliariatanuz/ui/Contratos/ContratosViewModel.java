package com.tanuz.inmobiliariatanuz.ui.Contratos;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private MutableLiveData<Integer>tvNoContrato;


    public LiveData<ArrayList<Inmueble>> getInmueble(){
        if(inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }
    public LiveData<Integer> getNoContrato(){
        if(tvNoContrato == null){
            tvNoContrato = new MutableLiveData<>();
        }
        return tvNoContrato;
    }
    public void  ObtenerInmPorContrato() {
        ApiClient api = ApiClient.getApi();
        ArrayList<Inmueble> i = api.obtenerPropiedadesAlquiladas();
        if (i.size() == 0) {
            tvNoContrato.setValue(View.VISIBLE);
        } else {
            inmuebles.setValue(i);
            tvNoContrato.setValue(View.INVISIBLE);
        }
        }

}