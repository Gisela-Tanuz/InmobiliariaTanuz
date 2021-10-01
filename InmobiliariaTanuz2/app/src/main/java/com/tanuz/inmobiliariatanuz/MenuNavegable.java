package com.tanuz.inmobiliariatanuz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.tanuz.inmobiliariatanuz.databinding.ActivityMenuNavegableBinding;
import com.tanuz.inmobiliariatanuz.modelo.Propietario;

public class MenuNavegable extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuNavegableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuNavegableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuNavegable.toolbar);
      /*  binding.appBarMenuNavegable.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;
        iniciarHeader(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_inmuebles,R.id.inquilinosFragment,R.id.contratosFragment,R.id.cerrarSesionFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_navegable);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navegable, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_navegable);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void iniciarHeader(NavigationView navigationView){

        View header = navigationView.getHeaderView(0);
        ImageView avatar = header.findViewById(R.id.ivAvatar);
        TextView nombre= header.findViewById(R.id.tvNombre);
        TextView email= header.findViewById(R.id.tvEmail);
        Propietario p = (Propietario) getIntent().getBundleExtra("propietario").getSerializable("propietario");
        //ApiClient api = ApiClient.getApi();
        //Propietario p = api.obtenerUsuarioActual();
        avatar.setImageResource(p.getAvatar());
        nombre.setText(p.getNombre()+" "+ p.getApellido());
        email.setText(p.getEmail());

    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        //este metodo es para que cuando vuelva hacia el login se cierre la app
        Log.d("saldia" ,"pulso el back");
        finishAffinity();
    }*/
}