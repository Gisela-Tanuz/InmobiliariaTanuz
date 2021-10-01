package com.tanuz.inmobiliariatanuz.ui.Contratos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Contrato;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.modelo.Pago;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

import java.util.ArrayList;

public class DetallesContratoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Contrato> contrato;

   private ApiClient api;


    public LiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }


    public void ObtenerContrato(@NonNull Bundle bundle){
     api = ApiClient.getApi();
     Contrato c = api.obtenerContratoVigente((Inmueble) bundle.getSerializable("Inmueble"));
     contrato.setValue(c);
    }

}