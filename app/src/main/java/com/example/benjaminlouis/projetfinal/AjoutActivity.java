package com.example.benjaminlouis.projetfinal;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

public class AjoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Spinner spinner = (Spinner) findViewById(R.id.serviceSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.services_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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
                    User user = new User(null, nom, prenom, sexe, metier, service, mail, telephone, cv);
                    /*UsersDataSource dataSource = new UsersDataSource(AjoutActivity.this);
                    UserDAO userDAO = dataSource.newUserDAO();
                    user = userDAO.create(user);*/
                    Intent intent= new Intent(AjoutActivity.this,Main.class);
                    intent.putExtra("add",true);
                    intent.putExtra("user",user);
                    startActivity(intent);
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
                Intent intent= new Intent(AjoutActivity.this,Main.class);
                startActivity(intent);
            }
        });
    }
}
