package com.tanuz.inmobiliariatanuz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.tanuz.inmobiliariatanuz.modelo.Propietario;

public class MainActivity extends AppCompatActivity {

    private EditText email, clave;
    private Button ingresar;
    private TextView mensaje;
    private ImageView imagen;
    private MainActivityViewModel vm;
    private MainAcivitySensor ms;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
        //permisos para el sensor
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);

        ms = new MainAcivitySensor(getApplicationContext());
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this.ms,sensor,SensorManager.SENSOR_DELAY_NORMAL);



        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getOk().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mensaje.setVisibility(integer);
            }
        });
        vm.getUsuario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                email.setText(propietario.getEmail());
                clave.setText(propietario.getContraseña());
            }
        });

    }


    public void inicializarVista(){
        //     vm= new MainActivityViewModel();
        email= findViewById(R.id.etEmail);
        clave= findViewById(R.id.etClave);
        ingresar= findViewById(R.id.btIngresar);
        mensaje = findViewById(R.id.tvMensaje);
        imagen=findViewById(R.id.ivfotoInm);
        imagen.setImageResource(R.drawable.imob);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.Ingresar(email.getText().toString(),clave.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this.ms,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(ms);
    }
}