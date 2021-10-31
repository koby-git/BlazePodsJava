package com.koby.blazepodsjava.ui.movielist;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.koby.blazepodsjava.R;
import com.koby.blazepodsjava.model.Item;
import com.koby.blazepodsjava.ui.adapters.CustomAdapter;
import com.koby.blazepodsjava.ui.moviedetails.MovieDetailsViewModel;

import java.util.ArrayList;


public class MovieListFragment extends Fragment {

    private RecyclerView discoverRecyclerView;

    private ArrayList<Item> dataSet = new ArrayList<>();
    private CustomAdapter adapter = new CustomAdapter(dataSet);
    private ProgressBar discover_progressbar ;
    private MovieListViewModel movieListViewModel;

    public MovieListFragment() {
        super(R.layout.fragment_movie_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMembers();
        setUpViews(view);
    }

    private void initMembers() {

        movieListViewModel = new ViewModelProvider(getActivity()).get(MovieListViewModel.class);
        movieListViewModel.movieList.observe(getViewLifecycleOwner(), o -> {

            System.out.println("vvvvvvv");
            System.out.println(o);
            if (o!=null) {
                dataSet.clear();
                System.out.println("ddddddd");
                dataSet.addAll(o);
                adapter.notifyDataSetChanged();
                discover_progressbar.setVisibility(View.GONE);
            }});

    }

    public void setUpViews(View view) {
        discover_progressbar = view.findViewById(R.id.discover_progressbar);
                discoverRecyclerView = view.findViewById(R.id.rvDoggoRoom);
        discoverRecyclerView.setAdapter(adapter);
    }
}
