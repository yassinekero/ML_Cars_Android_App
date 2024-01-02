package com.example.car_ml_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String defaut = "ML Algorithm ";

    private Button submit;
    private Button clear;
    private EditText mpg;
    private EditText displacement;
    private EditText acceleration;
    private EditText weight;
    private EditText horsepower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation des éléments de l'interface
        submit = findViewById(R.id.submit);
        clear=findViewById(R.id.clear);
        mpg=findViewById(R.id.mpg);
        displacement=findViewById(R.id.disp);
        acceleration=findViewById(R.id.acc);
        weight=findViewById(R.id.weight);
        horsepower=findViewById(R.id.hp);

        // Ajout des listeners aux éléments
        clear.setOnClickListener(clearListner);
        submit.setOnClickListener(submitListener);
    }
    private View.OnClickListener clearListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mpg.getText().clear();
            displacement.getText().clear();
            acceleration.getText().clear();
            acceleration.getText().clear();
            weight.getText().clear();
            horsepower.getText().clear();
            submit.setText(defaut);
            Log.d("y: ", mpg.toString());
        }
    };
    private View.OnClickListener submitListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String mp = mpg.getText().toString();
            String disp = displacement.getText().toString();
            String acc = acceleration.getText().toString();
            String w = weight.getText().toString();
            String h = horsepower.getText().toString();

            if (mp.isEmpty() || disp.isEmpty() || acc.isEmpty() || w.isEmpty() || h.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                int tMp = Integer.parseInt(mp);
                int tDisp = Integer.parseInt(disp);
                int tAc = Integer.parseInt(acc);
                int tW = Integer.parseInt(w);
                int tH = Integer.parseInt(h);

                if (tMp <= 0 || tDisp <= 0 || tAc <= 0 || tW <= 0 || tH <= 0) {
                    Toast.makeText(MainActivity.this, "Invalid number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("mpg", tMp);
                    intent.putExtra("displacement", tDisp);
                    intent.putExtra("horsepower", tH);
                    intent.putExtra("weight", tW);
                    intent.putExtra("acceleration", tAc);
                    startActivity(intent);
                }
            }
        }


    };
}