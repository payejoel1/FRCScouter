package studiopaye.frcscouter;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ScoutingPrefaceActivity extends AppCompatActivity {
    String teamcolor = "Blue";
    EditText teamnum;
    String teamnumText;
    EditText matchnum;
    String matchnumText;
    Button alliancebtn;

    public void goto_activity_scouting(View view){
        Intent intent = new Intent(ScoutingPrefaceActivity.this, ScoutingActivity.class);
        intent.putExtra("teamnum", teamnumText);
        intent.putExtra("teamcolor", teamcolor);
        intent.putExtra("matchnum", matchnumText);
        startActivity(intent);
    }

    public void init_elements(){
        alliancebtn = (Button)findViewById(R.id.alliance_toggle);
        alliancebtn.setTextColor(ContextCompat.getColor(this,R.color.mywhite));
        alliancebtn.setBackgroundColor(ContextCompat.getColor(this,R.color.myblue));
        alliancebtn.setText("Blue Side");
        teamnum = (EditText)findViewById(R.id.team_num);
        matchnum = (EditText)findViewById(R.id.match_num);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scouting_preface);
        init_elements();
    }

    public void toggle_alliance(View view){
        if(teamcolor =="Blue"){
            teamcolor ="Red";
            alliancebtn.setBackgroundColor(ContextCompat.getColor(this,R.color.myred));
            alliancebtn.setText("Red Side");
        }
        else if(teamcolor =="Red"){
            teamcolor ="Blue";
            alliancebtn.setBackgroundColor(ContextCompat.getColor(this,R.color.myblue));
            alliancebtn.setText("Blue Side");
        }
    }

    public void enter_team(View view){
        Random r = new Random();
        teamnumText = teamnum.getText().toString();
        matchnumText = matchnum.getText().toString();
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP|Gravity.LEFT,0,0);
        if(TextUtils.isEmpty(teamnumText)){
            toast.makeText(ScoutingPrefaceActivity.this,"No team number entered",toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(matchnumText)){
            toast.makeText(ScoutingPrefaceActivity.this,"No match number entered",toast.LENGTH_SHORT).show();
        }else{
            //pass all this info to a new activity with intent
            boolean old = false;
            User.loadData(this);
            for(Team curTeam : User.teamList){
                if(teamnumText.equals(curTeam.getTeamNum())){
                    old = true;
                    curTeam.addMatch(new Match(r.nextInt(80-65)+65,teamcolor,matchnumText));
                    toast.makeText(ScoutingPrefaceActivity.this,"Match "+matchnumText+" added to team "+teamnumText,toast.LENGTH_SHORT).show();
                }
            }
            if(old == false){
                User.addTeam(teamnumText);
                toast.makeText(ScoutingPrefaceActivity.this,"Team "+teamnumText+" added",toast.LENGTH_SHORT).show();
                for(Team curTeam : User.teamList){
                    if(teamnumText.equals(curTeam.getTeamNum())){
                        curTeam.addMatch(new Match(r.nextInt(80-65)+65,teamcolor,matchnumText));
                        toast.makeText(ScoutingPrefaceActivity.this,"Match "+matchnumText+" added to team "+teamnumText,toast.LENGTH_SHORT).show();
                    }
                }
            }
            User.saveData(this);
            goto_activity_scouting(view);
            //toast.makeText(ScoutingPrefaceActivity.this,"Scouting team "+teamnumText+", playing in match "+matchnumText,toast.LENGTH_SHORT).show();
        }
    }
}
