package com.example.car_ml_learning;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.car_ml_learning.mltools.BayesOperations;
import com.example.car_ml_learning.mltools.DataEntry;
import com.example.car_ml_learning.mltools.KNNOperations;
import com.example.car_ml_learning.mltools.OriginsEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AffichageActivity extends AppCompatActivity {
    TextView t1;
    int defaultValueForMpg = 0;
    int defaultValueForDisplacement =  0;
    int defaultValueForAcceleration=0;
    int defaultValueForWeight=0;
    int defaultValueForHorspower=0;
    int defaultValueForK=0;
    int defaultValueForPrecision=0;
    int defaultValueForRecall=0;
    int defaultValueForFscore=0;
    int defaultValueForAccurate=0;
    int mpg,displacement,acceleration,weight,horsepower,K,precision,recall,fscore,accurate;
    String selectedOption;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        t1= (TextView) findViewById(R.id.textView1);

        Intent i = getIntent();
        mpg=Integer.parseInt(String.valueOf(i.getIntExtra("mpg",defaultValueForMpg)).trim());


        displacement= Integer.parseInt(String.valueOf(i.getIntExtra("displacement",defaultValueForDisplacement)).trim());
        acceleration=Integer.parseInt( String.valueOf(i.getIntExtra("acceleration",defaultValueForAcceleration)).trim());
        weight= Integer.parseInt(String.valueOf(i.getIntExtra("weight",defaultValueForWeight)).trim());
        horsepower= Integer.parseInt(String.valueOf(i.getIntExtra("horsepower",defaultValueForHorspower)).trim());
        selectedOption=i.getStringExtra("selectedOption");
     //  K=Integer.parseInt(i.getStringExtra("tKN").trim());
        precision=Integer.parseInt(String.valueOf(i.getIntExtra("precision",defaultValueForPrecision)).trim());
        recall=Integer.parseInt(String.valueOf(i.getIntExtra("recall",defaultValueForRecall)).trim());
        fscore=Integer.parseInt(String.valueOf(i.getIntExtra("fscore",defaultValueForFscore)));accurate=Integer.parseInt(String.valueOf(i.getIntExtra("accurate",defaultValueForAccurate)).trim());

        DataEntry entry = new DataEntry(mpg, displacement, horsepower, weight, acceleration);
        List<DataEntry> dataEntries = new ArrayList<>();

        dataEntries.add(new DataEntry(35, 72, 69, 1613, 18, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(31, 76, 52, 1649, 17, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(39, 79, 58, 1755, 17, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(35, 81, 60, 1760, 16, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(31, 71, 65, 1773, 19, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(33, 91, 53, 1795, 18, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(33, 91, 53, 1795, 17, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(36, 98, 66, 1800, 14, OriginsEnum.AMERICAN));
        dataEntries.add(new DataEntry(36, 91, 60, 1800, 16, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(30, 97, 71, 1825, 12, OriginsEnum.EUROPEAN));
        dataEntries.add(new DataEntry(36, 79, 58, 1825, 19, OriginsEnum.EUROPEAN));
        dataEntries.add(new DataEntry(27, 97, 60, 1834, 19, OriginsEnum.EUROPEAN));
        dataEntries.add(new DataEntry(26, 97, 46, 1835, 21, OriginsEnum.EUROPEAN));
        dataEntries.add(new DataEntry(32, 71, 65, 1836, 21, OriginsEnum.JAPANESE));
        dataEntries.add(new DataEntry(30, 80, 62, 1845, 15, OriginsEnum.EUROPEAN));


        OriginsEnum finalResult = KNNOperations.predictEntry(entry,1, dataEntries);
       t1.setText("The Prediction is : " +finalResult);
    }

}