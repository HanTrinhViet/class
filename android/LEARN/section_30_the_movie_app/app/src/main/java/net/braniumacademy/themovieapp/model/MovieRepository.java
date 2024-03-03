package net.braniumacademy.themovieapp.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import net.braniumacademy.themovieapp.R;
import net.braniumacademy.themovieapp.serviceapi.MovieApiService;
import net.braniumacademy.themovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getService();
        Call<Result> call = movieApiService.getPopularMovie(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result != null && result.getResults() != null) {
                    movies = result.getResults();
                    Log.d("HAHAHA", Arrays.toString(movies.toArray()));
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
