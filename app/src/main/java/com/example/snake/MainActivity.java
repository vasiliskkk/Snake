package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static ImageView img_swipe;
    public static Dialog dialogScore;
    private GameView gv;
    private GameViewWithObstacles gvwo;
    public static TextView txt_score, txt_best_score, txt_dialog_score, txt_dialog_best_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.main_menu);

        setupMainMenuButtons();
    }

    private void setupMainMenuButtons() {
        Button buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.game_mode_selection);
                setupGameModeSelectionButtons();
            }
        });

        Button buttonExit = findViewById(R.id.exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupGameModeSelectionButtons() {
        Button buttonMenu = findViewById(R.id.menu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.main_menu);
                setupMainMenuButtons();
            }
        });

        Button buttonOrdinary = findViewById(R.id.button_ordinary);
        buttonOrdinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGameScreen();
            }
        });

        Button buttonObstacleCourse = findViewById(R.id.obstacle_course_mode);
        buttonObstacleCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOCModeScreen();
            }
        });
    }

    private void loadGameScreen() {
        setContentView(R.layout.activity_main);
        img_swipe = findViewById(R.id.img_swipe);
        gv = findViewById(R.id.gv);
        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_best_score);
        dialogScore();
    }

    private void loadGameWOScreen() {
        setContentView(R.layout.obstacle_course_mode);
        img_swipe = findViewById(R.id.img_swipe);
        gvwo = findViewById(R.id.gvwo);
        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_best_score);
        gvwodialogScore();
    }

    private void loadOCModeScreen() {
        loadGameWOScreen();
    }

    private void dialogScore() {
        int bestScore = 0;
        SharedPreferences sp = this.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
        if (sp != null) {
            bestScore = sp.getInt("bestscore", 0);
        }
        MainActivity.txt_best_score.setText(bestScore + "");
        dialogScore = new Dialog(this);
        dialogScore.setContentView(R.layout.dialog_start);
        txt_dialog_score = dialogScore.findViewById(R.id.txt_dialog_score);
        txt_dialog_best_score = dialogScore.findViewById(R.id.txt_dialog_best_score);
        txt_dialog_best_score.setText(bestScore + "");
        dialogScore.setCanceledOnTouchOutside(false);
        RelativeLayout rl_start = dialogScore.findViewById(R.id.rl_start);
        rl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_swipe.setVisibility(View.VISIBLE);
                gv.reset();
                dialogScore.dismiss();
            }
        });
        RelativeLayout rl_menu = dialogScore.findViewById(R.id.rl_menu);
        rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogScore.dismiss();
                setContentView(R.layout.main_menu);
                setupMainMenuButtons();
            }
        });

        RelativeLayout rl_exit = dialogScore.findViewById(R.id.rl_exit);
        rl_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialogScore.show();
    }

    private void gvwodialogScore() {
        int bestScore = 0;
        SharedPreferences sp = this.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
        if (sp != null) {
            bestScore = sp.getInt("bestscore", 0);
        }
        MainActivity.txt_best_score.setText(bestScore + "");
        dialogScore = new Dialog(this);
        dialogScore.setContentView(R.layout.dialog_start);
        txt_dialog_score = dialogScore.findViewById(R.id.txt_dialog_score);
        txt_dialog_best_score = dialogScore.findViewById(R.id.txt_dialog_best_score);
        txt_dialog_best_score.setText(bestScore + "");
        dialogScore.setCanceledOnTouchOutside(false);
        RelativeLayout rl_start = dialogScore.findViewById(R.id.rl_start);
        rl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_swipe.setVisibility(View.VISIBLE);
                gvwo.reset();
                dialogScore.dismiss();
            }
        });
        RelativeLayout rl_menu = dialogScore.findViewById(R.id.rl_menu);
        rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogScore.dismiss();
                setContentView(R.layout.main_menu);
                setupMainMenuButtons();
            }
        });

        RelativeLayout rl_exit = dialogScore.findViewById(R.id.rl_exit);
        rl_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialogScore.show();
    }
}