package com.milesguan.androidstudy.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.milesguan.androidstudy.R;

/**
 * Copyright (c) 2017 BiliBili Inc.
 * Created by renjieguan on 17/5/31.
 */

public class ContentProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "peng");
                getContentResolver().insert(Uri.withAppendedPath(TestProvider.BASE_URI, "student"), contentValues);
            }
        });
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getContentResolver().query(Uri.withAppendedPath(TestProvider.BASE_URI, "student"), null, null, null, null);
                if (cursor == null) {
                    return;
                }
                while (cursor.moveToNext()) {
                    Log.i("grj", cursor.getInt(cursor.getColumnIndex("id")) + " " + cursor.getString(cursor.getColumnIndex("name")));
                }
                cursor.close();
            }
        });
        Uri uri = TestProvider.BASE_URI.buildUpon().appendPath("1").appendQueryParameter("aa", "123").build();
        String s = uri.getQueryParameter("aa");
        Log.i("grj", uri.toString() + "   " + s + "   "  + uri.getPathSegments());

    }
}
