package com.bostjan.mfc.minesweeper;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bostjan.mfc.minesweeper.game.EventHandler;
import com.bostjan.mfc.minesweeper.game.GameWindow;
import com.bostjan.mfc.minesweeper.main.MainWindow;
import com.bostjan.mfc.minesweeper.main.RegisterUser;
import com.bostjan.mfc.minesweeper.sqlite.DatabaseHelper;

public class UserSettingsWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_window);

        final EditText txtCurrentPassword = findViewById(R.id.txtCurrentPassword);
        final EditText txtPassword = findViewById(R.id.txtPassword);
        final EditText txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        Button btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser.changePassword(MainWindow.username, txtCurrentPassword.getText() + "", txtPassword.getText() + "", txtConfirmPassword.getText() + "", getApplicationContext());
            }
        });

        final EditText txtMines = findViewById(R.id.txtMines);
        final EditText txtRows = findViewById(R.id.txtRows);
        final EditText txtColumns = findViewById(R.id.txtColumns);
        final TextView txtTime = findViewById(R.id.lblHrsminsec);
        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                String name = GameWindow.mines + "_" + GameWindow.currentSizeX + "x" + GameWindow.currentSizeY;
                Cursor res = databaseHelper.displayUser(MainWindow.username);
                res.moveToFirst();
                int userid = res.getInt(0);
                Cursor cursor = databaseHelper.getScore(userid, name);
                cursor.moveToFirst();

                String time = cursor.getString(3);
                txtTime.setText("Time: " + time);

            }
        });

        final TextView txtLevel = findViewById(R.id.txtLevel);
        final TextView txtSubLevel = findViewById(R.id.txtSubLevel);
        final TextView txtHrsminsec_1 = findViewById(R.id.txtHrsminsec_1);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                Cursor cuid = databaseHelper.displayUser(MainWindow.username);
                cuid.moveToFirst();
                int userid = cuid.getInt(0);

                Cursor cmlvl = databaseHelper.getMaxLevel(userid);
                cmlvl.moveToFirst();
                int maxLevel = cmlvl.getInt(0);

                Cursor cursor = databaseHelper.getScore(userid, maxLevel);
                cursor.moveToFirst();
                txtLevel.setText("Level: " + (cursor.getInt(1) + 1));
                txtSubLevel.setText("Mines: " + cursor.getInt(2) + "%");
                txtHrsminsec_1.setText("Time: " + cursor.getString(4));

            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(UserSettingsWindow.this, GameWindow.class);
                startActivity(intent);
            }
        });

    }
}
