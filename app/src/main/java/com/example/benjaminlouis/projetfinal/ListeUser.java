package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import java.util.List;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class ListeUser extends Fragment {
    private UsersDataSource source;
    private UserDAO dao;
    private RecyclerView liste;

    public ListeUser(){


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_liste, container, false);
        this.liste=view.findViewById(R.id.listUsers);
        this.source = new UsersDataSource(this.getContext());
        this.dao=new UserDAO(source);
        printListUsers();
        return view;

    }

    private void printListUsers() {
        List<User> newUsers = this.dao.readAll();
        liste.setLayoutManager(new LinearLayoutManager(this.getContext()));
        liste.setAdapter(new MyAdapter(newUsers,this.getContext()));


    }

}
