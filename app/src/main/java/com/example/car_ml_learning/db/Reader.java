package com.example.car_ml_learning.db;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.car_ml_learning.mltools.DataEntry;
import com.example.car_ml_learning.mltools.OriginsEnum;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {


    public static List<DataEntry> getDBData(Context context, String fileName) throws IOException {
        List<DataEntry> entries = new ArrayList<>();

        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String data[] = line.split(",");
                List<Integer> dataInt = new ArrayList<Integer>();
                for (int i = 0; i < data.length; i++) {
                    if (i == 5) {
                        dataInt.add(OriginsEnum.valueOf(data[i].toUpperCase().trim()).ordinal());
                    } else {
                        dataInt.add(Integer.parseInt(data[i]));
                    }
                }
                entries.add(new DataEntry(dataInt.get(0), dataInt.get(1), dataInt.get(2), dataInt.get(3), dataInt.get(4), OriginsEnum.values()[dataInt.get(5)]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

}
