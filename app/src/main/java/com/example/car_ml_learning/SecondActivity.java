package com.example.car_ml_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText k;
    private Button submit;
    private Button clear;
    int defaultValueForMpg = 0;
    int defaultValueForDisplacement =  0;
    int defaultValueForAcceleration=0;
    int defaultValueForWeight=0;
    int defaultValueForHorsepower=0;
    private final String defaut = "ML Algorithm ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        radioGroup = findViewById(R.id.radioGroup);
        k = findViewById(R.id.k);
        submit = findViewById(R.id.submitML);
        clear=findViewById(R.id.clearML);
        clear.setOnClickListener(clearListner);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && checkedId == R.id.knn) {
                    k.setVisibility(View.VISIBLE);
                } else {
                    k.setVisibility(View.GONE);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kN = "";

                if (k.getVisibility() == View.VISIBLE && isKnnSelected()) {
                    kN = k.getText().toString();
                    int tKN = Integer.parseInt(kN);
                }


                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String selectedOption = radioButton.getText().toString();

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("mpg", getIntent().getIntExtra("mpg", defaultValueForMpg));
                intent.putExtra("displacement", getIntent().getIntExtra("displacement",defaultValueForDisplacement));
                intent.putExtra("acceleration", getIntent().getIntExtra("acceleration",defaultValueForAcceleration));
                intent.putExtra("weight", getIntent().getIntExtra("weight",defaultValueForWeight));
                intent.putExtra("horsepower", getIntent().getIntExtra("horsepower",defaultValueForHorsepower));
                intent.putExtra("selectedOption", selectedOption);
                intent.putExtra("tKN", kN);
                startActivity(intent);
            }
        });

    }

    private boolean isKnnSelected() {
        return radioGroup.getCheckedRadioButtonId() == R.id.knn;
    }
    private View.OnClickListener clearListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            k.getText().clear();
            submit.setText(defaut);
        }
    };
}