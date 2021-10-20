package com.example.notepad.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.notepad.R;
import com.example.notepad.domain.City;
import com.example.notepad.ui.details.CityDetailsActivity;
import com.example.notepad.ui.details.CityDetailsFragment;
import com.example.notepad.ui.fm.FmActivity;
import com.example.notepad.ui.list.CitiesListFragment;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_CITY = "ARG_CITY";

    private City selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fm_options).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FmActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (!(fragmentManager.findFragmentById(R.id.fragment_container) instanceof CitiesListFragment)) {
            fragmentManager.popBackStack();
        }

        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_CITY)) {
            selectedCity = savedInstanceState.getParcelable(ARG_CITY);

            if (isLandscape) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_CITY, selectedCity);

                fragmentManager.setFragmentResult(CityDetailsFragment.KEY_CITIES_LIST_DETAILS, bundle);
            } else {


                /*
                CityDetailsFragment detailsFragment = CityDetailsFragment.newInstance(selectedCity);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .addToBackStack(null)
                        .commit();*/
            }
        }

        getSupportFragmentManager().setFragmentResultListener(CitiesListFragment.KEY_CITIES_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedCity = result.getParcelable(CitiesListFragment.ARG_CITY);

                if (isLandscape) {

                    fragmentManager.setFragmentResult(CityDetailsFragment.KEY_CITIES_LIST_DETAILS, result);
                } else {

                    Intent intent = new Intent(MainActivity.this, CityDetailsActivity.class);
                    intent.putExtra(CityDetailsFragment.ARG_CITY, selectedCity);

                    startActivity(intent);

//                    CityDetailsFragment detailsFragment = CityDetailsFragment.newInstance(selectedCity);
//
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, detailsFragment)
//                            .addToBackStack(null)
//                            .commit();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedCity != null) {
            outState.putParcelable(ARG_CITY, selectedCity);
        }
        super.onSaveInstanceState(outState);
    }

    /*
    @Override
    public void onCityClicked(City city) {
        CityDetailsFragment detailsFragment = CityDetailsFragment.newInstance(city);
        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);
        if (isLandscape) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_details_container, detailsFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }*/
}
