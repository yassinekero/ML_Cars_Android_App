package com.example.car_ml_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    EditText precision,recall,f_score,accurate;
    private Button predict;
    private Button clear;
    private final String defaut = "Predict";
    int defaultValueForMpg = 0;
    int defaultValueForDisplacement =  0;
    int defaultValueForAcceleration=0;
    int defaultValueForWeight=0;
    int defaultValueForHorespower=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        predict=findViewById(R.id.submitpr);
        clear=findViewById(R.id.clearpr);
        precision=findViewById(R.id.pr);
        recall=findViewById(R.id.rec);
        f_score=findViewById(R.id.fs);
        accurate=findViewById(R.id.accu);
        clear.setOnClickListener(clearListner);
        predict.setOnClickListener(predictListener);
    }
    private View.OnClickListener clearListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            precision.getText().clear();
            recall.getText().clear();
            f_score.getText().clear();
            accurate.getText().clear();
            predict.setText(defaut);
        }
    };
    private View.OnClickListener predictListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String pr = precision.getText().toString();
            //int tPr = Integer.parseInt(pr);
            String rec = recall.getText().toString();
            //int tRec=Integer.parseInt(rec);
            String fS =f_score.getText().toString();
            //int tFs = Integer.parseInt(fS);
            String aC =accurate.getText().toString();
            //int tA= Integer.parseInt(aC);
            if (pr.isEmpty() || rec.isEmpty() || aC.isEmpty() || fS.isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else{
                int tPr = Integer.parseInt(pr);
                int tRec=Integer.parseInt(rec);
                int tFs = Integer.parseInt(fS);
                int tA= Integer.parseInt(aC);
                if(tPr <= 0 || tRec <=0 || tFs <=0 || tA <=0  ){
                    Toast.makeText(ThirdActivity.this, "Invalid number", Toast.LENGTH_SHORT).show();
                }else{
                Intent intent = new Intent(ThirdActivity.this, AffichageActivity.class);
                intent.putExtra("precision", tPr);
                intent.putExtra("recall", tRec);
                intent.putExtra("fscore", tFs);
                intent.putExtra("accurate", tA);
                intent.putExtra("mpg", getIntent().getIntExtra("mpg", defaultValueForMpg));
                intent.putExtra("displacement", getIntent().getIntExtra("displacement",defaultValueForDisplacement));
                intent.putExtra("acceleration", getIntent().getIntExtra("acceleration",defaultValueForAcceleration));
                intent.putExtra("weight", getIntent().getIntExtra("weight",defaultValueForWeight));
                intent.putExtra("horsepower", getIntent().getIntExtra("horsepower",defaultValueForHorespower));
                intent.putExtra("selectedOption",getIntent().getStringExtra("selectedOption"));
                intent.putExtra("tKN", getIntent().getStringExtra("tKN"));
                startActivity(intent);
            }}


        }

    };

}