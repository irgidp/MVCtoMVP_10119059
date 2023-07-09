package com.example.mvctomvp_10119059;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity implements MainView {

    private EditText meter;
    private EditText kilometer;
    private EditText centimeter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        initView();
        observeModel();
        setupTextChangeListeners();
    }

    private void initView() {
        meter = findViewById(R.id.meter);
        kilometer = findViewById(R.id.kilometer);
        centimeter = findViewById(R.id.centimeter);
    }

    private void setupTextChangeListeners() {
        meter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onMeterValueChanged(meter.getText().toString());
            }
        });
    }

    private void observeModel() {
        Meter meterModel = Meter.getInstance();
        meterModel.getKilometer().observe(this, kilometer -> {
            this.kilometer.setText(kilometer);
        });

        meterModel.getCentimeter().observe(this, centimeter -> {
            this.centimeter.setText(centimeter);
        });

        // Set initial values
        this.kilometer.setText(meterModel.getKilometer().getValue());
        this.centimeter.setText(meterModel.getCentimeter().getValue());
    }


    @Override
    public void updateKilometer(String kilometerValue) {
        kilometer.setText(kilometerValue);
    }

    @Override
    public void updateCentimeter(String centimeterValue) {
        centimeter.setText(centimeterValue);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}


