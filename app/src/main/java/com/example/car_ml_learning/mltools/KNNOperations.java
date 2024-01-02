package com.example.car_ml_learning.mltools;

import static com.example.car_ml_learning.mltools.Operations.calcEuclideanDistance;
import static com.example.car_ml_learning.mltools.Operations.trimList;
import  com.example.car_ml_learning.mltools.Couplet;



import java.util.*;



public class KNNOperations {



    //Hadi hiya li  katcreeya lina List<Couplet> bach n7oot kol ligne : Origin dyal + distanca eucl
    public static List<Couplet> createEuclidDistList(DataEntry dataEntry, List<DataEntry> dataEntries) {

        List<Couplet> euclDistList = new ArrayList<>();

        for (DataEntry entry : dataEntries) {

            euclDistList.add(new Couplet(entry.getOrign(), calcEuclideanDistance(entry,dataEntry)));

        }

        //had methde kattratb lina dik List<Couplet> min sgher li kbir , sort() ra fiha bhal arrow function hit Couplet fih Orgin o value o hna bghina ratbo ela value
        euclDistList.sort(Comparator.comparingDouble(Couplet::getEuclDist));

        return euclDistList;
    }



    //Hadi li kat3ti final result
    public static OriginsEnum predictEntry(DataEntry dataEntry ,int k , List<DataEntry> dataEntries)
    {
        List<Couplet> trimmedList = trimList(createEuclidDistList(dataEntry, dataEntries), k);
        int japCount = 0;
        int amerCount = 0;
        int europCount = 0;
        for(Couplet couplet : trimmedList)
        {
            if(couplet.getOrigin() == OriginsEnum.JAPANESE)
            {
              japCount++;
            }
            else if (couplet.getOrigin() == OriginsEnum.AMERICAN)
            {
               amerCount++;
            }
            else if (couplet.getOrigin() == OriginsEnum.EUROPEAN)
            {
             europCount++;
            }
        }
        int max = Math.max(japCount, Math.max(amerCount, europCount));

        OriginsEnum result = max == japCount ? OriginsEnum.JAPANESE : max == amerCount ? OriginsEnum.AMERICAN : OriginsEnum.EUROPEAN;
        return result;
    }


}
