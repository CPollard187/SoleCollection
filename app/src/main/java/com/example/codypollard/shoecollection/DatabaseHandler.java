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

    public static final String TABLE_SHOES = "shoe";
    /**
     * CREATE column names
     */

    public static final String COLUMN_ID = "id";

    /**
     *  Table column names
     */

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_TYPE = "type";
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
            + COLUMN_BRAND + " TEXT, " + COLUMN_TYPE + " TEXT,"
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
        values.put(COLUMN_BRAND, shoe.getBrand());
        values.put(COLUMN_TYPE, shoe.getType());
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
    public Shoe getShoe(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Shoe shoe = null;
        Cursor cursor = db.query(TABLE_SHOES,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_BRAND, COLUMN_TYPE,
                        COLUMN_DESCRIPTION,COLUMN_COLOURWAY,COLUMN_CONDITION,COLUMN_RETAILPRICE},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null,null);
        if(cursor != null){
            cursor.moveToFirst();
            shoe = new Shoe(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));
        }
        db.close();
        return shoe;
    }

    public ArrayList<Shoe> getAllLocations(){
        ArrayList<Shoe> shoeList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SHOES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                shoeList.add(new Shoe(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)));
            }while(cursor.moveToNext());
        }
        db.close();
        return shoeList;
    }

    /**
     * UPDATE
     */
    public int updateShoe(Shoe shoe){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, shoe.getName());
        values.put(COLUMN_BRAND, shoe.getBrand());
        values.put(COLUMN_TYPE, shoe.getType());
        values.put(COLUMN_DESCRIPTION, shoe.getDescription());
        values.put(COLUMN_COLOURWAY, shoe.getColourWay());
        values.put(COLUMN_CONDITION, shoe.getCondition());
        values.put(COLUMN_RETAILPRICE, shoe.getRetailPrice());
        return db.update(TABLE_SHOES, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(shoe.getId())});
    }


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

