package com.bostjan.mfc.minesweeper.game;

import android.content.Context;
import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bostjan.mfc.minesweeper.sqlite.DatabaseHelper;
import com.bostjan.mfc.minesweeper.sqlite.SQLiteJDBC;

public class LevelSaveAndLoad {

    public static Context context;

	public static void loadLevel(String username) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);

		Cursor cursor = databaseHelper.displayUser(username);

		while (cursor.moveToNext()) {

			GameWindow.level = cursor.getInt(3);
			GameWindow.minePercentage = cursor.getInt(4);

		}

	}

	public static void loadPowerUps(String username) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);

		Cursor cursor = databaseHelper.displayUser(username);

		while (cursor.moveToNext()) {

			GameWindow.allPowerUps[0] = cursor.getInt(5);
			GameWindow.allPowerUps[1] = cursor.getInt(6);
			GameWindow.health = cursor.getInt(7);

		}

	}

	public static void saveLevel(String username) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);

		databaseHelper.updateLevel(username, GameWindow.level, GameWindow.minePercentage);

	}

	public static void savePowerUps(String username) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);

		databaseHelper.updatePowerUps(username, GameWindow.allPowerUps[0], GameWindow.allPowerUps[1], GameWindow.health);
		System.out.println("powerups saved");

	}

	public static void saveBestScoreClassic(String username) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);

		String name = GameWindow.mines + "_" + GameWindow.currentSizeX + "x" + GameWindow.currentSizeY;
		Cursor res = databaseHelper.displayUser(username);
		res.moveToFirst();
		int userid = res.getInt(0);
		Cursor cursor = databaseHelper.getScore(userid, name);

		if (cursor.getCount() == 0) {

			databaseHelper.insertScore(userid, name, GameWindow.timeInSec, EventHandler.time);
			return;

		}

		cursor.moveToFirst();
		if (cursor.getInt(2) > GameWindow.timeInSec) {

			databaseHelper.updateScore(userid, name, GameWindow.timeInSec, EventHandler.time);

		}

	}

	public static void saveBestScoreCrazy(String username) {

		DatabaseHelper databaseHelper = new DatabaseHelper(context);

		Cursor res = databaseHelper.displayUser(username);
		res.moveToFirst();
        int userid = res.getInt(0);
        Cursor cursor = databaseHelper.getScore(userid, GameWindow.level, GameWindow.minePercentage);

		if (cursor.getCount() == 0) {

			databaseHelper.insertScore(userid, GameWindow.level, GameWindow.minePercentage, GameWindow.timeInSec, EventHandler.time);
			return;

		}

		cursor.moveToFirst();
		if (databaseHelper.getScore(userid, GameWindow.level, GameWindow.minePercentage).getInt(3) > GameWindow.timeInSec) {

			databaseHelper.updateScore(userid, GameWindow.level, GameWindow.minePercentage, GameWindow.timeInSec, EventHandler.time);

		}

    }

}
