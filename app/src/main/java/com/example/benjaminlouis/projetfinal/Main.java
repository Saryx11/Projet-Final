package com.example.benjaminlouis.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    UsersDataSource source = new UsersDataSource(this);
    UserDAO dao=new UserDAO(source);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        Boolean addIntent = intent.getBooleanExtra("add", false);
        if(addIntent){
            User u=intent.getParcelableExtra("user");
            dao.create(u);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this,AjoutActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        printListUsers();



    }

    private void printListUsers() {
        List<User> newUsers = this.dao.readAll();

        RecyclerView listUsers = (RecyclerView) findViewById(R.id.listUsers);
        listUsers.setLayoutManager(new LinearLayoutManager(this));
        listUsers.setAdapter(new MyAdapter(newUsers));/*
        final MyAdapter<User> adapter = new ArrayAdapter<User>(Main.this,
                android.R.layout.simple_list_item_1,newUsers);
        listUsers.setAdapter(adapter);*/

        /*listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item = adapter.getItem(position);
                Log.d("ACTION","CLICKED ON SOMEONE : " + item.toString());
                // We cannot send a User in an intent, so we send each attribute in extra
                Intent intent = new Intent(Main.this,AddDeleteActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("family", item.getFamilyName());
                intent.putExtra("first",item.getFirstName());
                intent.putExtra("age",item.getAge());
                intent.putExtra("job",item.getJob());
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_aide) {
            // Handle the camera action
        } else if (id == R.id.nav_options) {

        /*} else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {*/

        } else if (id == R.id.nav_ajouter) {

        } else if (id == R.id.nav_supprimer) {

        } else if (id==R.id.nav_vider){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
