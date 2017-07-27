package com.example.cookzyme.cookzyme.module;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cookzyme.cookzyme.database.Ingredients;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ice on 18-Jul-17.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "database";
    public static final String REFRIGERATOR_TABLE_NAME = "refrigerator";
    public static final String REFRIGERATOR_COLUMN_ID = "_id";
    public static final String REFRIGERATOR_COLUMN_NAME = "name";
    public static final String REFRIGERATOR_COLUMN_PATH = "path";
    public static final String REFRIGERATOR_COLUMN_AMOUNT = "amount";
    public static final String REFRIGERATOR_COLUMN_UNIT = "unit";
    public static final String REFRIGERATOR_COLUMN_EXPIRE = "expire";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + REFRIGERATOR_TABLE_NAME + " (" +
                REFRIGERATOR_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REFRIGERATOR_COLUMN_NAME + " TEXT, " +
                REFRIGERATOR_COLUMN_PATH + " TEXT, " +
                REFRIGERATOR_COLUMN_AMOUNT + " REAL UNSIGNED, " +
                REFRIGERATOR_COLUMN_UNIT + " TEXT," +
                REFRIGERATOR_COLUMN_EXPIRE + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + REFRIGERATOR_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertRefrigerator(Ingredients ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REFRIGERATOR_COLUMN_NAME, ingredient.getName());
        values.put(REFRIGERATOR_COLUMN_PATH, ingredient.getPath());
        values.put(REFRIGERATOR_COLUMN_AMOUNT, ingredient.getAmount());
        values.put(REFRIGERATOR_COLUMN_UNIT, ingredient.getUnit());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        values.put(REFRIGERATOR_COLUMN_EXPIRE, df.format(ingredient.getExpire()));

        return db.insert(REFRIGERATOR_TABLE_NAME, null, values);
    }

    public ArrayList<Ingredients> getAllRefrigerator() {
        ArrayList<Ingredients> refrigerator=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                REFRIGERATOR_COLUMN_ID,
                SQLiteDBHelper.REFRIGERATOR_COLUMN_NAME,
                SQLiteDBHelper.REFRIGERATOR_COLUMN_PATH,
                SQLiteDBHelper.REFRIGERATOR_COLUMN_UNIT,
                SQLiteDBHelper.REFRIGERATOR_COLUMN_AMOUNT,
                SQLiteDBHelper.REFRIGERATOR_COLUMN_EXPIRE
        };

//            String selection =
//                    SampleSQLiteDBHelper.PERSON_COLUMN_NAME + " like ? and " +
//                            SampleSQLiteDBHelper.PERSON_COLUMN_AGE + " > ? and " +
//                            SampleSQLiteDBHelper.PERSON_COLUMN_GENDER + " like ?";

//            String[] selectionArgs = {"%" + name + "%", age, "%" + gender + "%"};

        Cursor cursor = db.query(
                SQLiteDBHelper.REFRIGERATOR_TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                "date("+REFRIGERATOR_COLUMN_EXPIRE+")"                                      // don't sort
        );
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Ingredients in = new Ingredients(Integer.toString(cursor.getColumnIndex(projection[0])),
                        cursor.getString(cursor.getColumnIndex(projection[1])),
                        cursor.getString(cursor.getColumnIndex(projection[2])),
                        cursor.getString(cursor.getColumnIndex(projection[3])),
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex(projection[4]))),
                        df.parse(cursor.getString(cursor.getColumnIndex(projection[5]))));
                cursor.moveToNext();
                refrigerator.add(in);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return  refrigerator;
    }
    public int updateRefrigerator(Ingredients ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REFRIGERATOR_COLUMN_AMOUNT, ingredient.getAmount());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        values.put(REFRIGERATOR_COLUMN_EXPIRE, df.format(ingredient.getExpire()));
        values.put(REFRIGERATOR_COLUMN_UNIT,ingredient.getUnit());

        // updating row
        return db.update(REFRIGERATOR_TABLE_NAME, values, REFRIGERATOR_COLUMN_ID + " = ?",
                new String[] { String.valueOf(ingredient.getId()) });
    }
    public int removeRefrigerator(Ingredients ingredient){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection =SQLiteDBHelper.REFRIGERATOR_COLUMN_ID + " = ?";

        String[] selectionArgs = {"%" + ingredient.getId() + "%"};

        return db.delete(REFRIGERATOR_TABLE_NAME,selection,selectionArgs);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
