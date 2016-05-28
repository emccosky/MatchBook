package com.viperbots.matchbook;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.Thread;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements Runnable {

    private ListView navDrawerList;
    private ArrayAdapter<String> navAdapter;
    private ActionBarDrawerToggle navDrawerToggle;
    private DrawerLayout navDrawerLayout;
    private String navActivityTitle;

    String fileName = "myFile.comp";
    Competition currComp;

    awsDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF670F")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navActivityTitle = getTitle().toString();
        setupDrawer();

        navDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        navDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You clicked a thing", Toast.LENGTH_SHORT).show();
            }
        });

        initDB();
        //initData(); ============== IMPLEMENT FILE SAVING TO LIMIT DATABASE QUERIES ================
        currComp = new Competition(0, "Austin Competition", "Austin, Texas", "May 27, 2016");
        refreshData();
        populateMatchList();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if(navDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        navDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initDB(){
        db = new awsDBHandler();
        //db.populateMatch(new Match(1,0,6299,4997,8221,4211));
    }

    private void initData(){
        File file = new File(getFilesDir(),fileName);

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            currComp = (Competition) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLeaveActivity(){
        File file = new File(getFilesDir(),fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currComp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDrawerItems() {
        String[] navArray = {"Home", "My Team", "Teams", "Event Details", "Settings"};
        navAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navArray);
        navDrawerList.setAdapter(navAdapter);
    }

    private void setupDrawer(){
        navDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("FTC MatchBook");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(navActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        navDrawerToggle.setDrawerIndicatorEnabled(true);
        navDrawerLayout.setDrawerListener(navDrawerToggle);
    }

    public void populateMatchList(){
        ArrayList<Match> matches = currComp.getMatches();
        TableLayout matchTable = (TableLayout)findViewById(R.id.matchTableMain);
        for(int i = 0; i < matches.size(); i++){
            LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout row = (RelativeLayout) inflater.inflate(R.layout.table_row, null);
            TextView matchNum = (TextView) row.findViewById(R.id.matchNumLabel);
            matchNum.setText(i+1+"");
            matchTable.addView(row,i);
        }
    }

    public void refreshData(){
        currComp.setMatches(db.getAllMatches(currComp._id));
    }

    @Override
    public void run() {
        refreshData();
    }
}
