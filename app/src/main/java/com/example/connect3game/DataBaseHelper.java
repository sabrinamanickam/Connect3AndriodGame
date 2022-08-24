package com.example.connect3game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String WINNER_TABLE1 = "WINNER_TABLE";
    public static final String WINNER_TABLE = WINNER_TABLE1;
    public static final String WINNER_NAME = "WINNER_NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context,"winner db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + WINNER_TABLE1 + "(" + WINNER_NAME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(WinnerModel winnerModel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(WINNER_NAME,winnerModel.getName());
        long insert = db.insert(WINNER_TABLE, null, cv);
        if(insert==-1)
            return false;
        else
            return true;

    }

    public boolean deleteOne(WinnerModel winnerModel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String queryString=("DELETE FROM " + WINNER_TABLE + " WHERE " + WINNER_NAME + " = "+ winnerModel.getName());
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else
            return false;
    }




    public List<WinnerModel> getAll()
    {
        List<WinnerModel> returnList= new ArrayList<>();

        String queryString="SELECT * FROM "+WINNER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                String winnerName=cursor.getString(0);
                WinnerModel newWinner=new WinnerModel(winnerName);
                returnList.add(newWinner);
            }while (cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return returnList;
    }
}

