package com.example.benjaminlouis.projetfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AjoutActivity extends AppCompatActivity {
    boolean modif;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        Set<String> metiers =settings.getStringSet("metiers",new HashSet<String>());

        ArrayAdapter<String> adapterMetier=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,new ArrayList<String>(metiers));
        AutoCompleteTextView metierAuto=(AutoCompleteTextView)findViewById(R.id.metierText);
        metierAuto.setAdapter(adapterMetier);


        Spinner spinner = (Spinner) findViewById(R.id.serviceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.services_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Intent addIntent=getIntent();
        modif=addIntent.getBooleanExtra("modif",false);

        if(modif){
            User u=addIntent.getParcelableExtra("user");
            id=u.getId();
            ((TextView)findViewById(R.id.nomEdit)).setText(u.getNom());
            ((TextView)findViewById(R.id.prenomEdit)).setText(u.getPrenom());
            ((TextView)findViewById(R.id.cvEdit)).setText(u.getCv());
            ((TextView)findViewById(R.id.metierText)).setText(u.getMetier());
            ((TextView)findViewById(R.id.mailEdit)).setText(u.getMail());
            ((TextView)findViewById(R.id.telephoneEdit)).setText(u.getTelephone());
            RadioGroup groupe =(RadioGroup)findViewById(R.id.sexeGroupe);
            for (int i=0;i< groupe.getChildCount();i++) {
                View o = groupe.getChildAt(i);
                if (o instanceof RadioButton) {
                    if(((RadioButton) o).getText().equals(u.getSexe())){
                        ((RadioButton) o).setChecked(true);
                    }
                }
            }

            Spinner serv=(Spinner)findViewById(R.id.serviceSpinner);
            serv.setSelection(adapter.getPosition(u.getService()));
        }

        Button valider = (Button)findViewById(R.id.valider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom =((EditText)findViewById(R.id.nomEdit)).getText().toString();
                String prenom=((EditText)findViewById(R.id.prenomEdit)).getText().toString();
                String metier= ((AutoCompleteTextView)findViewById(R.id.metierText)).getText().toString();
                String service= ((Spinner)findViewById(R.id.serviceSpinner)).getSelectedItem().toString();
                String mail=((EditText)findViewById(R.id.mailEdit)).getText().toString();
                String telephone =((EditText)findViewById(R.id.telephoneEdit)).getText().toString();
                String cv =((EditText)findViewById(R.id.cvEdit)).getText().toString();
                RadioGroup sex=(RadioGroup)findViewById(R.id.sexeGroupe);
                String sexe= ((RadioButton)findViewById(sex.getCheckedRadioButtonId())).getText().toString();

                if(!nom.equals("")&&!prenom.equals("")&&!metier.equals("")&&!service.equals("")&&!mail.equals("")
                        &&!telephone.equals("")&&!cv.equals("")) {
                    if(telephone.matches("^0[1-9][0-9]{8}")){
                        User user = new User(null, nom, prenom, sexe, metier, service, mail, telephone, cv);
                        Intent intent= new Intent(AjoutActivity.this,ListActivity.class);
                        if(modif){
                            user.setId(id);
                            intent.putExtra("modif",true);
                            ajoutPreferences(metier);

                        }
                        else {
                            intent.putExtra("add", true);
                            ajoutPreferences(metier);
                        }
                        intent.putExtra("user",user);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else{
                        Context context = getApplicationContext();
                        CharSequence text = "Seuls les numéros Français de forme 0XXXXXXXXX sont acceptés";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }

                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Tous les champs sont obligatoires";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        Button annuler = (Button)findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AjoutActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ajoutPreferences(String metier){

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        Set<String> metiers=settings.getStringSet("metiers",new HashSet<String>());
        metiers.add(metier);
        SharedPreferences.Editor editor=settings.edit();
        editor.putStringSet("metiers",metiers);
        editor.apply();
    }
}
