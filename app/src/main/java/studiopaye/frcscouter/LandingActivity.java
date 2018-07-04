package studiopaye.frcscouter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

public class LandingActivity extends AppCompatActivity{
    public final static String mainfile = "mainfile.txt";

    public void goto_scouting_preface(View view){
        Intent intent = new Intent(this, ScoutingPrefaceActivity.class);
        startActivity(intent);
    }

    public void goto_scouting_log(View view){
        Intent intent = new Intent(this, ScoutingLogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);
    }
}
