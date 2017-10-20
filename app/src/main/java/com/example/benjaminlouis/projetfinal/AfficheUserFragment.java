package com.example.benjaminlouis.projetfinal;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class AfficheUserFragment extends Fragment {
    Button appel;
    Button mail;
    Button modifier;
    Button supprimer;
    View view;
    User u;
    boolean confirm;
    public AfficheUserFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);
        u=null;
        appel=view.findViewById(R.id.callButton);
        mail = view.findViewById(R.id.mailButton);
        modifier = view.findViewById(R.id.modifier);
        supprimer = view.findViewById(R.id.supprimer);
        appel.setEnabled(false);
        mail.setEnabled(false);
        modifier.setEnabled(false);
        supprimer.setEnabled(false);

        if(this.getArguments()!=null) {
            u = this.getArguments().getParcelable("user");
        }

        if(u!=null) {
            updateUserView(u);
            /*((TextView)view.findViewById(R.id.nomView)).setText(u.getNom());
            ((TextView)view.findViewById(R.id.prenomView)).setText(u.getPrenom());
            ((TextView)view.findViewById(R.id.cvView)).setText(u.getCv());
            ((TextView)view.findViewById(R.id.metierview)).setText(u.getMetier());
            ((TextView)view.findViewById(R.id.mailView)).setText(u.getMail());
            ((TextView)view.findViewById(R.id.sexeView)).setText(u.getSexe());
            ((TextView)view.findViewById(R.id.serviceView)).setText(u.getService());
            ((TextView)view.findViewById(R.id.telephoneView)).setText(u.getTelephone());*/


        }


        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telephone = Uri.parse("tel:"+((TextView)view.findViewById(R.id.telephoneView)).getText().toString());
                Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
                startActivity(secondeActivite);
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mail = Uri.parse("mailto:"+((TextView)view.findViewById(R.id.mailView)).getText().toString());
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                startActivity(i);
            }
        });


        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AjoutActivity.class);
                i.putExtra("modif",true);
                i.putExtra("user",u);
                startActivity(i);
            }
        });


        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setMessage("Confirmez vous la suppression");
                adb.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //confirm=true;
                        Intent i=new Intent(getActivity(),ListActivity.class);
                        i.putExtra("suppr",true);
                        i.putExtra("user",u);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                });

                adb.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //confirm=false;
                    }
                });

                adb.show();
                /*if (confirm){
                    Intent i=new Intent(getActivity(),ListActivity.class);
                    i.putExtra("suppr",true);
                    i.putExtra("user",u);
                    startActivity(i);
                }*/
            }
        });

        return view;
    }


    public void updateUserView(User usr){

        this.u=usr;
        appel=view.findViewById(R.id.callButton);
        mail = view.findViewById(R.id.mailButton);
        modifier = view.findViewById(R.id.modifier);
        supprimer = view.findViewById(R.id.supprimer);
        ((TextView)view.findViewById(R.id.nomView)).setText(usr.getNom());
        ((TextView)view.findViewById(R.id.prenomView)).setText(usr.getPrenom());
        ((TextView)view.findViewById(R.id.cvView)).setText(usr.getCv());
        ((TextView)view.findViewById(R.id.metierview)).setText(usr.getMetier());
        ((TextView)view.findViewById(R.id.mailView)).setText(usr.getMail());
        ((TextView)view.findViewById(R.id.sexeView)).setText(usr.getSexe());
        ((TextView)view.findViewById(R.id.serviceView)).setText(usr.getService());
        ((TextView)view.findViewById(R.id.telephoneView)).setText(usr.getTelephone());
        appel.setEnabled(true);
        mail.setEnabled(true);
        modifier.setEnabled(true);
        supprimer.setEnabled(true);
    }

}
