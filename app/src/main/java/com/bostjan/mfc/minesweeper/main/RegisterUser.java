package com.bostjan.mfc.minesweeper.main;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.bostjan.mfc.minesweeper.game.LevelSaveAndLoad;
import com.bostjan.mfc.minesweeper.sqlite.DatabaseHelper;
import com.bostjan.mfc.minesweeper.sqlite.SQLiteJDBC;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUser {
	
	public static File file = new File("users.txt");
	
	public static boolean registerUser(String username, String password, String passwordConfirm, Context applicationContext) {
		
		//declare local variables
		DatabaseHelper databaseHelper = new DatabaseHelper(applicationContext);
		boolean registerSuccess = false;
		
		try {
			
			//check if user exists
			if (userExists(username, applicationContext)) {
				
				//JOptionPane.showMessageDialog(GameWindow.frame, "user already exists", "Error", JOptionPane.DEFAULT_OPTION);
				Toast.makeText(applicationContext, "User already exists", Toast.LENGTH_SHORT).show();

			}
			else {
				
				//check if passwords match
				if (password.equals(passwordConfirm)) {
					
					databaseHelper.addUser(username, getMD5(password));
					registerSuccess = true;
					
				}
				else
					//JOptionPane.showMessageDialog(GameWindow.frame, "Passwords doesn't match", "Error", JOptionPane.DEFAULT_OPTION);
					Toast.makeText(applicationContext, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
				
			}
			
		} finally {
			
		}
		
		return registerSuccess;
		
	}
	
	public static boolean loginUser(String username, String password, Context applicationContext) {
		
		//declare local variables
		DatabaseHelper databaseHelper = new DatabaseHelper(applicationContext);
		boolean loginSuccess = false;
		boolean wrongPassword = true;

		LevelSaveAndLoad.context = applicationContext;

		Cursor res = databaseHelper.displayUsers();

		//read file
		while (res.moveToNext()) {

            String uName = res.getString(1);
            String passwd = res.getString(2);

            //check if user exists
            if (userExists(username, applicationContext)) {

                //check matching passwords
                if (passwd.equals(getMD5(password))) {

                    loginSuccess = true;
                    wrongPassword = false;

                }

            }
            else {

                //JOptionPane.showMessageDialog(GameWindow.frame, "User doesn't exist", "Error", JOptionPane.DEFAULT_OPTION);
                Toast.makeText(applicationContext, "User doesn't exist", Toast.LENGTH_SHORT).show();

            }

        }

		if (wrongPassword && userExists(username, applicationContext)) {

            //JOptionPane.showMessageDialog(GameWindow.frame, "Wrong password", "Error", JOptionPane.DEFAULT_OPTION);
            Toast.makeText(applicationContext, "Wrong password", Toast.LENGTH_SHORT).show();

        }

		return loginSuccess;
		
	}

	public static boolean userExists(String username, Context applicationContext) {
		
		DatabaseHelper databaseHelper = new DatabaseHelper(applicationContext);

		Cursor res = databaseHelper.displayUsers();

		while (res.moveToNext()) {

            String string = res.getString(1);

            if (string.equals(username)) {

                return true;

            }

        }

		return false;
		
	}
	
	public static void changePassword(String username, String currentPassword, String password, String confirmPassword, Context applicationContext) {
		
		//declare local variables
		DatabaseHelper databaseHelper = new DatabaseHelper(applicationContext);
		boolean loginSuccess = false;
		boolean wrongPassword = true;
		boolean passwordNotMatch = true;

		Cursor cursor = databaseHelper.displayUsers();

		//read file
		while (cursor.moveToNext()) {

            String uName = cursor.getString(1);
            String passwd = cursor.getString(2);

            //check if user exists
            if (userExists(username, applicationContext)) {

                //check matching passwords
                if (passwd.equals(getMD5(currentPassword))) {

                    if (password.equals(confirmPassword)) {

                        databaseHelper.changePassword(username, getMD5(password));
                        passwordNotMatch = false;
                        System.out.println("password changed");

                    }

                    loginSuccess = true;
                    wrongPassword = false;

                }

            }
            else {

                System.out.println("user doesn't exists");

            }

        }

		if (wrongPassword) {

            //JOptionPane.showMessageDialog(GameWindow.frame, "Wrong Password", "Error", JOptionPane.DEFAULT_OPTION);
            Toast.makeText(applicationContext, "Wrong Password", Toast.LENGTH_SHORT).show();

        }
        else if (passwordNotMatch) {

            //JOptionPane.showMessageDialog(GameWindow.frame, "Passwords don't match", "Error", JOptionPane.DEFAULT_OPTION);
            Toast.makeText(applicationContext, "Passwords don't match", Toast.LENGTH_SHORT).show();

        }

	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
}
