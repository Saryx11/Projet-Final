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

        //Récupération de l'intent d'ajout/modification/suppression
        Intent intent= getActivity().getIntent();
        Boolean addIntent = intent.getBooleanExtra("add", false);
        Boolean modifIntent = intent.getBooleanExtra("modif",false);
        Boolean supprIntent = intent.getBooleanExtra("suppr",false);

        //Si on vient d'ajouter
        //On ajoute l'utilisateur à la base
        if(addIntent){
            User u=intent.getParcelableExtra("user");
            dao.create(u);
            intent.putExtra("add",false);
        }


        //Si on vient de modifier
        //On Update l'utilisateur
        if(modifIntent){
            User u=intent.getParcelableExtra("user");
            dao.update(u);
            intent.putExtra("modif",false);
        }

        //Si on vient de supprimer
        //On supprime l'utilisateur dans la base
        if(supprIntent){
            User u = intent.getParcelableExtra("user");
            dao.delete(u);
        }

        //On affiche la liste des utilisateurs présents en base
        this.liste=view.findViewById(R.id.listUsers);
        printListUsers();
        return view;
    }

    private void printListUsers() {
        //Création de la liste des utilisateurs
        List<User> newUsers = this.dao.readAll();
        liste.setLayoutManager(new LinearLayoutManager(this.getContext()));
        liste.setAdapter(new MyAdapter(newUsers,this));
    }

}
