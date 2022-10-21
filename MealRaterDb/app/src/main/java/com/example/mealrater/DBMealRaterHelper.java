package com.example.mealrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBMealRaterHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MealRater.db";
    private static final int DB_VERSION = 1;
    private Context context;

    private static final String CREATE_TABLE_MEALRATER=
            "create table mealRater(_id integer primary key autoincrement,"
            + "restaurantName text not null,"
            + "dishName text not null,"
            + "rating real) ";

    public DBMealRaterHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEALRATER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        Log.w(DBMealRaterHelper.class.getName(),
                "Upgrading database from version " + oldV + " to "
        + newV + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS mealRater");
        onCreate(db);
    }
}
