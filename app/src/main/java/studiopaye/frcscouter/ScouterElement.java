package studiopaye.frcscouter;

/**
 * Created by Joel Paye on 12/30/2017.
 */

public class ScouterElement {
    private String name;
    private int mImageResource;
    private int mMinutes;
    private int mSeconds;

    public ScouterElement(String n, int i, int m, int s){
        name = n;
        mImageResource = i;
        mMinutes = m;
        mSeconds = s;
    }

    public void setName(String n){name = n;}
    public String getName(){return name;}

    public void setImageResource(int i){mImageResource = i;}
    public int getImageResource(){return mImageResource;}

    public void setMinutes(int m){mMinutes = m;}
    public int getMinutes(){return mMinutes;}

    public void setSeconds(int s){mSeconds = s;}
    public int getSeconds(){return mSeconds;}
}
