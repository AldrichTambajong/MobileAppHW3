package com.example.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MealRaterDataSource {
    private static DBMealRaterHelper database;

    public MealRaterDataSource(Context context){
        database = new DBMealRaterHelper(context);
    }

    public static long insertData(String name, String dish, float rate){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("restaurantName",name);
        values.put("dishName",dish);
        values.put("rating",rate);
        long id = db.insert("mealRater",null,values);
        return id;
    }
}
