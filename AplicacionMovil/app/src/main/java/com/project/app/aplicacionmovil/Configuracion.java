package com.project.app.aplicacionmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Configuracion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String user;
    private NavigationView navigationView;
    private ImageView abrirMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_config);

        Intent intent = getIntent();
        this.user = intent.getStringExtra("User");

        navigationView = (NavigationView)findViewById(R.id.nav_config);
        navigationView.setItemIconTintList(null);
        abrirMenu = (ImageView)findViewById(R.id.abrirMenuConfig);
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navConfig) {
            Toast.makeText(getApplicationContext(), "Ya te encuentras en Configuracion", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.navHome)
        {
            Intent intent = new Intent(this, MenuP.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        return true;
    }
}
