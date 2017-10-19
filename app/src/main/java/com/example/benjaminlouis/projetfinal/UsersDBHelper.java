package com.example.benjaminlouis.projetfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class UsersDBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME="Sante.db";
    public static final int DB_Version=3;

    public UsersDBHelper(Context context){
        super(context, DB_NAME, null, DB_Version);
    }

    public static String getQueryCreate(){
        return "CREATE TABLE Sante("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"nom VARCHAR(30) NOT NULL,"
                +"prenom VARCHAR(20) NOT NULL,"
                +"sexe VARCHAR (20) NOT NULL,"
                +"metier VARCHAR(50) NOT NULL,"
                +"service VARCHAR(50)NOT NULL,"
                +"mail VARCHAR (50) NOT NULL,"
                +"telephone VARCHAR(10) NOT NULL,"
                +"cv VARCHAR (1500) NOT NULL"
                +");";
    }

    public static String getQueryDrop(){
        return "DROP TABLE IF EXISTS Sante";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getQueryCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getQueryDrop());
        db.execSQL(getQueryCreate());

    }
}
