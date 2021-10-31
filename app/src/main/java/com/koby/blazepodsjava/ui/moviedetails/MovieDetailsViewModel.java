package com.koby.blazepodsjava.ui.moviedetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koby.blazepodsjava.model.Item;
import com.koby.blazepodsjava.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MovieDetailsViewModel extends ViewModel {
    private MutableLiveData<List<Item>> _movieList = new MutableLiveData();
    public LiveData<List<Item>> movieList = _movieList;

    private Repository repository;

    @Inject
    public MovieDetailsViewModel(Repository repository) {
        this.repository = repository;
        repository.callInitMovieList();
        _movieList.setValue(repository.getAllMovieList().getValue());
    }
}
