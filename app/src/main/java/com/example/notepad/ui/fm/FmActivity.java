package com.example.notepad.ui.fm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.notepad.R;
import com.example.notepad.ui.OnBackPressed;

public class FmActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment instanceof OnBackPressed) {
            boolean result = ((OnBackPressed) fragment).onBackPressed();

            if (!result) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        findViewById(R.id.replace_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FirstFragment(), FirstFragment.TAG)
                        .commit();
            }
        });


        findViewById(R.id.replace_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SecondFragment())
                        .commit();

            }
        });

        findViewById(R.id.replace_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ThirdFragment())
                        .commit();

            }
        });


        findViewById(R.id.add_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragment_container, new FirstFragment(), FirstFragment.TAG)
                                .commitAllowingStateLoss();
                    }
                }, 3000L);

            }
        });


        findViewById(R.id.add_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new SecondFragment(), "SecondFragment")
                        .commit();
            }
        });

        findViewById(R.id.add_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new ThirdFragment(), "ThirdFragment")
                        .commit();
            }
        });

        findViewById(R.id.add_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new FourthFragment(), "FourthFragment")
                        .commit();
            }
        });

        findViewById(R.id.remove_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag(FirstFragment.TAG);

                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        });


        findViewById(R.id.remove_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag("SecondFragment");

                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        });

        findViewById(R.id.remove_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("ThirdFragment");

                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        });

        findViewById(R.id.remove_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("FourthFragment");

                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        });

        //show


        findViewById(R.id.show_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag(FirstFragment.TAG);

                getSupportFragmentManager().beginTransaction()
                        .show(fragment)
                        .commit();
            }
        });


        findViewById(R.id.show_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag("SecondFragment");

                getSupportFragmentManager().beginTransaction()
                        .show(fragment)
                        .commit();
            }
        });

        findViewById(R.id.show_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("ThirdFragment");

                getSupportFragmentManager().beginTransaction()
                        .show(fragment)
                        .commit();
            }
        });

        findViewById(R.id.show_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("FourthFragment");

                getSupportFragmentManager().beginTransaction()
                        .show(fragment)
                        .commit();
            }
        });

        //hide

        findViewById(R.id.hide_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag(FirstFragment.TAG);

                getSupportFragmentManager().beginTransaction()
                        .hide(fragment)
                        .commit();
            }
        });


        findViewById(R.id.hide_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getSupportFragmentManager().findFragmentByTag("SecondFragment");

                getSupportFragmentManager().beginTransaction()
                        .hide(fragment)
                        .commit();
            }
        });

        findViewById(R.id.hide_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("ThirdFragment");

                getSupportFragmentManager().beginTransaction()
                        .hide(fragment)
                        .commit();
            }
        });

        findViewById(R.id.hide_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("FourthFragment");

                getSupportFragmentManager().beginTransaction()
                        .hide(fragment)
                        .commit();
            }
        });


        findViewById(R.id.multiple_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new FirstFragment())
                        .add(R.id.fragment_container, new SecondFragment(), "Second")
                        .add(R.id.fragment_container, new ThirdFragment())
                        .add(R.id.fragment_container, new FourthFragment())
//                        .addToBackStack(null)
                        .commitNow();

                SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag("Second");
                secondFragment.doSomethingUseful();
            }
        });

        findViewById(R.id.add_transaction_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new FirstFragment(), FirstFragment.TAG)
                        .addToBackStack("First")
                        .commit();
            }
        });


        findViewById(R.id.add_transaction_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new SecondFragment(), "SecondFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        findViewById(R.id.add_transaction_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new ThirdFragment(), "ThirdFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        findViewById(R.id.add_transaction_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new FourthFragment(), "FourthFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        findViewById(R.id.go_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack("First", 0);
            }
        });

    }

}
