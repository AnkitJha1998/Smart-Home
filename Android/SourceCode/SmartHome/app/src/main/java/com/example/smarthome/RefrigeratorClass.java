package com.example.smarthome;

public class RefrigeratorClass {

    private int onOffSwitch;
    private double eggDistance;
    private double vegRotMeter;
    private double milkMeter;

    public int getOnOffSwitch() {
        return onOffSwitch;
    }

    public double getEggDistance() {
        return eggDistance;
    }

    public double getVegRotMeter() {
        return vegRotMeter;
    }

    public double getMilkMeter() {
        return milkMeter;
    }


    public void setOnOffSwitch(int onOffSwitch) {
        this.onOffSwitch = onOffSwitch;
    }

    public void setEggDistance(double eggDistance) {
        this.eggDistance = eggDistance;
    }

    public void setVegRotMeter(double vegRotMeter) {
        this.vegRotMeter = vegRotMeter;
    }

    public void setMilkMeter(double milkMeter) {
        this.milkMeter = milkMeter;
    }
}
