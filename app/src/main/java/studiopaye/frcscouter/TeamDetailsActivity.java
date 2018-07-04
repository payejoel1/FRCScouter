package studiopaye.frcscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class TeamDetailsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MatchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String headerString;
    private String matchesString;
    private String scoreString;
    private TextView mHeader;
    private TextView mMatches;
    private TextView mScoreAvg;
    private Team curTeam;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_team_details);

        mRecyclerView = findViewById(R.id.MatchrecyclerView);
        mRecyclerView.setHasFixedSize(true); //true so long as it doesn't update here
        mLayoutManager = new LinearLayoutManager(this);
        mHeader = findViewById(R.id.teamdetailheader);
        mMatches = findViewById(R.id.cardmatchnum2);
        mScoreAvg = findViewById(R.id.teampnt);

        extras = getIntent().getExtras();

        if(extras!=null){
            curTeam = User.teamList.get(extras.getInt("arraypos"));
            mAdapter = new MatchAdapter(curTeam.getMatchList());
            headerString = extras.getString("teamnum");
            matchesString = String.valueOf(curTeam.getMatchList().size());
            scoreString = String.valueOf(curTeam.getPntAvg());
            mHeader.setText(headerString +" Overview");
            mMatches.setText(matchesString);
            mScoreAvg.setText(scoreString);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MatchAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                //Intent intent = new Intent(ScoutingLogActivity.this, TeamDetailsActivity.class);
                //intent.putExtra("teamnum", User.teamList.get(position).getTeamNum());
                //intent.putExtra("matchlist", User.teamList.get(position).getMatchList());
                //startActivity(intent);
            }
        });
    }

    public void delete_team(View view){
        Intent intent = new Intent(TeamDetailsActivity.this, ScoutingLogActivity.class);
        intent.putExtra("noback",true);
        User.teamList.remove(extras.getInt("arraypos"));
        User.saveData(this);
        //finish();
        startActivity(intent);
    }
}
