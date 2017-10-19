package com.example.benjaminlouis.projetfinal;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class UserDAO {
    private static final String COL_NOM="nom";
    private static final String COL_PRENOM="prenom";
    private static final String COL_SEXE="sexe";
    private static final String COL_METIER="metier";
    private static final String COL_SERVICE="service";
    private static final String COL_MAIL="mail";
    private static final String COL_TELEPHONE="telephone";
    private static final String COL_CV="cv";
    private static final String COL_ID="id";
    private static final String TABLE_NAME="Sante";
    private static UsersDataSource dataSource;

    public UserDAO(UsersDataSource dataSource){
        this.dataSource=dataSource;
    }



    public synchronized User create(User user){
        ContentValues values=new ContentValues();
        values.put(COL_NOM,user.getNom());
        values.put(COL_PRENOM,user.getPrenom());
        values.put(COL_SEXE,user.getSexe());
        values.put(COL_METIER,user.getMetier());
        values.put(COL_SERVICE,user.getService());
        values.put(COL_MAIL,user.getMail());
        values.put(COL_TELEPHONE,user.getTelephone());
        values.put(COL_CV,user.getCv());

        int id=(int)this.dataSource.getDb().insert(TABLE_NAME,null,values);

        //user.setId(id);
        return user;
    }

    public synchronized User update(User user){
        ContentValues values=new ContentValues();
        values.put(COL_NOM,user.getNom());
        values.put(COL_PRENOM,user.getPrenom());
        values.put(COL_SEXE,user.getSexe());
        values.put(COL_METIER,user.getMetier());
        values.put(COL_SERVICE,user.getService());
        values.put(COL_MAIL,user.getMail());
        values.put(COL_TELEPHONE,user.getTelephone());
        values.put(COL_CV,user.getCv());

        String clause=COL_ID+"=?";
        String[] clauseArgs=new String []{String.valueOf(user.getId())};

        this.dataSource.getDb().update(TABLE_NAME,values,clause,clauseArgs);
        return user;
    }

    public synchronized void delete(User user){
        String clause=COL_ID+"=?";
        String[] clauseArgs=new String[]{String.valueOf(user.getId())};
        this.dataSource.getDb().delete(TABLE_NAME,clause,clauseArgs);
    }

    public User read(User user){
        String[]allColumns = new String []{COL_ID,COL_NOM,COL_PRENOM,COL_SEXE,COL_METIER,COL_SERVICE,COL_MAIL,COL_TELEPHONE,COL_CV};
        String clause=COL_ID+"=?";
        String[] clauseArgs=new String[]{String.valueOf(user.getId())};
        Cursor cursor = dataSource.getDb().query(TABLE_NAME,allColumns,"ID=?",clauseArgs,null,null,null);
        cursor.moveToFirst();
        user.setNom(cursor.getString(1));
        user.setPrenom(cursor.getString(2));
        user.setSexe(cursor.getString(3));
        user.setMetier(cursor.getString(4));
        user.setService(cursor.getString(5));
        user.setMail(cursor.getString(6));
        user.setTelephone(cursor.getString(7));
        user.setCv(cursor.getString(8));
        cursor.close();
        return user;
    }

    public List<User> readAll(){
        String[]allColumns = new String []{COL_ID,COL_NOM,COL_PRENOM,COL_SEXE,COL_METIER,COL_SERVICE,COL_MAIL,COL_TELEPHONE,COL_CV};
        Cursor cursor = dataSource.getDb().query(TABLE_NAME,allColumns,null,null,null,null,null);
        List<User>users=new ArrayList<User>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            users.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }
}
