package studiopaye.frcscouter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Joel Paye on 12/30/2017.
 */

public class Match {
    private int matchid;
    private String matchnum;
    private int score;
    private String side;
    ArrayList<ScouterElement> elementList = new ArrayList<>();

    public Match(){
        score = 0;
        side = "Blue";
        matchid = 0;
        matchnum = "0";
    }

    public Match(int s, String a, String n){
        score = s;
        side = a;
        matchnum = n; //WILL CHANGE
    }

    //public void generateID //create id based on elements of the scouter, time, teamnum, matchnum, etc

    public void setMatchnum(String t){matchnum = t;}
    public String getMatchnum(){return matchnum;}

    public void setMatchid(int m){matchid = m;}
    public int getMatchid(){return matchid;}

    public void setScore(int s){score = s;}
    public int getScore(){return score;}

    public void setSide(String a){side = a;}
    public String getSide(){return side;}

    public ArrayList<ScouterElement> getElementList(){return elementList;}
}
