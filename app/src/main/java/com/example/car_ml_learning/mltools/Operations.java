package com.example.car_ml_learning.mltools;

import  com.example.car_ml_learning.mltools.*;

import java.util.List;

public class Operations {


    //Hadi kat3tik occurences dyal chi origin fi List<DataEntry>
    public static int calcSumOrigin(OriginsEnum origin, List<DataEntry> dataEntries) {
        int sum = 0;
        for (DataEntry entry : dataEntries) {
            if (entry.getOrign() == origin) {
                sum++;
            }
        }
        return sum;
    }


    //Hadi kat3tiha attribute o List dyal dataEntries li anda fi excel o katra3 lik moyenne dyalo
    public static float calcAverage(AttributesEnum attribute, List<DataEntry> dataEntries) {
        float average = 0;
        for (DataEntry entry : dataEntries) {
            average += entry.enumToAttribute(attribute);
        }
        average = average / dataEntries.size();
        return average;
    }



    //hadi kat7ssab dik parite fi formule li hiya, ex : POWER(A2,A$17$) gha khadmo biha fi calcEuclideanDistance
    private   static float calcCartesianCoord(DataEntry dataEntryInList, DataEntry dataEntry, AttributesEnum attribute )

    {
        float attributeEntry = dataEntry.enumToAttribute(attribute);
        return (float) Math.pow(dataEntryInList.enumToAttribute(attribute) - attributeEntry, 2);

    }

    //Hadi kat3ina euclidean distance dyal chaque ligne
    public  static float calcEuclideanDistance(DataEntry dataEntryInList, DataEntry dataEntry)
    {
        return (float) Math.sqrt(calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.MPG) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.DISPLACEMENT) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.HORSEPOWER) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.WEIGHT) + calcCartesianCoord(dataEntryInList,dataEntry, AttributesEnum.ACCELERATION));
    }


    //Had methode bach nakhodo gher list til 7ad rank li bgahyna dyal K min List<Couplet>
    public static List<Couplet> trimList(List<Couplet> list, int rank) {
        if (rank >= 0 && rank <= list.size()) {
            return list.subList(0, rank);
        } else {
            throw new IllegalArgumentException("Range Ivalide");
        }
    }

    public static  int stringToInt(String value)
    {
        return Integer.parseInt(value);

    }



}
