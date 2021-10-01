package com.tanuz.inmobiliariatanuz.ui.Inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.modelo.Inquilino;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

public class DetallesInquilinoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Inquilino> inquilino;

    public LiveData<Inquilino> getInquilino(){
        if(inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }
    public void obtenerInquilino(Bundle bundle){

        ApiClient api = ApiClient.getApi();
        Inquilino i = api.obtenerInquilino(bundle.getSerializable("item"));
        inquilino.setValue(i);
            }
}