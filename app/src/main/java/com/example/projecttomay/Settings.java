package com.example.projecttomay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Settings extends AppCompatActivity {
    private String [] FILENAME = {
            "listochek.txt",
            "listochek2.txt",
            "listochek3.txt",
            "listochek4.txt",
            "listochek5.txt",
            "listochek6.txt",
            "listochek7.txt"};
    ArrayList<String> list = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;
    public static int day;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(howDay());
        openFile();
        listView = (ListView) findViewById(R.id.list_items);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }
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
            case R.id.d1:day = 2;
            openFile(); return true;
            case R.id.d2:day = 3;openFile(); return true;
            case R.id.d3:day = 4;openFile(); return true;
            case R.id.d4:day = 5;openFile(); return true;
            case R.id.d5:day = 6;openFile(); return true;
            case R.id.d6:day = 0;openFile(); return true;
            case R.id.d7:day = 1;openFile(); return true;
            case R.id.toDay:openFile(); return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        //return super.onOptionsItemSelected(item);
    }

    void openFile(){

        try {
            InputStream inputstream = openFileInput(FILENAME[day]);

            if (openFileInput(FILENAME[day])!=null){
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                String line;
                StringBuilder builder = new StringBuilder();

                list = new ArrayList<>();
                while ((line = reader.readLine())!=null){
                    list.add(line);
                }

                inputstream.close();
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);

            }
        } catch (Throwable e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Exposition: " + e.toString(), Toast.LENGTH_LONG).show();
        }
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

    public void setNewOpject(View view) {
        adapter.setNotifyOnChange(true);
        list.add(editText.getText().toString());
        editText.setText("");
    }

    public void saves(View view) {
        try {
            //OutputStream outputStream = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(FILENAME[day],0));
            for (int i = 0; i < list.size(); i++) {
                osw.write(list.get(i)+"\n");
            }
            osw.close();
        } catch (Throwable e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Exposition: " + e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}
