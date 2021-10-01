package com.tanuz.inmobiliariatanuz.ui.Contratos;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Contrato;
import com.tanuz.inmobiliariatanuz.modelo.Pago;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

import java.util.ArrayList;

public class PagosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Pago>> pagos;
    private ApiClient api;
    public LiveData<ArrayList<Pago>> getPagos(){
        if(pagos == null){
            pagos= new MutableLiveData<>();
        }
        return pagos;
    }

  public void ObtenerPagos(Bundle bundle){
      Contrato contrato = (Contrato) bundle.getSerializable("contrato");
      api = ApiClient.getApi();
      ArrayList<Pago> p = api.obtenerPagos(contrato);
      pagos.setValue(p);
  }

}