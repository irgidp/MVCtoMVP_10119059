package com.example.mvctomvp_10119059;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Meter {
    private static Meter instance;

    private double meter;

    private MutableLiveData<String> kilometer;
    private MutableLiveData<String> centimeter;

    private Meter() {
        this.meter = 0;
        this.kilometer = new MutableLiveData<>();
        this.centimeter = new MutableLiveData<>();
    }

    public static synchronized Meter getInstance() {
        if (instance == null) {
            instance = new Meter();
        }

        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public LiveData<String> getKilometer() {
        return kilometer;
    }

    public LiveData<String> getCentimeter() {
        return centimeter;
    }

    public void setMeter(double meter) {
        this.meter = meter;
        calculateLength();
    }

    private void calculateLength() {
        toKilometer();
        toCentimeter();
    }

    private void toKilometer() {
        double kilometerValue = meter / 1000;
        kilometer.postValue(String.valueOf(kilometerValue));
    }

    private void toCentimeter() {
        double centimeterValue = meter * 100;
        centimeter.postValue(String.valueOf(centimeterValue));
    }
}
