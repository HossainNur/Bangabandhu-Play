package com.example.bangabandhuplay.ui.movies;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.bangabandhuplay.data.model.category.root.single.Data;
import com.example.bangabandhuplay.data.model.sliders.Original;
import com.example.bangabandhuplay.data.repository.MoviesRepository;
import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    private MoviesRepository moviesRepository;
    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new MoviesRepository(application);
    }

    public MutableLiveData<List<Original>> getSliders(){
        return moviesRepository.fetchSlider();
    }

    public MutableLiveData<List<Data>> fetchMoviesCategory(String slug){
        return moviesRepository.fetchMoviesCategory(slug);
    }
}
