package net.braniumacademy.themovieapp.serviceapi;

import net.braniumacademy.themovieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<Result> getPopularMovie(@Query(value = "api_key") String apiKey);
}
