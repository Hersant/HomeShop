package com.bse.garage;

public abstract class Vehicle {
    private String description;
    protected String modelName;
    private String manufacturer;
    private int speed;
    private int year;
    private String color;
    private int[] dimensions = new int[3];
    private int weight;

    public Vehicle(String description, String modelName, String manufacturer, int speed,
                   int year, String color, int[] dimensions, int weight) {
        this.description = description;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.speed = speed;
        this.year = year;
        this.color = color;
        this.dimensions = dimensions;
        this.weight = weight;
    }

    public abstract void start();

    public abstract void stop();

}
