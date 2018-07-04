package studiopaye.frcscouter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joel Paye on 12/31/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ExampleViewHolder> {
    private ArrayList<Match> mMatchList;
    private MatchAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MatchAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mMatchNum;
        public TextView mPnt;
        public RelativeLayout mlayout;
        public ExampleViewHolder(View itemView, final MatchAdapter.OnItemClickListener listener) {
            super(itemView);
            mMatchNum = itemView.findViewById(R.id.matchnumber);
            mPnt = itemView.findViewById(R.id.scorenumber);

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

    public MatchAdapter(ArrayList<Match> matchList){
        mMatchList = matchList;
    }

    @Override
    public MatchAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        MatchAdapter.ExampleViewHolder evh = new MatchAdapter.ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(MatchAdapter.ExampleViewHolder holder, int position) {
        Match currentMatch = mMatchList.get(position);
        holder.mMatchNum.setText(String.valueOf(currentMatch.getMatchnum()));
        holder.mPnt.setText(String.valueOf(currentMatch.getScore()));
        if(currentMatch.getSide()=="Blue"){
            holder.mlayout.setBackgroundColor(0x0e84ce);
        }else if(currentMatch.getSide()=="Red"){
            holder.mlayout.setBackgroundColor(0xff5050);
        }
    }

    @Override
    public int getItemCount() {
        return mMatchList.size();
    }
}
