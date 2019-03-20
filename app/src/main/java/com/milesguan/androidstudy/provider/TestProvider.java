package com.milesguan.androidstudy.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Copyright (c) 2017 BiliBili Inc.
 * Created by renjieguan on 17/5/31.
 */

public class TestProvider extends ContentProvider {
    private MyDbHelper myDbHelper;
    private UriMatcher matcher;


    public static final String CONTENT_AUTHORITY = "com.milesguan.androidstudy.provider.TestProvider";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int STUDENT_ID = 1;
    private static final int STUDENT = 2;

    @Override
    public boolean onCreate() {
        myDbHelper = new MyDbHelper(getContext());
        matcher = new UriMatcher(UriMatcher.NO_MATCH);        // 创建匹配器, 用来识别Uri
        matcher.addURI(CONTENT_AUTHORITY, "student/#", STUDENT_ID);    // 添加一个带id的Uri, #可以匹配一个数字
        matcher.addURI(CONTENT_AUTHORITY, "student", STUDENT);        // 给匹配器添加一个可以识别的Uri, 指定返回的Code
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch ( matcher.match(uri)) {
            case STUDENT:
                cursor = db.query("student", projection, selection, selectionArgs, sortOrder, null, null);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch (matcher.match(uri)) {
            case STUDENT:
                _id = db.insert("student", null, values);
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
