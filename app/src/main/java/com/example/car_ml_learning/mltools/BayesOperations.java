package com.example.car_ml_learning.mltools;

import static com.example.car_ml_learning.mltools.Operations.calcAverage;
import static com.example.car_ml_learning.mltools.Operations.calcSumOrigin;

import java.util.List;



public class BayesOperations {



    //Wach A ola B, comparant mea moyenne
    private static boolean classifyAttribute(float value, float avg) {
        if (value >= avg) {
            return true;
        } else {
            return false;
        }
    }


    public static float calcClassProb(OriginsEnum origin, List<DataEntry> dataEntries) {

        return calcSumOrigin(origin, dataEntries) / (float) dataEntries.size();
    }

    //Hadi katcalculer prob condit dyal attribute dyal sujet li baghin ndero lih predicition
    public static float calcProbCondit(AttributesEnum attribute, OriginsEnum origin, boolean classification, List<DataEntry> dataEntries) {
        float avg = calcAverage(attribute, dataEntries);


        if (classification) {
            int occurences = 0;
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.getOrign() == origin && classifyAttribute(dataEntry.enumToAttribute(attribute), avg)) {
                    occurences++;
                }
            }
            return occurences / (float) calcSumOrigin(origin, dataEntries);
        } else {
            int occurences = 0;
            for (DataEntry dataEntry : dataEntries) {
                if (dataEntry.getOrign() == origin && dataEntry.enumToAttribute(attribute) < avg) {
                    occurences++;
                }
            }
            return occurences / (float) calcSumOrigin(origin, dataEntries);
        }


    }


    //Hadi kat7ssab lina prob dyal dak sujet li andna fi kol attribute o katraj3i produit; hiya matalan (3/5 * 2/5 * 1/4 * 2/9)
    public static float calcOrginProbabilityOfEntry(OriginsEnum origin, DataEntry dataEntry, List<DataEntry> dataEntries) {
        float pMpj = calcProbCondit(AttributesEnum.MPG, origin, classifyAttribute(dataEntry.getMpg(), calcAverage(AttributesEnum.MPG, dataEntries)), dataEntries);
        float pDisplacement = calcProbCondit(AttributesEnum.DISPLACEMENT, origin, classifyAttribute(dataEntry.getDisplacement(), calcAverage(AttributesEnum.DISPLACEMENT, dataEntries)), dataEntries);
        float pHorsepower = calcProbCondit(AttributesEnum.HORSEPOWER, origin, classifyAttribute(dataEntry.getHorsepower(), calcAverage(AttributesEnum.HORSEPOWER, dataEntries)), dataEntries);
        float pWeight = calcProbCondit(AttributesEnum.WEIGHT, origin, classifyAttribute(dataEntry.getWeight(), calcAverage(AttributesEnum.WEIGHT, dataEntries)), dataEntries);
        float pAcceleration = calcProbCondit(AttributesEnum.ACCELERATION, origin, classifyAttribute(dataEntry.getAcceleration(), calcAverage(AttributesEnum.ACCELERATION, dataEntries)), dataEntries);

        return pMpj * pDisplacement * pHorsepower * pWeight * pAcceleration;
    }


    //Hadi li kat3ti final result
    public static OriginsEnum predictEntry(DataEntry dataEntry, List<DataEntry> dataEntries) {

        //hado kan 7assbo pour chaique origine, mmatal 3/5 * 2/5 * 1/4 * 2/9 (hadi ra khdamna bi mehtode li 9bal)  * Prob de classe dyal attribute
        float pJapanese = calcOrginProbabilityOfEntry(OriginsEnum.JAPANESE, dataEntry, dataEntries) * calcClassProb(OriginsEnum.JAPANESE, dataEntries);
        float pAmerican = calcOrginProbabilityOfEntry(OriginsEnum.AMERICAN, dataEntry, dataEntries) * calcClassProb(OriginsEnum.AMERICAN, dataEntries);
        float pEuropean = calcOrginProbabilityOfEntry(OriginsEnum.EUROPEAN, dataEntry, dataEntries) * calcClassProb(OriginsEnum.EUROPEAN, dataEntries);
        float biggestProbability = Math.max(pJapanese, Math.max(pAmerican, pEuropean));
        OriginsEnum finalPrediction = (biggestProbability == pJapanese) ? OriginsEnum.JAPANESE : (biggestProbability == pAmerican) ? OriginsEnum.AMERICAN : OriginsEnum.EUROPEAN;
        return finalPrediction;
    }
}
