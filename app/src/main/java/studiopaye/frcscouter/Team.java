package studiopaye.frcscouter;

import java.util.ArrayList;

/**
 * Created by Joel Paye on 12/28/2017.
 */

public class Team {
    private String teamNum;
    private int matchTotal;
    ArrayList<Match> matchList = new ArrayList<>();

    public Team(){
        teamNum = "";
        matchTotal = 0;
    }
    public Team(String t){
        teamNum = t;
        matchTotal = 0;
    }

    public void setTeamNum(String n){teamNum = n;}
    public String getTeamNum(){return teamNum;}

    public void setMatchNum(int m){matchTotal = m;}
    public int getMatchNum(){return matchTotal;}

    public double getPntAvg(){
        double total = 0.00;
        if(matchList.size()!=0){
            for(Match match: matchList){
                total += match.getScore();
            }
            total = total/matchList.size();
        }
        return total;
    }

    public ArrayList<Match> getMatchList(){return matchList;}

    public void addMatch(Match match){
        matchList.add(match);
    }

    public void incrementMatchTotal(){
        matchTotal++;
    }
}
