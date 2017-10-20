package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import java.util.List;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class ListeUserFragment extends Fragment {
    private UsersDataSource source;
    private UserDAO dao;
    private RecyclerView liste;

    public ListeUserFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.source = new UsersDataSource(this.getActivity());
        this.dao=new UserDAO(source);
        View view = inflater.inflate(R.layout.fragment_liste, container, false);

        Intent intent= getActivity().getIntent();
        Boolean addIntent = intent.getBooleanExtra("add", false);
        Boolean modifIntent = intent.getBooleanExtra("modif",false);
        Boolean supprIntent = intent.getBooleanExtra("suppr",false);
        if(addIntent){
            User u=intent.getParcelableExtra("user");
            dao.create(u);
        }
        if(modifIntent){
            User u=intent.getParcelableExtra("user");
            dao.update(u);
        }
        if(supprIntent){
            User u = intent.getParcelableExtra("user");
            dao.delete(u);
        }


        this.liste=view.findViewById(R.id.listUsers);
        printListUsers();
        return view;
    }

    private void printListUsers() {
        List<User> newUsers = this.dao.readAll();
        liste.setLayoutManager(new LinearLayoutManager(this.getContext()));
        liste.setAdapter(new MyAdapter(newUsers,this));
    }

}
