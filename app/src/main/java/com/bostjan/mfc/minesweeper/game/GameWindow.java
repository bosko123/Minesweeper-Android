package com.bostjan.mfc.minesweeper.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bostjan.mfc.minesweeper.R;
import com.bostjan.mfc.minesweeper.UserSettingsWindow;
import com.bostjan.mfc.minesweeper.assets_settings.ImageRegister;
import com.bostjan.mfc.minesweeper.main.MainWindow;
import com.bostjan.mfc.minesweeper.power_ups.PowerUpsEvents;

import java.util.concurrent.ThreadLocalRandom;

public class GameWindow extends AppCompatActivity {

    protected ConstraintLayout grid;

    protected Button btnStart;
    protected static Button btnReset;

    protected int t = 0;

    private static final long serialVersionUID = -2025052403489910012L;
    private ConstraintLayout  contentPane;
    private Runnable openTiles = new open();
    private Thread thread0 = new Thread(openTiles);
    private Runnable runningTimer = new timer();
    private Thread thread1 = new Thread(runningTimer);
    private Runnable openerCrate = new openCreate();
    private Thread thread2 = new Thread(openerCrate);
    private Runnable empty = new xRayCooldown();
    private Thread thread3 = new Thread(empty);

    public static int health = 10;
    public static boolean pressed = false;

    public static int level = 0;
    public static int minePercentage = 10;
    public static int sizeX = 10;
    public static int sizeY = 10;

    public static int currentSizeX = 0;
    public static int currentSizeY = 0;
    public static int mines = 0;

    public static int timeInSec = -1;
    public static int secunds = -1;
    public static int minutes = 0;
    public static int hours = 0;

    public static int[] allPowerUps = {3, 3};
    public static boolean drillSelected = false;
    public static boolean xraySelected = false;
    public static boolean xrayActive = false;

    public static GameWindow frame = new GameWindow();

    public static int frames = 1;
    public static int uses = 0;
    public static int time = 0;

