package com.example.car_ml_learning.mltools;



//Had Classe gha nkhadmo bih fach ghanbghiw nratbo dok les distances eucl, donc had claase kay associer lin dok les valeurs finales mea Origin bhal chi couplet
public class Couplet
{
  private OriginsEnum origin;
    private float euclDist;

    public OriginsEnum getOrigin() {
        return origin;
    }

    public float getEuclDist() {
        return euclDist;
    }

    public Couplet(OriginsEnum origin, float euclDit) {
        this.origin = origin;
        this.euclDist = euclDit;
    }

    @Override
    public String toString() {
        return "Couplet{" +
                "origin=" + origin +
                ", euclDit=" + euclDist +
                '}';
    }
}
