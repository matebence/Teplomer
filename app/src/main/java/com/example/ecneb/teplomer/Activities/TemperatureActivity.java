package com.example.ecneb.teplomer.Activities;

import android.annotation.SuppressLint;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecneb.teplomer.Activities.Presenter.Temperature;
import com.example.ecneb.teplomer.Activities.Presenter.TemperatureActivityPresenter;
import com.example.ecneb.teplomer.R;

public class TemperatureActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, TemperatureActivityPresenter.View {

    private TemperatureActivityPresenter temperatureActivityPresenter;

    private RelativeLayout loading;
    private SwipeRefreshLayout refresh;

    private TextView temperatureValue;
    private TextView humidityValue;

    private Button decreaseButton;
    private Button increaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        init();
        this.temperatureActivityPresenter.fetchTemperatureData();
    }

    private void init() {
        this.temperatureActivityPresenter = new TemperatureActivityPresenter(this, getApplicationContext());

        this.loading = findViewById(R.id.loading);

        this.refresh = findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);

        this.temperatureValue = findViewById(R.id.temperature_value);
        this.humidityValue = findViewById(R.id.humidity_value);

        this.decreaseButton = findViewById(R.id.decrease_button);
        this.decreaseButton.setOnClickListener(this);
        this.increaseButton = findViewById(R.id.increase_button);
        this.increaseButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateTemperatureData(final Temperature temperature) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                temperatureValue.setText(Double.toString(temperature.getTemperature()));
                humidityValue.setText(Double.toString(temperature.getHumidity()));
            }
        });
    }

    @Override
    public void showSnackbar(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View rootView = findViewById(android.R.id.content);
                Snackbar snackbar = Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSnackbar));
                snackbar.show();
            }
        });

    }

    @Override
    public void disableButtons(final boolean disabled) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                decreaseButton.setEnabled(disabled);
                increaseButton.setEnabled(disabled);
            }
        });
    }

    @Override
    public void enableButtons(final boolean enabled) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                decreaseButton.setEnabled(enabled);
                increaseButton.setEnabled(enabled);
            }
        });
    }

    @Override
    public void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.decrease_button:
                temperatureActivityPresenter.decreaseTemeprature();
                break;
            case R.id.increase_button:
                temperatureActivityPresenter.increaseTemeprature();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(false);
        temperatureActivityPresenter.fetchTemperatureData();
    }
}