package com.milesguan.androidstudy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.milesguan.androidstudy.R;
import com.milesguan.androidstudy.coordinator.CoordinatorLayoutActivity;
import com.milesguan.androidstudy.glide.GlideActivity;
import com.milesguan.androidstudy.rxjava.RxBusActivity;
import com.milesguan.androidstudy.rxjava.RxJavaActivity;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private List<Entry> initEntry() {
        List<Entry> entries = new LinkedList<>();
        entries.add(new Entry(CoordinatorLayoutActivity.class , "CoordinatorLayout"));
        entries.add(new Entry(RxJavaActivity.class , "RxJava基本api"));
        entries.add(new Entry(RxBusActivity.class , "RxJava用于事件总线"));
        entries.add(new Entry(DataBindingActivity.class , "DataBinding"));
        entries.add(new Entry(AspectActivity.class , "AspectJ面向切面编程"));
        entries.add(new Entry(TestActivity.class , "TestActivity"));
        entries.add(new Entry(RecyclerViewActivity.class , "RecyclerView"));
        entries.add(new Entry(GlideActivity.class , "Glide"));
        return entries;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView listView = (ListView) findViewById(R.id.lv);
        final List<Entry> entries = initEntry();
        ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>(this , R.layout.item_entry , R.id.text , entries);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this , entries.get(position).activity));
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                100);

    }

    private static class Entry{

        public Class<? extends Activity> activity;

        public String title;

        public Entry(Class<? extends Activity> activity, String title) {
            this.activity = activity;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