    public static ImageView[][] tiles = null;
    public static boolean gameOver = false;
    public static boolean start = false;
    public static boolean openingCrate = false;
    public static double width = 0;
    public static double height = 0;
    private ConstraintLayout  pnlGrid;
    public static TextView lblTimer;
    public static EditText txtRows;
    public static EditText txtCols;
    public static EditText txtMines;
    public static TextView lblMines;
    public static boolean crazyModeOn = false;
    private ConstraintLayout  panel_1;
    public static TextView lblMines_1;
    public static TextView lblRow;
    public static TextView lblColumns;
    private ConstraintLayout  panel_2;
    private ConstraintLayout  panel_3;
    public static TextView lblDrillsCount;
    public static Button btnOpen;
    public static ImageView lblCrate;
    public static ImageView lblPowerup;
    public static TextView lblXRaysCount;
    public static ImageView lblDrill;
    public static ImageView lblXRay;
    private ConstraintLayout  panel_4;
    public static TextView lblHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(GameWindow.this, MainWindow.class);
                startActivity(intent);
            }
        });

        Button btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(GameWindow.this, UserSettingsWindow.class);
                startActivity(intent);
            }
        });

        ImageRegister.imageLoader();

        grid = findViewById(R.id.grid);

        lblMines = findViewById(R.id.lblMines);
        lblTimer = findViewById(R.id.lblTimer);

        txtMines = findViewById(R.id.txtMines);
        txtRows = findViewById(R.id.txtRows);
        txtCols = findViewById(R.id.txtCols);

        lblHealth = findViewById(R.id.lblHealth);
        lblHealth.setText("Lives " + health + "/10");

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setVisibility(View.INVISIBLE);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnOpen.getText().equals("Click To Open")) {

                    PowerUpsEvents.getPowerUp();
                    try {

                        thread2.start();

                    } catch (Exception e) {

                    }

                }
                else {

                    openingCrate = false;
                    frames = 1;

                }

            }
        });

        lblCrate = findViewById(R.id.lblCrate);
        lblCrate.setVisibility(View.INVISIBLE);

        lblPowerup = findViewById(R.id.lblPowerup);
        lblPowerup.setVisibility(View.INVISIBLE);

        lblDrill = findViewById(R.id.lblDrill);
        lblDrill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (crazyModeOn && allPowerUps[0] > 0) {

                    //set how much tiles can drill open
                    int stTiles = tiles.length * tiles[0].length;
                    double percentageUse = Math.round((stTiles * PowerUpsEvents.DRILL_USES) / 100);
                    PowerUpsEvents.drill.setUses((int)percentageUse);

                    drillSelected = true;
                    uses = PowerUpsEvents.drill.getUses();
                    allPowerUps[0]--;
                    lblDrillsCount.setText(allPowerUps[0] + "");

                }

            }
        });
        /*lblXRay = findViewById(R.id.lblXRay);
        lblXRay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (crazyModeOn && allPowerUps[1] > 0) {

                    //set how time will drill last
                    int stTiles = tiles.length * tiles[0].length;
                    double percentageTime = Math.round((stTiles * PowerUpsEvents.XRAY_TIME) / 100);
                    PowerUpsEvents.xRay.setTime((int)percentageTime);

                    xraySelected = true;
                    time = PowerUpsEvents.xRay.getTime();
                    allPowerUps[1]--;
                    lblXRaysCount.setText(allPowerUps[1] + "");

                }

            }
        });*/

        lblDrillsCount = findViewById(R.id.txtDrillCount);
        //lblXRaysCount = findViewById(R.id.txtXRayCount);

        if (!crazyModeOn) {

            GameWindow.lblDrill.setVisibility(View.INVISIBLE);
            GameWindow.lblDrillsCount.setVisibility(View.INVISIBLE);
            //GameWindow.lblXRay.setVisibility(View.INVISIBLE);
            //GameWindow.lblXRaysCount.setVisibility(View.INVISIBLE);
            GameWindow.lblHealth.setVisibility(View.INVISIBLE);

        }
        else {

            GameWindow.txtMines.setVisibility(View.INVISIBLE);
            GameWindow.txtRows.setVisibility(View.INVISIBLE);
            GameWindow.txtCols.setVisibility(View.INVISIBLE);

        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)grid.getLayoutParams();
        layoutParams.width = size.x - 100;
        layoutParams.height = size.x - 100;
        grid.setLayoutParams(layoutParams);

        btnReset = findViewById(R.id.btnReset);
        btnReset.setEnabled(false);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                EventHandler.reset();
                startActivity(getIntent());
            }
        });

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnStart.setEnabled(false);
                btnReset.setEnabled(true);

                //set tile grid size
                if (!crazyModeOn) {

                    currentSizeX = Integer.parseInt(txtRows.getText() + "");
                    currentSizeY = Integer.parseInt(txtCols.getText() + "");
                    mines = Integer.parseInt(txtMines.getText() + "");
                    tiles = new ImageView[currentSizeY][currentSizeX];
                    EventHandler.stMines = Integer.parseInt(txtMines.getText() + "");

                }
                else {

                    int x = sizeX + level;
                    int y = sizeY + level;
                    tiles = new ImageView[x][y];
                    EventHandler.stMines = Math.round(((x * y) * minePercentage) / 100);
                    System.out.println(minePercentage);

                }

                //define grid tiles rows and columns
                double rows = tiles.length;
                double cols = tiles[tiles.length-1].length;

                //set tile size
                width = Math.round(grid.getWidth() / rows);
                height = Math.round(grid.getHeight() / cols);

                System.out.println(width);
                System.out.println(height);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)grid.getLayoutParams();
                layoutParams.width = (int)(width * rows);
                layoutParams.height = (int)(height * cols);
                grid.setLayoutParams(layoutParams);

                //set offset
                /*int offsetX = Responsive.getOffsetX((int)rows, pnlGrid.getWidth(), (int)width);
                int offsetY = Responsive.getOffsetY((int)cols, pnlGrid.getHeight(), (int)height);*/

                //define image as null
                int image = -1;

                //get image and set it's size
                image = ImageRegister.flagImages[0];

                //prepare game (generate mines, flags, crates)
                EventHandler.generateMines();
                EventHandler.rightFlags();

                if (crazyModeOn)
                    EventHandler.crazyMode();

                lblDrillsCount.setText(allPowerUps[0] + "");
                //lblXRaysCount.setText(allPowerUps[1] + "");

                //start timers/threads
                thread0.start();
                thread1.start();
                //thread3.start();

                lblMines.setText(EventHandler.stMines + "");

                //place nukes if got before
                int nukes = PowerUpsEvents.xNukes;

					/*while (nukes != 0) {

						RandomPowerUp.placeNuke();

						nukes--;

					}*/

                //place tiles
                for (int i = 0; i < tiles.length; i++) {

                    for (int j = 0; j < tiles[i].length; j++) {

                        tiles[i][j] = new ImageView(getApplicationContext());
                        final ImageView tile = tiles[i][j];

                        tile.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                if (!gameOver) {

                                    EventHandler.placeFlags(tile, getApplicationContext());

                                }

                            }
                        });

                        tile.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {

                                if (!gameOver && !openingCrate) {

                                    //drill selected?
                                    if (!drillSelected) {

                                        EventHandler.openTile(tile, getApplicationContext());

                                    }
                                    else {

                                        EventHandler.breakTile(tile, getApplicationContext());

                                    }

                                }

                                return false;
                            }
                        });

                        /*tile.setOnHoverListener(new View.OnHoverListener() {
                            @Override
                            public boolean onHover(View v, MotionEvent event) {

                                if (xraySelected)
                                    EventHandler.xray(tile);

                                return false;
                            }
                        });*/

                        /*tile.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {

                                if (xrayActive)
                                    EventHandler.xray(tile);

                                return true;
                            }
                        });*/

                        int x = i * (int)width;
                        int y = j * (int)height;
                        tile.setX(x);
                        tile.setY(y);
                        tile.setLayoutParams(new ConstraintLayout.LayoutParams((int)width, (int)height));
                        tile.setImageResource(image);
                        tile.setScaleType(ImageView.ScaleType.FIT_XY);
                        tile.setId(i);
                        grid.addView(tiles[i][j]);

                    }

                }

                start = true;

            }
        });

    }

    public void updateDisplay() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTimer.setText(EventHandler.time);
            }
        });

    }

    public void updateImages(final ImageView imageView, final int id) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(id);
            }
        });

    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public void visible(final View view) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
            }
        });

    }

    public void invisible(final View view) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void setText(final Button view, final String text) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setText(text);
            }
        });

    }

    public void setText(final TextView view, final String text) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setText(text);
            }
        });

    }

    /*public void playExplosion() {

        MediaPlayer ring = MediaPlayer.create(GameWindow.this, R.raw.explosion);
        ring.start();

    }*/

    private class open implements Runnable {

        @Override
        public void run() {

            while (!gameOver) {

                if (!openingCrate) {

                    //EventHandler.openTiles();

                }
                if (openingCrate) {

                    if (frames == 1 || frames == 60)
                        visible(btnOpen);
                    else
                        invisible(btnOpen);
                    if (frames < 60)
                        setText(btnOpen, "Click To Open");
                    else if (frames == 60) {
                        setText(btnOpen, "OK");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lblPowerup.setImageResource(PowerUpsEvents.powerupImage);
                            }
                        });
                        visible(lblPowerup);
                    }
                    visible(lblCrate);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lblCrate.setImageResource(ImageRegister.crate[frames-1]);
                        }
                    });

                }else {

                    invisible(btnOpen);
                    invisible(lblCrate);
                    invisible(lblPowerup);

                }

                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private class timer implements Runnable {
        @Override
        public void run() {

            while (!gameOver) {

                EventHandler.timer();
                updateDisplay();

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        }
    }

    private class openCreate implements Runnable {

        @Override
        public void run() {

            while (!gameOver) {

                try {

                    //System.out.println(frames);
                    Thread.sleep(60);
                    //GameWindow.lblCrate.setIcon(new ImageIcon(GameWindow.class.getResource("/assets/textures/gui/create_icons/crate (" + frames + ").png")));
                    //openCrate = false;

                    if (frames == 60) {

                        //thread2.stop();

                    }
                    else {

                        frames++;

                    }

                    //thread2.stop();

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        }

    }

    private class xRayCooldown implements Runnable {
        @Override
        public void run() {

            while (!gameOver) {

                EventHandler.xRayCooldown();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
