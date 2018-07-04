package studiopaye.frcscouter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joel Paye on 12/30/2017.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ExampleViewHolder>{
    private ArrayList<Team> mTeamList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTeamNum;
        public TextView mMatchNum;
        public TextView mPntAvg;
        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTeamNum = itemView.findViewById(R.id.cardteamnum);
            mMatchNum = itemView.findViewById(R.id.cardmatchnum);
            mPntAvg = itemView.findViewById(R.id.cardpnt);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public TeamAdapter(ArrayList<Team> teamList){
        mTeamList = teamList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_card, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Team currentTeam = mTeamList.get(position);
        holder.mTeamNum.setText(currentTeam.getTeamNum());
        holder.mMatchNum.setText(String.valueOf(currentTeam.getMatchList().size()));
        holder.mPntAvg.setText(String.valueOf(currentTeam.getPntAvg()));
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }
}
