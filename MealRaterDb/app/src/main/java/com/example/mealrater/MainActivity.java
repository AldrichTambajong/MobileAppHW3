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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button rateButton;
    private Button saveButton;
    private EditText restaurantName;
    private EditText dishName;
    private TextView rating;
    private MealRaterDataSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateButton = findViewById(R.id.rateButton);
        saveButton = findViewById(R.id.saveButton);
        restaurantName = findViewById(R.id.restaurantInput);
        dishName = findViewById(R.id.dishInput);
        rating = findViewById(R.id.ratingDisplay);
        source = new MealRaterDataSource(this);

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

    public void save(View view){
        String restaurant = restaurantName.getText().toString();
        String dish = dishName.getText().toString();
        float rate = Float.parseFloat(rating.getText().toString());

        long id = MealRaterDataSource.insertData(restaurant,dish,rate);

        if(id > 0){
            Toast.makeText(this,"Data saved",Toast.LENGTH_LONG).show();
            restaurantName.setText("");
            dishName.setText("");
            rating.setText("");
        }
    }

}