package studiopaye.frcscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public class ScoutingLogActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TeamAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Bundle extras;
    private boolean noback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scouting_log);
        noback = false;

        User.loadData(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true); //true so long as it doesn't update here
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TeamAdapter(User.teamList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                Intent intent = new Intent(ScoutingLogActivity.this, TeamDetailsActivity.class);
                intent.putExtra("teamnum", User.teamList.get(position).getTeamNum());
                intent.putExtra("arraypos", position);
                startActivity(intent);
            }
        });
        extras = getIntent().getExtras();

        if(extras!=null){
            if(extras.getBoolean("noback")){
                noback = true;
            }
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }
}
