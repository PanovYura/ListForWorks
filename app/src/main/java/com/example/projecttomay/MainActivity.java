package com.example.projecttomay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    TextView textView, weekText;

    public static int day;
    public static String arrayss [][] = {
            {
                    "0. ",
                    "1. ",
                    "2. ",
                    "3. ОБЖ(чет.)/Физ-ра(неч.)",
                    "4. Баш"
            },
            {
                    "0. Отдых",
                    "1. И только отдых",
                    "2. Ну и дз"
            },
            {
                    "0. Общество (неч.)",
                    "1. Общество",
                    "2. Физика",
                    "3. Информ"
            },
            {
                    "0. Англ",
                    "1. Гео(чет.)/Био(неч.)"
            },
            {
                    "0. Англ(неч.)/Литра(чет.)",
                    "1. Литра",
                    "2. Русский",
                    "3. Истор(чет.)"
            },
            {
                    "0. Химия",
                    "1. История",
                    "2. Физ-ра",
                    "3. Матем"
            },
            {
                    "0. Информ(1-ая группа)",
                    "1. Матем",
                    "2. Матем",
                    "3. Информ(2-ая группа)"
            }
    };

    public static boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.llmain);
        textView = (TextView) findViewById(R.id.textView2);
        weekText = (TextView) findViewById(R.id.textView3);
        textView.setText(howDay());
        weekText.setText(howWeek());
        getSpisok(day);

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
        //int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_updater:
               recreate();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this,Settings.class);
                startActivity(intent);
                return true;
            case R.id.d1:getSpisok(2); return true;
            case R.id.d2:getSpisok(3); return true;
            case R.id.d3:getSpisok(4); return true;
            case R.id.d4:getSpisok(5); return true;
            case R.id.d5:getSpisok(6); return true;
            case R.id.d6:getSpisok(0); return true;
            case R.id.d7:getSpisok(1); return true;
            case R.id.toDay:getSpisok(day); return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        //return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            aBoolean = true;
        } else if (id == R.id.nav_gallery) {
            aBoolean = false;
        } else if(id == R.id.nav_send){
            Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static String howDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        day = calendar.get(Calendar.DAY_OF_WEEK);
        String s = null;
        switch (day){
            case 1: s="Воскресение";break;
            case 2: s= "Понедельник";break;
            case 3: s= "Вторник";break;
            case 4: s= "Среда";break;
            case 5: s= "Четверг";break;
            case 6: s= "Пятница";break;
            case 7: s= "Суббота";day = 0;break;
        }
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        return s;
    }

    void getSpisok(int i) {
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayss[i]);
        listView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayss[i]));

        switch (i){
            case 1: textView.setText("Воскресение");break;
            case 2: textView.setText("Понедельник");break;
            case 3: textView.setText("Вторник");break;
            case 4: textView.setText("Среда");break;
            case 5: textView.setText("Четверг");break;
            case 6: textView.setText("Пятница");break;
            case 0: textView.setText("Суббота");break;
        }
    }

    static String howWeek(){
        String s = "чет";
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        s = (week%2 == 0?"чет ":"нечет ") + week + " неделя";
        return s;
    }
}
