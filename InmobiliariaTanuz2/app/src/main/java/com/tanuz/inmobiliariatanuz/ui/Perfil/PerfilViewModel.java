package com.tanuz.inmobiliariatanuz.ui.Perfil;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Propietario;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Propietario> usuario;
    private MutableLiveData<Integer> visibleEditar;
    private MutableLiveData<Integer> visibleGuardar;
    private MutableLiveData<Boolean> estadoEditable;


    public LiveData<Propietario> getUsuario() {

        if(usuario == null){
            usuario = new MutableLiveData<>();
        }

        return usuario;
    }
    public MutableLiveData <Integer> getVisibleEditar(){
        if(visibleEditar == null){
            visibleEditar = new MutableLiveData<>();
        }
        return visibleEditar;
    }
    public MutableLiveData <Integer> getVisibleGuardar(){
        if(visibleGuardar == null){
            visibleGuardar = new MutableLiveData<>();
        }
        return visibleGuardar;
    }
    public MutableLiveData<Boolean> getEstadoEditable(){
        if (estadoEditable == null){
            estadoEditable= new MutableLiveData<>();
        }
        return estadoEditable;
    }

    public void ObtenerUsusario() {
        ApiClient api= ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();
        usuario.setValue(p);
    }
    public  void modificarDatos(Propietario p){
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(p);

        visibleEditar.setValue(View.VISIBLE);
        visibleGuardar.setValue(View.INVISIBLE);
        estadoEditable.setValue(false);

    }
    public void cambiarEstado(){
        estadoEditable.setValue(true);
        visibleEditar.setValue(View.INVISIBLE);
        visibleGuardar.setValue(View.VISIBLE);
    }
}