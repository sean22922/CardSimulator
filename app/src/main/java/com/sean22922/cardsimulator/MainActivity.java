package com.sean22922.cardsimulator;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FGO.OnFragmentInteractionListener,CC.OnFragmentInteractionListener,THBJ.OnFragmentInteractionListener,Custom.OnFragmentInteractionListener {
    private Toolbar toolbar;
    private FragmentTransaction ft;
    private FGO f_fgo=null;
    private CC f_cc=null;
    private THBJ f_thbj=null;
    private Custom f_custom=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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
        if (id == R.id.about) {
            AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
            b.setTitle(getString(R.string.about));
            b.setMessage(R.string.aboutmsg);
            b.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface d,int witch){

                }
            });
            b.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ft=getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.FGO:
                if(f_fgo==null)f_fgo=FGO.newInstance(0);
                ft.replace(R.id.container,f_fgo);
                ft.commitAllowingStateLoss();
                break;
            case R.id.CC:
                if(f_cc==null)f_cc=CC.newInstance(1);
                ft.replace(R.id.container,f_cc);
                ft.commitAllowingStateLoss();
                break;
            case R.id.THBJ:
                if(f_thbj==null)f_thbj=THBJ.newInstance(2);
                ft.replace(R.id.container,f_thbj);
                ft.commitAllowingStateLoss();
                break;
            case R.id.customize:
                if(f_custom==null)f_custom=Custom.newInstance(3);
                ft.replace(R.id.container,f_custom);
                ft.commitAllowingStateLoss();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri){

    }
}
