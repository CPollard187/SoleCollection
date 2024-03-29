package com.example.codypollard.shoecollection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.core.Twitter;

/**
 * Author = Cody Pollard
 * Date = 2019
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    HomeFragment.OnFragmentInteractionListener,
                    ClosetFragment.OnFragmentInteractionListener,
                    AddAShoeFragment.OnFragmentInteractionListener,
                    CollectionFragment.OnFragmentInteractionListener,
                    FavShoeFragment.OnFragmentInteractionListener,
                    UpdateShoeFragment.OnFragmentInteractionListener,
                    SearchFragment.OnFragmentInteractionListener,
                    SearchFormFragment.OnFragmentInteractionListener,
                    CreditsFragment.OnFragmentInteractionListener {

    FragmentManager fm;
    public static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Start a transactionn and set it to view the HomeFragment
        fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new HomeFragment());
            transaction.commit();
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        /**
         * When you click on a nav bar button it sends you to the corresponding fragment
         */

        if (id == R.id.nav_home) {
            fm.beginTransaction()
            .replace(R.id.content, new HomeFragment())
            .commit();
        } else if (id == R.id.nav_closet) {
            fm.beginTransaction()
            .replace(R.id.content, new ClosetFragment())
            .commit();
        } else if (id == R.id.nav_collection) {
            fm.beginTransaction()
            .replace(R.id.content, new CollectionFragment())
            .commit();
        } else if (id == R.id.nav_addashoe) {
            fm.beginTransaction()
                    .replace(R.id.content, new AddAShoeFragment())
                    .commit();
        }else if (id == R.id.nav_searchShoe) {
            fm.beginTransaction()
                    .replace(R.id.content, new SearchFormFragment())
                    .commit();
        }else if (id == R.id.nav_credits) {
                fm.beginTransaction()
                        .replace(R.id.content, new CreditsFragment())
                        .commit();
        } else if (id == R.id.nav_email) {
            String [] email = {"cody.pollard01@stclairconnect.ca"};
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Soles Collection App");
            intent.putExtra(Intent.EXTRA_TEXT, "Hey Creator, I was playing with your application and have a few questions");
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }else{
                Snackbar.make(findViewById(android.R.id.content),
                        "Download correct software to complete this task",
                        Snackbar.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
