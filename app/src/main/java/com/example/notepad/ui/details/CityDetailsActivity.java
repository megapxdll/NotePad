package com.example.notepad.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.notepad.R;
import com.example.notepad.domain.City;

public class CityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        City city = getIntent().getParcelableExtra(CityDetailsFragment.ARG_CITY);

        getSupportFragmentManager().setFragmentFactory(new CityDetailsFragmentFactory(city));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_details);

        if (savedInstanceState == null) {


            Fragment cityDetailsFragment = new CityDetailsFragment(city);
            // cityDetailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_details_container, cityDetailsFragment)
                    .commit();
        }

//        CityDetailsFragment fragment =  (CityDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details_container);
//        fragment.doSomethingUseful();
//

    }

    static class CityDetailsFragmentFactory extends FragmentFactory {

        private final City city;

        public CityDetailsFragmentFactory(City city) {
            this.city = city;
        }

        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            if (className.equals(CityDetailsFragment.class.getName())) {
                return new CityDetailsFragment(city);
            }
            return super.instantiate(classLoader, className);
        }
    }
}
