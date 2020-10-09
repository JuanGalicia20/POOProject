package com.project.app.aplicacionmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Horarios extends AppCompatActivity {
    private CardView anadir;
    private Button newButton;
    private CardView formulario;
    private ImageView close;
    private ImageButton crearHorario;
    private RadioGroup opcion;
    private RadioButton seleccionado;
    private EditText nombreHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        nombreHorario = (EditText)findViewById(R.id.txtViewNHorario);
        opcion = (RadioGroup) findViewById(R.id.radioGroup);
        formulario = (CardView) findViewById(R.id.nuevohorario);
        formulario.setVisibility(View.GONE);
        anadir = (CardView) findViewById(R.id.anadir);
        this.anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulario.setVisibility(View.VISIBLE);
            }
        });

        close = (ImageView) findViewById(R.id.imageClose);
        this.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                formulario.setVisibility(View.GONE);
            }
        });


        crearHorario = (ImageButton)findViewById(R.id.imageButtonCrearHorario);
        crearHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                addHorario();
                formulario.setVisibility(View.GONE);
                nombreHorario.setText("");
            }
        });
    }

    public void addHorario()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.rootlayout);
        newButton = new Button(this);
        String nombre = nombreHorario.getText().toString();
        newButton.setTag(nombreHorario.getText().toString());
        newButton.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        newButton.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        newButton.setBackgroundResource(R.drawable.horarioback);
        newButton.setTextSize(20);
        /*
        <Button
                android:id="@+id/button1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="16dp"
                android:layout_marginRight="16dp"
                android:layout_marginBottom="16dp"
                android:background="@drawable/horarioback"
                android:fontFamily="@font/amaranth"
                android:padding="10dp"
                android:text="Buttons"
                android:textColor="@android:color/secondary_text_light"
                android:textSize="25dp" />
         */
        newButton.setText(nombreHorario.getText().toString());
        layout.addView(newButton);
        Toast.makeText(this,"Nuevo horario Creado exitosamente",Toast.LENGTH_SHORT).show();
    }

    public void checkButton(View v)
    {
        int radioId = opcion.getCheckedRadioButtonId();
        seleccionado = (RadioButton)findViewById(radioId);
        Toast.makeText(this,"Selected: "+seleccionado.getText(),Toast.LENGTH_SHORT).show();
    }

    public void openHorarioSeleccionado(String id) {
        Intent intent = new Intent(this, HorarioSeleccionado.class);
        startActivity(intent);
    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}