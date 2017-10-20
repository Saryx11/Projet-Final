package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class AfficheUser extends Fragment {
    Button appel;
    View view;

    public AfficheUser(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);
        User u=this.getArguments().getParcelable("user");

        if(u!=null) {

            ((TextView)view.findViewById(R.id.nomView)).setText(u.getNom());
            ((TextView)view.findViewById(R.id.prenomView)).setText(u.getPrenom());
            ((TextView)view.findViewById(R.id.cvView)).setText(u.getCv());
            ((TextView)view.findViewById(R.id.metierview)).setText(u.getMetier());
            ((TextView)view.findViewById(R.id.mailView)).setText(u.getMail());
            ((TextView)view.findViewById(R.id.sexeView)).setText(u.getSexe());
            ((TextView)view.findViewById(R.id.serviceView)).setText(u.getService());
            ((TextView)view.findViewById(R.id.telephoneView)).setText(u.getTelephone());

            /*RadioGroup groupe =(RadioGroup)view.findViewById(R.id.sexeGroupe);
            for (int i=0;i< groupe.getChildCount();i++) {
                View o = groupe.getChildAt(i);
                if (o instanceof RadioButton) {
                    if(((RadioButton) o).getText().equals(usr.getSexe())){
                        ((RadioButton) o).setChecked(true);
                    }
                }
            }

            Spinner serv=(Spinner)view.findViewById(R.id.serviceSpinner);
            ArrayAdapter<String> adapter=(ArrayAdapter<String>)serv.getAdapter();
            serv.setSelection(adapter.getPosition(usr.getService()));*/

        }

        Button appel=view.findViewById(R.id.callButton);
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telephone = Uri.parse("tel:"+((TextView)view.findViewById(R.id.telephoneView)).getText().toString());
                Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
                startActivity(secondeActivite);
            }
        });


        Button mail = view.findViewById(R.id.mailButton);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mail = Uri.parse("mailto:"+((TextView)view.findViewById(R.id.mailView)).getText().toString());
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                startActivity(i);
            }
        });

        return view;

    }

    public void updateUserView(User usr){
        ((TextView)getView().findViewById(R.id.nomView)).setText(usr.getNom());
        ((TextView)getView().findViewById(R.id.prenomView)).setText(usr.getPrenom());
        ((TextView)getView().findViewById(R.id.cvView)).setText(usr.getCv());
        ((TextView)getView().findViewById(R.id.metierview)).setText(usr.getMetier());
        ((TextView)getView().findViewById(R.id.mailView)).setText(usr.getMail());
        ((TextView)getView().findViewById(R.id.telephoneView)).setText(usr.getTelephone());
        RadioGroup groupe =(RadioGroup)getView().findViewById(R.id.sexeGroupe);
        for (int i=0;i< groupe.getChildCount();i++) {
            View o = groupe.getChildAt(i);
            if (o instanceof RadioButton) {
                if(((RadioButton) o).getText().equals(usr.getSexe())){
                    ((RadioButton) o).setChecked(true);
                }
            }
        }

        Spinner serv=(Spinner)getView().findViewById(R.id.serviceSpinner);
        ArrayAdapter<String> adapter=(ArrayAdapter<String>)serv.getAdapter();
        serv.setSelection(adapter.getPosition(usr.getService()));
    }
}
