package net.braniumacademy.themovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.braniumacademy.themovieapp.model.Movie;
import net.braniumacademy.themovieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = new MovieRepository(application);
    }

    // live data
    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData();
    }
}
