package com.tanuz.inmobiliariatanuz;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanuz.inmobiliariatanuz.modelo.Propietario;
import com.tanuz.inmobiliariatanuz.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> usuario;
    private MutableLiveData<Integer> ok;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }


    public LiveData<Propietario> getUsuario(){
        if(usuario == null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }
    public LiveData<Integer> getOk(){
        if(ok == null){
            ok= new MutableLiveData<>();
        }
        return ok;
    }

    public void Ingresar(String email, String clave){
        if(email != null && clave != null && email.length()>0 && clave.length()>0) {
            ApiClient api = ApiClient.getApi();
            Propietario p = api.login(email, clave);
            if (p == null) {
                ok.setValue(View.VISIBLE);
            } else {
                ok.setValue(View.INVISIBLE);
                usuario.setValue(p);
                Intent i = new Intent(context, MenuNavegable.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("propietario",p);
                i.putExtra("propietario",bundle);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }


}
