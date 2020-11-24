package com.project.app.aplicacionmovil;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;

public class MenuP extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String user;
    private ImageButton btnHorarios;
    private ImageButton btnTareas;
    private ImageButton btnConsejos;
    private ImageButton btnPlanificacion;
    private NavigationView navigationView;
    private ImageView abrirMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_menup);


        /**
         * Cambiar para objeto tipo user
         */
        Intent intent = getIntent();
        this.user = intent.getStringExtra("User");

        btnHorarios = (ImageButton)findViewById(R.id.btnhorarios);
        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(2, user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }


            }
        });

        btnTareas=(ImageButton)findViewById(R.id.btntTareas);
        btnTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(3, user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });

        btnConsejos=(ImageButton)findViewById(R.id.btnConsejos);
        btnConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(4, user );
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });

        btnPlanificacion=(ImageButton)findViewById(R.id.btnPlanificacion);
        btnPlanificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con = conexion();
                if(con)
                {
                    openMenuPrincipal(5,user);
                }
                else
                {
                    Intent intent = new Intent(MenuP.this, Internet.class);
                    startActivity(intent);
                }

            }
        });

        navigationView = (NavigationView)findViewById(R.id.nav_view_menup) ;
        abrirMenu = (ImageView)findViewById(R.id.abrirMenu);
        navigationView.setItemIconTintList(null);
        abrirMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });


        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    public void openMenuPrincipal(int opcion, String user)
    {
        if(opcion == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(opcion == 2)
        {
            Intent intent = new Intent(this,Horarios.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        else if(opcion == 3)
        {
            Intent intent = new Intent(this, Tareas.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }

        else if(opcion == 4)
        {
            Intent intent = new Intent(this, consejos_manager.class);
            startActivity(intent);
        }

        else if(opcion == 5)
        {
            Intent intent = new Intent(this, Planificacion.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }

    }

    public boolean conexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isConnected())
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No está conectado a internet", Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navConfig) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.navHome)
        {
            Toast.makeText(getApplicationContext(), "Ya te encuentras en el inicio", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}