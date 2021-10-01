package com.tanuz.inmobiliariatanuz.ui.Inquilinos;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Inmueble;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

import java.util.ArrayList;

public class InquilinosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
private MutableLiveData<ArrayList<Inmueble>> inmueble;
private MutableLiveData<Integer>tvNoInmueble;

public LiveData<ArrayList<Inmueble>> getInmueble(){
    if(inmueble == null){
        inmueble = new MutableLiveData<>();
    }
    return inmueble;
}
    public LiveData<Integer> getNoInmueble(){
        if(tvNoInmueble == null){
            tvNoInmueble = new MutableLiveData<>();
        }
        return tvNoInmueble;
    }

public void obtenerPropAlquilada(){
    ApiClient api = ApiClient.getApi();
    ArrayList<Inmueble> i = api.obtenerPropiedadesAlquiladas();

       inmueble.setValue(i);
       tvNoInmueble.setValue(View.INVISIBLE);
   }


}