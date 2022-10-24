package com.bostjan.mfc.minesweeper.main;

import android.Manifest;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bostjan.mfc.minesweeper.GameModeWindow;
import com.bostjan.mfc.minesweeper.R;
import com.bostjan.mfc.minesweeper.game.GameWindow;
import com.bostjan.mfc.minesweeper.game.LevelSaveAndLoad;
import com.bostjan.mfc.minesweeper.sqlite.DatabaseHelper;

public class MainWindow extends AppCompatActivity {

    public static String fontPath = "/assets/pixle_font.ttf";
    public static String explosionSoundPath = "src/resources/sound/explosion.wav";

    private ConstraintLayout contentPane;
    private EditText txtUsername;
    private static Button btnLogin;
    private static Button btnRegister;
    private EditText textField;
    private EditText pwdPassword;

    public static String username;
    public static String password;
    private TextView lblPassword;
    private TextView lblUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        pwdPassword = findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = txtUsername.getText() + "";
                password = pwdPassword.getText() + "";

                if (RegisterUser.loginUser(username, password, getApplicationContext())) {

                    LevelSaveAndLoad.loadLevel(username);
                    LevelSaveAndLoad.loadPowerUps(username);
                    finish();
                    Intent intent = new Intent(MainWindow.this, GameModeWindow.class);
                    startActivity(intent);

                }

            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent intent = new Intent(MainWindow.this, RegisterWindow.class);
                startActivity(intent);

            }
        });

    }
}
