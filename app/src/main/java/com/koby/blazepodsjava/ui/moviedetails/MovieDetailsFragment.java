package com.koby.blazepodsjava.ui.moviedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.koby.blazepodsjava.R;
import com.koby.blazepodsjava.model.Item;
import com.koby.blazepodsjava.ui.adapters.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsFragment extends Fragment {
    private ArrayList<Item> dataSet = new ArrayList<>();
    private CustomAdapter adapter = new CustomAdapter(dataSet);
    public MovieDetailsFragment() {
        super(R.layout.fragment_movie_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMembers();
//        setUpViews(view);
        fetchDoggoImages();
    }


    private RecyclerView discoverRecyclerView;
    private MovieDetailsViewModel movieDetailsViewModel;

//    private ProgressBar var discoverProgressbar:


    private void fetchDoggoImages() {
//        movieDetailsViewModel.discoverMovieList
//            .distinctUntilChanged()
//            .observe(viewLifecycleOwner, {
//                discoverProgressbar.visibility = View.GONE
//                adapter.submitData(lifecycle, it)
//            })
    }

    private void initMembers() {
        movieDetailsViewModel = new ViewModelProvider(getActivity()).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.movieList.observe(getViewLifecycleOwner(), o -> {
            dataSet.clear();
            dataSet.addAll(o);
        });
//        adapter = MoviePagingAdapter {
//            findNavController().navigate(
//                DiscoverFragmentDirections
//                    .actionDiscoverFragmentToDetailsFragment(
//                        Integer.parseInt(it.id)
//                    )
//            )
//        }
    }

    public void setUpViews(View view) {
        discoverRecyclerView = view.findViewById(R.id.rvDoggoRoom);
        discoverRecyclerView.setAdapter(adapter);

    }
}
