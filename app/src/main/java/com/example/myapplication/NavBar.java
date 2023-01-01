package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBar extends AppCompatActivity {
    Home home= new Home();
    Finanzas finanzas = new Finanzas();
    estadistica estadistica = new estadistica();
    VerPerfil verPerfil=new VerPerfil();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_nav_bar);


        Bundle miBundle = this.getIntent().getExtras();
        if (miBundle != null) {
            String correo= miBundle.getString("correo");
            Bundle args= new Bundle();
            args.putString("correo", correo);
            verPerfil.setArguments(args);
            home.setArguments(args);
        }else {
            Toast.makeText(NavBar.this, "no hay parametros", Toast.LENGTH_LONG).show();
        }

        BottomNavigationView navigation=findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(home);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                loadFragment(home);
                return true;
                case R.id.Finanzas:
                    loadFragment(finanzas);
                    return true;
                case R.id.estadistica:
                    loadFragment(estadistica);
                    return true;
                case R.id.verPerfil:
                    loadFragment(verPerfil);
                    return  true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer,fragment);
        transaction.commit();
    }


}