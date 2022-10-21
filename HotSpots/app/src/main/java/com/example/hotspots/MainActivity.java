package com.example.hotspots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button rateButton;
    private Button saveButton;
    private EditText barName;
    private EditText barAddress;
    private TextView beer;
    private TextView wine;
    private TextView music;
    private TextView avgRating;

    FirebaseDatabase firebaseDb;
    DatabaseReference dbReference;
    locationInfo locInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beer = findViewById(R.id.beerDisplay);
        wine = findViewById(R.id.wineDisplay);
        music = findViewById(R.id.musicDisplay);
        avgRating = findViewById(R.id.avgRating);

        barName = findViewById(R.id.barName);
        barAddress = findViewById(R.id.barAddress);

        rateButton = findViewById(R.id.rateButton);
        saveButton = findViewById(R.id.saveButton);

        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference("Bar");

        locInfo = new locationInfo();

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayRatings();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = barName.getText().toString();
                String loc = barAddress.getText().toString();
                Float beerRate = Float.parseFloat(beer.getText().toString());
                Float wineRate = Float.parseFloat(wine.getText().toString());
                Float musicRate = Float.parseFloat(music.getText().toString());

                addDataToDb(name,loc,beerRate,wineRate,musicRate);

                float avg = (beerRate + wineRate + musicRate)/3;

                avgRating.setText(String.valueOf(avg));
                avgRating.setVisibility(View.VISIBLE);
            }
        });
    }

    public void displayRatings(){
        RatingFragment ratingFragment = RatingFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragmentDisplay,ratingFragment).addToBackStack(null).commit();
    }

    public void closeFragment(float beerRate, float wineRate, float musicRate){
        TextView beerRating = findViewById(R.id.beerDisplay);
        TextView wineRating = findViewById(R.id.wineDisplay);
        TextView musicRating = findViewById(R.id.musicDisplay);

        beerRating.setText(String.valueOf(beerRate));
        wineRating.setText(String.valueOf(wineRate));
        musicRating.setText(String.valueOf(musicRate));

        beerRating.setVisibility(View.VISIBLE);
        wineRating.setVisibility(View.VISIBLE);
        musicRating.setVisibility(View.VISIBLE);

        FragmentManager fm = getSupportFragmentManager();
        RatingFragment ratingFragment = (RatingFragment) fm.findFragmentById(R.id.fragmentDisplay);

        if(ratingFragment!= null){
            FragmentTransaction fragmentTransaction =
                    fm.beginTransaction();
            fragmentTransaction.remove(ratingFragment).commit();
        }
    }

    private void addDataToDb(String name, String address, float beerRate, float wineRate, float musicRate){
        locInfo.setLocName(name);
        locInfo.setAddress(address);
        locInfo.setBeerRating(beerRate);
        locInfo.setWineRating(wineRate);
        locInfo.setMusicRating(musicRate);

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbReference.setValue(locInfo);

                Toast.makeText(MainActivity.this,"Data added successfully!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Data was not added",Toast.LENGTH_SHORT).show();
            }
        });
    }


}