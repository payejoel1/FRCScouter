package studiopaye.frcscouter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ScoutingActivity extends AppCompatActivity {
    private Bundle extras;
    private String curTeam;
    private String matchNum;
    private String sideColor;
    private boolean hasCube = false;
    private int cubesScored = 0;
    private int boostCubes = 0;
    private int forceCubes = 0;
    private int levitateCubes = 0;
    private int estimatedpoint = 0;
    private int gameClock = 150;
    private CountDownTimer timer = new CountDownTimer(1000,20){
        @Override
        public void onTick(long millisUntilFinished){

        }
        @Override
        public void onFinish(){
            try{
                clockPulse();
            }catch(Exception e){
                Log.e("Error","Error: "+e.toString());
            }
        }
    }.start();
    private CountDownTimer update = new CountDownTimer(100,20) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            try{
                updatePulse();
            }catch(Exception e){
                Log.e("Error","Error: "+e.toString());
            }
        }
    }.start();

    private Button cubeButton;
    private TextView boostText;
    private TextView forceText;
    private TextView levitateText;
    private TextView timeText;
    private TextView cubecontrolText;
    private TextView pntcontriText;

    public void init_elements(){
        cubeButton = (Button)findViewById(R.id.cubecontrol);
        boostText = (TextView)findViewById(R.id.boost);
        forceText = (TextView)findViewById(R.id.force);
        levitateText = (TextView)findViewById(R.id.levitate);
        timeText = (TextView)findViewById(R.id.time);
        cubecontrolText = (TextView)findViewById(R.id.cubesscored);
        pntcontriText = (TextView)findViewById(R.id.estpntcontri);
    }

    public void toggleCube(View view){
        if(hasCube){
            dropCube(view);
        }else{
            getCube(view);
        }
    }
    public void getCube(View view){
        hasCube = true;
        cubeButton.setBackground(this.getResources().getDrawable(R.drawable.cubeactivated));
    }
    public void dropCube(View view){
        hasCube = false;
        cubeButton.setBackground(this.getResources().getDrawable(R.drawable.cubedeactivated));
    }
    public void scoreCube(View view){
        if(hasCube){
            dropCube(view);
            cubesScored++;
        }
    }

    public void incrementBoost(View view){
        if(boostCubes < 3 && hasCube){
            boostCubes++;
            estimatedpoint = estimatedpoint + 5;
            dropCube(view);
        }
    }
    public void incrementForce(View view){
        if(forceCubes < 3 && hasCube){
            forceCubes++;
            estimatedpoint = estimatedpoint + 5;
            dropCube(view);
        }
    }
    public void incrementLevitate(View view){
        if(levitateCubes < 3 && hasCube){
            levitateCubes++;
            estimatedpoint = estimatedpoint + 5;
            dropCube(view);
        }
    }

    public void clockPulse(){
        if(gameClock!=0){gameClock--;}
        timer.start();
    }

    public void updatePulse(){
        timeText.setText("Time: "+gameClock);
        boostText.setText("Boost x "+boostCubes);
        forceText.setText("Force x "+forceCubes);
        levitateText.setText("Levitate x "+levitateCubes);
        cubecontrolText.setText("Cube Control: "+cubesScored);
        pntcontriText.setText("Points: "+estimatedpoint);
        update.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        init_elements();

        extras = getIntent().getExtras();

        if(extras!=null) {
            curTeam = extras.getString("teamnum");
            matchNum = extras.getString("matchnum");
            sideColor = extras.getString("teamcolor");
        }
    }
}
