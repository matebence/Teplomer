package com.example.ecneb.teplomer.Activities.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ecneb.teplomer.HttpRequests.HttpRequest;
import com.example.ecneb.teplomer.HttpRequests.Request;
import com.example.ecneb.teplomer.Models.Data;
import com.example.ecneb.teplomer.Models.Decrease;
import com.example.ecneb.teplomer.Models.Increase;
import com.example.ecneb.teplomer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperatureActivityPresenter {

    private static final String TAG = TemperatureActivityPresenter.class.getClass().getSimpleName();

    private View view;
    private Context context;
    private Temperature temperature;

    public TemperatureActivityPresenter(View view, Context context){
        this.temperature = new Temperature();
        this.context = context;
        this.view = view;
    }

    public void fetchTemperatureData(){
        view.showProgressBar();
        view.disableButtons(false);

        Request request = HttpRequest.getClient().create(Request.class);
        Call<Data> call = request.getTemperature();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data>call, @NonNull Response<Data> response) {
                if(response.body() != null){
                    if(!(Double.isNaN(response.body().getTemperature())) && !(Double.isNaN(response.body().getHumidity()))){
                        temperature.setTemperature(response.body().getTemperature());
                        temperature.setHumidity(response.body().getHumidity());
                    }
                }

                view.updateTemperatureData(temperature);

                view.hideProgressBar();
                view.enableButtons(true);

                Log.d(TAG, context.getString(R.string.data_boli_ziskane));
            }

            @Override
            public void onFailure(@NonNull Call<Data>call, @NonNull Throwable t) {
                view.showSnackbar(context.getString(R.string.server_je_nedostupny));
                view.hideProgressBar();
                view.enableButtons(true);

                Log.e(TAG, t.toString());
            }
        });
    }

    public void increaseTemeprature(){
        view.showProgressBar();
        view.disableButtons(false);

        Request request = HttpRequest.getClient().create(Request.class);
        Call<Increase> call = request.increaeTemperature();

        call.enqueue(new Callback<Increase>() {
            @Override
            public void onResponse(@NonNull Call<Increase>call, @NonNull Response<Increase> response) {
                if(response.body() != null){
                    if(response.body().getIncreased() != 0){
                        view.showSnackbar(context.getString(R.string.teplota_zvysena)+ " " + response.body().getIncreased()+context.getString(R.string.celsius));
                    }
                }

                view.hideProgressBar();
                view.enableButtons(true);

                Log.d(TAG, context.getString(R.string.data_boli_spracovane));
            }

            @Override
            public void onFailure(@NonNull Call<Increase>call, @NonNull Throwable t) {
                view.showSnackbar(context.getString(R.string.server_je_nedostupny));
                view.hideProgressBar();
                view.enableButtons(true);

                Log.e(TAG, t.toString());
            }
        });
    }

    public void decreaseTemeprature(){
        view.showProgressBar();
        view.disableButtons(false);

        Request request = HttpRequest.getClient().create(Request.class);
        Call<Decrease> call = request.decreaseTemperature();

        call.enqueue(new Callback<Decrease>() {
            @Override
            public void onResponse(@NonNull Call<Decrease>call, @NonNull Response<Decrease> response) {
                if(response.body() != null){
                    if(response.body().getDecreased() != 0){
                        view.showSnackbar(context.getString(R.string.teplolota_znizena)+ " " + response.body().getDecreased()+context.getString(R.string.celsius));
                    }
                }

                view.hideProgressBar();
                view.enableButtons(true);

                Log.d(TAG, context.getString(R.string.data_boli_spracovane));
            }

            @Override
            public void onFailure(@NonNull Call<Decrease>call, @NonNull Throwable t) {
                view.showSnackbar(context.getString(R.string.server_je_nedostupny));
                view.hideProgressBar();
                view.enableButtons(true);

                Log.e(TAG, t.toString());
            }
        });
    }

    public interface View{

        void updateTemperatureData(Temperature temperature);

        void showSnackbar(String msg);

        void disableButtons(boolean disabled);
        void enableButtons(boolean enabled);

        void showProgressBar();
        void hideProgressBar();
    }
}