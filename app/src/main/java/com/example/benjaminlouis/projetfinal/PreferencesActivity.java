package com.example.benjaminlouis.projetfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PreferencesActivity extends AppCompatActivity {
    ArrayList<String> metiersList;
    ArrayList<String> prefs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        Set<String> metiers =settings.getStringSet("metiers",new HashSet<String>());
        metiersList=new ArrayList<String>(metiers);
        final ArrayAdapter<String> prefAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,metiersList);
        ListView listePref=(ListView)findViewById(R.id.listpref);
        listePref.setAdapter(prefAdapter);
        prefAdapter.getCount();

        listePref.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prefAdapter.remove(prefAdapter.getItem(position));
            }
        });

        Button valider = (Button)findViewById(R.id.Validerpref);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<prefAdapter.getCount();i++){
                    prefs.add(prefAdapter.getItem(i));
                }
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor=settings.edit();
                if(prefs.size()>0)
                    editor.putStringSet("metiers",new HashSet<String>(prefs));
                else
                    editor.putStringSet("metiers",new HashSet<String>());
                editor.apply();
                Intent i=new Intent(PreferencesActivity.this,ListActivity.class);
                startActivity(i);
            }
        });

    }
}
