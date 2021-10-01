package com.tanuz.inmobiliariatanuz.ui.Inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

public class DetalleInmuebleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Inmueble> inmuebles;
    private Inmueble i;

    public LiveData<Inmueble> getInmueble(){
        if(inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }
    public void obtenerInmuebles(Bundle bundle){
         i = (Inmueble) bundle.getSerializable("i");
        inmuebles.setValue(i);
    }
    public void guardarCambios(Boolean check){
        i.setEstado(check);
        ApiClient api = ApiClient.getApi();
        api.actualizarInmueble(i);
    }
}