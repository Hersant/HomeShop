package com.bse.garage;

import java.util.ArrayList;
import java.util.List;

public class Car extends Vehicle {
    private int door;
    private int literPer100km;
    private String motor;
    private List<String> options = new ArrayList<String>();

    public Car(String description, String modelName, String manufacturer, int speed,
               int year, String color, int[] dimensions, int weight,
               int door, int literPer100km, String motor, List<String> options) {
        super(description, modelName, manufacturer, speed, year, color, dimensions, weight);
        this.door = door;
        this.literPer100km = literPer100km;
        this.motor = motor;
        this.options = options;
    }

    @Override
    public void start() {
        System.out.println("Je suis " + modelName + " je consomme " + literPer100km + "l au 100km et je démarre !" );
    }

    @Override
    public void stop() {
        System.out.println("Je suis " + modelName + " j'arrête mon moteur " + motor);
    }

    public void startHeadLight() {
        System.out.println("J'allume mes phares.");
    }

    public void openTrunk(){
        System.out.println("J'ouvre mon coffre.");
    }
}
