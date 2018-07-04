package studiopaye.frcscouter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Joel Paye on 12/30/2017.
 */

public class User {
    public static ArrayList<Team> teamList = new ArrayList<>();
    public static int matchesScouted;

    public static void addTeam(Team team){
        teamList.add(team);
    }

    public static void addTeam(String teamnum){
        teamList.add(new Team(teamnum));
    }

    public static void saveData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(teamList);
        editor.putString("task list", json);
        editor.apply();
    }

    public static void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Team>>(){}.getType();
        teamList = gson.fromJson(json, type);

        if(teamList == null){
            teamList = new ArrayList<>();
        }
    }
}
