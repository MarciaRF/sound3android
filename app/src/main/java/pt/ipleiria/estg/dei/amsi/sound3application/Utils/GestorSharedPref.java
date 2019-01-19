package pt.ipleiria.estg.dei.amsi.sound3application.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.sound3application.Activitys.LoginSignUpActivity;

/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class GestorSharedPref {

    //the constants
    private static final String NOME_SHARED_PREF = "gestorsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_ID = "keyid";

    private static GestorSharedPref mInstance;
    private static Context mCtx;

    private GestorSharedPref(Context context) {
        mCtx = context;
    }

    public static synchronized GestorSharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GestorSharedPref(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(ArrayList<String> user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(NOME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_ID, Integer.parseInt(user.get(0)));
        editor.putString(KEY_USERNAME, user.get(1));
        editor.putString(KEY_EMAIL, user.get(2));
        editor.apply();
        System.out.println("-------->"+sharedPreferences.contains(KEY_USERNAME));
    }

    //this method will check whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(NOME_SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public ArrayList<String> getUser() {
        ArrayList<String> tempList = new ArrayList<String>();
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(NOME_SHARED_PREF, Context.MODE_PRIVATE);
        tempList.add(""+sharedPreferences.getLong(KEY_ID, -1));
        tempList.add(sharedPreferences.getString(KEY_USERNAME, null));
        tempList.add(sharedPreferences.getString(KEY_EMAIL, null));

        return tempList;
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(NOME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginSignUpActivity.class));
    }
}
