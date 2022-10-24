package com.bostjan.mfc.minesweeper.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "minesweeper.db";
    public static final String TABLE_USER = "user";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CURRENT_LEVEL = "current_level";
    public static final String MINE_PERCENTAGE = "mine_percentage";
    public static final String DRILL = "drill";
    public static final String XRAY = "xray";
    public static final String HEALTH = "health";

    public static final String TABLE_CLASSIC = "classic";
    public static final String CLASSICID = "classicid";
    public static final String NAME = "name";
    public static final String SECUNDS = "secunds";
    public static final String TIME0 = "time";
    public static final String FKUSERID0 = "fkuserid";

    public static final String TABLE_CRAZY = "crazy";
    public static final String CRAZYID = "crazyid";
    public static final String LEVEL = "level";
    public static final String MINEPER = "minePer";
    public static final String SECONDS = "secunds";
    public static final String TIME1 = "time";
    public static final String FKUSERID1 = "fkuserid";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user("
                + "userid integer primary key,"
                + "username varchar2(40),"
                + "password varchar2(40),"
                + "current_level integer,"
                + "mine_percentage integer,"
                + "drill integer,"
                + "xray integer,"
                + "health integer);");

        db.execSQL("CREATE TABLE crazy("
                + "crazyid integer primary key,"
                + "level integer,"
                + "minePer integer,"
                + "secunds integer,"
                + "time varchar2(10),"
                + "fkuserid integer,"
                + "FOREIGN KEY(fkuserid) REFERENCES user(userid));");

        db.execSQL("CREATE TABLE classic("
                + "classicid integer primary key,"
                + "name varchar2(40),"
                + "secunds integer,"
                + "time varchar2(10),"
                + "fkuserid integer,"
                + "FOREIGN KEY(fkuserid) REFERENCES user(userid))");

        System.out.println("create");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table IF EXISTS " + TABLE_USER);

        System.out.println("upgrade");

    }

    public boolean addUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(CURRENT_LEVEL, 0);
        contentValues.put(MINE_PERCENTAGE, 10);
        contentValues.put(DRILL, 3);
        contentValues.put(XRAY, 3);
        contentValues.put(HEALTH, 10);

        long result = db.insert(TABLE_USER, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor displayUsers() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        return res;

    }

    public Cursor displayUser(String username) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + USERNAME + " = '" + username + "';", null);
        return res;

    }

    public Cursor getScore(int userid, String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_CLASSIC + " where fkuserid = " + userid + " and name = '" + name + "';", null);
        return res;

    }

    public void insertScore(int userid, String name, int sec, String time) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(SECUNDS, sec);
        contentValues.put(TIME0, time);
        contentValues.put(FKUSERID0, userid);

        long result = db.insert(TABLE_CLASSIC, null, contentValues);

    }

    public void updateScore(int userid, String name, int sec, String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE classic SET secunds=" + sec + ", time='" + time + "' where fkuserid = " + userid + " and name = '" + name + "';");

    }

    public Cursor getScore(int userid, int level, int minePer) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from crazy where fkuserid = " + userid + " and level = " + level + " and minePer = " + minePer + ";", null);
        return res;

    }

    public void insertScore(int userid, int level, int minePer, int sec, String time) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LEVEL, level);
        contentValues.put(MINEPER, minePer);
        contentValues.put(SECUNDS, sec);
        contentValues.put(TIME1, time);
        contentValues.put(FKUSERID1, userid);

        long result = db.insert(TABLE_CRAZY, null, contentValues);

    }

    public void updateScore(int userid, int level, int minePer, int sec, String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE crazy SET secunds=" + sec + ", time='" + time + "' where fkuserid = " + userid + " and level = " + level + " and minePer = " + minePer + ";");

    }

    public void updatePowerUps(String username, int drill, int xray, int health) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE user SET drill=" + drill + ", xray=" + xray + ", health=" + health + " where username = '" + username + "';");

    }

    public void updateLevel(String username, int level, int minesPercent) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE user SET current_level=" + level + ", mine_percentage=" + minesPercent + " where username='" + username + "';");

    }

    public void changePassword(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE user SET password='" + password + "' WHERE username='" + username + "';");

    }

    public Cursor getMaxLevel(int userid) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(level) as level from crazy where fkuserid = " + userid + ";", null);
        return cursor;

    }

    public Cursor getScore(int userid, int level) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from crazy where fkuserid = " + userid + " and level = " + level + ";", null);
        return cursor;

    }

}
