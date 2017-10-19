package com.example.benjaminlouis.projetfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class UsersDataSource {
    private final UsersDBHelper helper;
    private SQLiteDatabase db;

    public UsersDataSource(Context context){
        helper=new UsersDBHelper(context);
    }

    public SQLiteDatabase getDb() {
        if(db==null)this.open();
        return db;
    }

    public void open() {
        db=helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public UserDAO newUserDAO(){
        return new UserDAO(this);
    }
}
