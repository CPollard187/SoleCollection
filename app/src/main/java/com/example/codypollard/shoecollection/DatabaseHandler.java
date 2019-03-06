package com.example.codypollard.shoecollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Keep track of the database version
     */

    public static final int DATABASE_VERSION = 1;

    /**
     * Create the name of the Database
     */

    public static final String DATABASE_NAME = "shoecollection";

    /**
     * Create the name of our tables
     */

    public static final String TABLE_SHOES = "location";
    /**
     * CREATE column names
     */

    public static final String COLUMN_ID = "id";

    /**
     *  Table column names
     */

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COLOURWAY = "colourway";
    public static final String COLUMN_CONDITION = "condition";
    public static final String COLUMN_RETAILPRICE = "retailprice";

    /**
     * Create statements for our tables
     */

    public static final String CREATE_SHOE_TABLE = "CREATE TABLE " +
            TABLE_SHOES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT, " + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_COLOURWAY + " TEXT, " + COLUMN_CONDITION + " TEXT,"
            + COLUMN_RETAILPRICE + " TEXT)";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * CRUD OPERATIONS FOR THE DATABASE AND TABLES
     * Create
     * Read
     * Update
     * Delete
     */

    public void addShoe(Shoe shoe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, shoe.getName());
        values.put(COLUMN_DESCRIPTION, shoe.getDescription());
        values.put(COLUMN_COLOURWAY, shoe.getColourWay());
        values.put(COLUMN_CONDITION, shoe.getCondition());
        values.put(COLUMN_RETAILPRICE, shoe.getRetailPrice());
        db.insert(TABLE_SHOES, null, values);
        db.close();
    }

    /**
     * READ OPERATIONS
     */


    /**
     * UPDATE
     */



    /**
     * DELETE OPERATIONS
     */

    public void deleteShoe(int shoe){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHOES, COLUMN_ID + "=?",
                new String[]{String.valueOf(shoe)});
        db.close();
    }

}

