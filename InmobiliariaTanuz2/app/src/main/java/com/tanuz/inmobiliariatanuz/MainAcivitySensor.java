package com.tanuz.inmobiliariatanuz;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;

public class MainAcivitySensor implements SensorEventListener {
    private Context context;
    private long whip = 0;
    private float whip_x, whip_y,whip_z;
    private int SHAKE_THERSHOLD = 600;

    public MainAcivitySensor(Context context) {
        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor s   = event.sensor;
        if(s.getType()== Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            long currentTime  = System.currentTimeMillis();

            if((currentTime - whip)>100){
             long dif = (currentTime - whip);
             whip = currentTime;
             float mover =Math.abs(x+y+z - whip_x-whip_y-whip_z)/dif*10000;

             if(mover > SHAKE_THERSHOLD){
                 hacerLLamada();
             }
             whip_x = x;
             whip_y = y;
             whip_z = z;
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void hacerLLamada() {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel: 12345"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
