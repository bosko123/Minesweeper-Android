package com.bostjan.mfc.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bostjan.mfc.minesweeper.game.GameWindow;

public class GameModeWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_window);

        Button btnClassic = findViewById(R.id.btnClassic);
        btnClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameWindow.crazyModeOn = false;
                finish();
                Intent intent = new Intent(GameModeWindow.this, GameWindow.class);
                startActivity(intent);

            }
        });

        Button btnCrazy = findViewById(R.id.btnCrazy);
        btnCrazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameWindow.crazyModeOn = true;
                finish();
                Intent intent = new Intent(GameModeWindow.this, GameWindow.class);
                startActivity(intent);

            }
        });

    }
}
