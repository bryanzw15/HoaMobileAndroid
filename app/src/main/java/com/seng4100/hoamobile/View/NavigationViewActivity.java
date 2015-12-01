package com.seng4100.hoamobile.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.Model.Activitybooks;
import com.seng4100.hoamobile.R;

import java.util.List;

import javax.xml.namespace.NamespaceContext;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class NavigationViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            ActivitybookFragmentView.OnFragmentInteractionListener,
            ActivityFragmentView.OnFragmentInteractionListener,
            TasklistFragmentView.OnFragmentInteractionListener,
            View.OnClickListener{

    View fabActivitybook;
    View fabActivity;
    View fabTasklist;
    View fabTask;
    String activitybookID;
    String activityID;
    String tasklistID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fabActivitybook = (View) findViewById(R.id.fabNewActivitybook);
        fabActivity = (View) findViewById(R.id.fabNewActivity);
        fabTasklist = (View) findViewById(R.id.fabNewTasklist);
        fabTask = (View) findViewById(R.id.fabNewTask);

        fabActivity.setVisibility(View.INVISIBLE);
        fabTasklist.setVisibility(View.INVISIBLE);
        fabTask.setVisibility(View.INVISIBLE);

        fabActivitybook.setOnClickListener(this);
        fabActivity.setOnClickListener(this);
        fabTasklist.setOnClickListener(this);
        fabTask.setOnClickListener(this);

        fabTask.setClickable(false);
        fabTasklist.setClickable(false);
        fabActivity.setClickable(false);

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
        getMenuInflater().inflate(R.menu.navigation_view, menu);
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
        Fragment fragment = null;
        Class fragmentClass = ActivitybookFragmentView.class;


        switch(id) {
            case R.id.nav_activitybooks:
                fragmentClass = ActivitybookFragmentView.class;
                fabActivitybook.setVisibility(View.VISIBLE);
                fabActivity.setVisibility(View.INVISIBLE);
                fabTasklist.setVisibility(View.INVISIBLE);
                fabTask.setVisibility(View.INVISIBLE);
                fabActivity.setClickable(false);
                fabTask.setClickable(false);
                fabTasklist.setClickable(false);
                break;
            case R.id.nav_activities:
                break;
            case R.id.nav_addActivity:
                fragmentClass = AddActivitybookViewFragment.class;
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;

            default:
                fragmentClass = ActivitybookFragmentView.class;
                break;
        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(fragment != null) {

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(fragment.getTag());
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String id, String requestClass) {
        Log.d("Sal2:", "id: " + id + " requestClass: " + requestClass);
        Fragment fragment;



        Class fragmentClass = ActivitybookFragmentView.class;
        //Get the specific fragment from that is passing the data

        switch(requestClass) {
            case "Activitybook":
                fragment = ActivitybookFragmentView.newInstance(id, requestClass);
                break;
            case "Activity":
                activitybookID = id;
                fragment = ActivityFragmentView.newInstance(id, requestClass);
                fabActivitybook.setVisibility(View.VISIBLE);
                fabActivity.setVisibility(View.VISIBLE);
                fabTasklist.setVisibility(View.INVISIBLE);
                fabTask.setVisibility(View.INVISIBLE);
                fabActivity.setClickable(true);
                fabTask.setClickable(false);
                fabTasklist.setClickable(false);
                break;
            case "Tasklist":
                activityID = id;
                fragment = TasklistFragmentView.newInstance(id, requestClass);
                fabActivitybook.setVisibility(View.VISIBLE);
                fabActivity.setVisibility(View.VISIBLE);
                fabTasklist.setVisibility(View.VISIBLE);
                fabTask.setVisibility(View.VISIBLE);
                fabTask.setClickable(true);
                fabTasklist.setClickable(true);
                fabActivity.setClickable(true);
                break;
            default:
                fragment = ActivitybookFragmentView.newInstance(id, requestClass);
                fabActivity.setVisibility(View.INVISIBLE);
                fabTasklist.setVisibility(View.INVISIBLE);
                fabTask.setVisibility(View.INVISIBLE);
                break;
        }


        if(fragment != null) {

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(fragment.getTag());
            transaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        //v.getTag();
        Log.e("Tag", "" + v.getTag());
        String tag = "" + v.getTag();
        Fragment fragment = null;
        Class fragmentClass = NavigationViewActivity.class;
        switch(tag) {
            case "newActivityBook":
                fragmentClass = AddActivitybookViewFragment.class;
                break;
            case "newActivity":
                fragment = AddActivityViewFragment.newInstance(activitybookID, "");
                break;
            case "newTasklist":
                fragment = AddTasklistViewFragment.newInstance(activityID, "");
                break;
            case "newTask":
                fragmentClass = AddTaskViewFragment.class;
                break;
            default:
                fragmentClass = NavigationViewActivity.class;
                break;
        }



        if(fragment == null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
    }
}
