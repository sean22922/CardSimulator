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
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FGO.OnFragmentInteractionListener,
        CC.OnFragmentInteractionListener,
        THBJ.OnFragmentInteractionListener,
        Custom.OnFragmentInteractionListener,
        Default.OnFragmentInteractionListener,
        CustomResultFragment.OnFragmentInteractionListener,
        CustomProbabilityFragment.OnFragmentInteractionListener {
    private Toolbar toolbar;
    private FragmentTransaction ft;
    private Default f_def=null;
    private FGO f_fgo=null;
    private CC f_cc=null;
    private THBJ f_thbj=null;
    private Custom f_custom=null;
    private LicensesDialog ld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ft=getSupportFragmentManager().beginTransaction();
        if(f_def==null)f_def=Default.newInstance(100);
        ft.add(R.id.container,f_def);
        ft.commit();
        Notices ns=new Notices();
        ns.addNotice(new Notice("MaterialTabHost","https://github.com/yanzm/MaterialTabHost","Copyright 2014 Yuki Anzai",new ApacheSoftwareLicense20()));
        ns.addNotice(new Notice("LicensesDialog ","https://github.com/PSDev/LicensesDialog","Copyright 2013-2017 Philip Schiffer",new ApacheSoftwareLicense20()));
        ns.addNotice(new Notice("MaterialEditText","https://github.com/rengwuxian/MaterialEditText","Copyright 2014 rengwuxian",new ApacheSoftwareLicense20()));
        ld=new LicensesDialog.Builder(this)
                .setTitle(R.string.notice)
                .setCloseText(R.string.close)
                .setNotices(ns)
                .build();
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
            ((TextView)new AlertDialog.Builder(this).setTitle(getString(R.string.about))
                    .setMessage(Html.fromHtml(getString(R.string.aboutmsg)))
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show().findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            return true;
        }
        if(id == R.id.license){
            ld.show();
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
                ft.commit();
                setTitle(getString(R.string.FGO));
                break;
            case R.id.CC:
                if(f_cc==null)f_cc=CC.newInstance(1);
                ft.replace(R.id.container,f_cc);
                ft.commit();
                setTitle(getString(R.string.CC));
                break;
            case R.id.THBJ:
                if(f_thbj==null)f_thbj=THBJ.newInstance(2);
                ft.replace(R.id.container,f_thbj);
                ft.commitAllowingStateLoss();
                setTitle(getString(R.string.THBJ));
                break;
            case R.id.customize:
                if(f_custom==null)f_custom=Custom.newInstance(3);
                ft.replace(R.id.container,f_custom);
                ft.commit();
                setTitle(getString(R.string.Custom));
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
