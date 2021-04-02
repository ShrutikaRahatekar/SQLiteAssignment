package com.example.sqlite_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname = "TblStudent";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table TblStudent (sno integer primary key autoincrement, sname text, sage integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TblStudent");
        onCreate(db);
    }
    //insert
    public boolean insert(String std_name, String std_age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sname", std_name);
        cv.put("sage", std_age);
        long r = db.insert("tblStudent", "null", cv);
        return r == -1 ? false : true;
    }
    public Cursor display(int stdno) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from TblStudent where sno = " + stdno, null);
        return cr;
    }
    public Cursor DisplayAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from TblStudent", null);
        return cr;
    }

    public boolean update(int ud_sno, String ud_sname, int ud_sage) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sname", ud_sname);
        cv.put("sage", ud_sage);
        Cursor cr = db.rawQuery("select * from TblStudent where sno = " + ud_sno, null);
        if (cr.getCount() > 0) {
            long r = db.update("tblStudent", cv, "sno=?", new String[]{String.valueOf(ud_sno)});
            return r == -1 ? false : true;
        } else {
            return false;
        }
    }

    public boolean delete(int ud_sno) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from TblStudent where sno = " + ud_sno, null);
        if (cr.getCount() > 0) {
            long r = db.delete("tblStudent", "sno = ?", new String[]{String.valueOf(ud_sno)});
            return r == -1 ? false : true;
        } else {
            return false;
        }
    }
}
