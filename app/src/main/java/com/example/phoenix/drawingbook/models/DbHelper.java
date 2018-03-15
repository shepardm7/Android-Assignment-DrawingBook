package com.example.phoenix.drawingbook.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phoenix on 07-Aug-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "DrawingBookDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Drawing (topBar TEXT, botBar TEXT, topDash TEXT, midDash TEXT, botDash TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Drawing";
        db.execSQL(sql);
        onCreate(db);
    }

    public void saveDrawing(Drawing drawing) {
        String sql = "DELETE FROM Drawing";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        ContentValues drawingData = new ContentValues();
        drawingData.put("topBar", drawing.getTopBar());
        drawingData.put("botBar", drawing.getBotBar());
        drawingData.put("topDash", drawing.getTopDash());
        drawingData.put("midDash", drawing.getMidDash());
        drawingData.put("botDash", drawing.getBotDash());

        db.insert("Drawing", null, drawingData);
    }

    public Drawing loadDrawing() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Drawing;";

        Cursor c = db.rawQuery(sql, null);

        List<Drawing> draws = new ArrayList<Drawing>();
        Drawing drawing = new Drawing();
        while (c.moveToNext()) {

            drawing.setTopBar(c.getString(c.getColumnIndex("topBar")));
            drawing.setBotBar(c.getString(c.getColumnIndex("botBar")));
            drawing.setTopDash(c.getString(c.getColumnIndex("topDash")));
            drawing.setMidDash(c.getString(c.getColumnIndex("midDash")));
            drawing.setBotDash(c.getString(c.getColumnIndex("botDash")));

            draws.add(drawing);
        }

        c.close();
        return  draws.get(0);
    }
}
