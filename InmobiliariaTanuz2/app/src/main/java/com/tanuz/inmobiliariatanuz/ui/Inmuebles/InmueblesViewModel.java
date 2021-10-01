package com.tanuz.inmobiliariatanuz.ui.Inmuebles;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

import java.util.ArrayList;

public class InmueblesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmueble;
    private MutableLiveData<Integer> tvNoInmueble;

    public LiveData<ArrayList<Inmueble>> getInmueble(){
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }
    public LiveData<Integer> getTvNoInmueble(){
        if(tvNoInmueble == null){
            tvNoInmueble = new MutableLiveData<>();
        }
        return tvNoInmueble;
    }

    public void ObtenerInmueble(){
        ApiClient apiClient = ApiClient.getApi();
        ArrayList<Inmueble> i = apiClient.obtnerPropiedades();

            inmueble.setValue(i);
            tvNoInmueble.setValue(View.INVISIBLE);
        }

}