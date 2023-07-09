package com.example.mvctomvp_10119059;

public class MainPresenter {
    private MainView view;
    private Meter model;

    public MainPresenter(MainView view) {
        this.view = view;
        this.model = Meter.getInstance();
    }

    public void onMeterValueChanged(String meterValue) {
        double parsedMeter = 0;
        if (!meterValue.isEmpty()) {
            parsedMeter = Double.parseDouble(meterValue);
        }
        model.setMeter(parsedMeter);
    }

    public void onDestroy() {
        model.destroy();
    }
}

