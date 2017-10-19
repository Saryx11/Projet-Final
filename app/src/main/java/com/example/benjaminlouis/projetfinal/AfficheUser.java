package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class AfficheUser extends Fragment {
    Button appel;

    public AfficheUser(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        Button appel=view.findViewById(R.id.callButton);
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        appel.setEnabled(false);

        Button mail = view.findViewById(R.id.mailButton);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mail.setEnabled(false);
        return view;

    }

    public void updateUserView(User usr){
        
    }
}
