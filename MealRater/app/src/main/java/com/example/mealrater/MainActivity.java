package com.example.mealrater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button rateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateButton = findViewById(R.id.rateButton);

        // Triggers displayFragment method upon button click
        rateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                displayFragment();
            }
        });
    }

    // Method to display fragment
    public void displayFragment(){
        RatingFragment ratingFragment = RatingFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragment_container,ratingFragment).addToBackStack(null).commit();
    }

    // Method to close fragment and change TextView rating value
    public void closeFragment(float stars){
        // Changing textview rating
        TextView ratingDisplay = findViewById(R.id.ratingDisplay);
        ratingDisplay.setText(String.valueOf(stars));
        ratingDisplay.setVisibility(View.VISIBLE);

        // Closing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        RatingFragment ratingFragment = (RatingFragment) fragmentManager.findFragmentById(R.id.fragment_container);

        if(ratingFragment!= null){
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(ratingFragment).commit();
        }
    }

}