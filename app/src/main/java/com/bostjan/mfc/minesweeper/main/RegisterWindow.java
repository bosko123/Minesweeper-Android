package com.bostjan.mfc.minesweeper.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bostjan.mfc.minesweeper.R;

public class RegisterWindow extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private static Button btnRegister;
    private static Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_window);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RegisterUser.registerUser(txtUsername.getText() + "", txtPassword.getText() + "", txtConfirmPassword.getText() + "", getApplicationContext())) {

                    finish();
                    Intent intent = new Intent(RegisterWindow.this, MainWindow.class);
                    startActivity(intent);

                }
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(RegisterWindow.this, MainWindow.class);
                startActivity(intent);
            }
        });

    }
}
