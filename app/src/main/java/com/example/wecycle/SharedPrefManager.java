package com.example.wecycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.wecycle.model.Student;

public class SharedPrefManager {

    // The constants
    private static final String sharedPref = "UserSharedPref";
    private static final String stuID = "keyStuID";
    private static final String nic = "keyNIC";
    private static final String firstName = "keyFName";
    private static final String lastName = "keyLName";
    private static final String mobNumber = "keyMobNum";
    private static final String email = "keyEmail";
    private static final String status = "keyStatus";

    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    // This method will store the user data in shared preferences
    public void userLogin(Student student) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(sharedPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(stuID, student.getStuID());
        editor.putString(nic, student.getNic());
        editor.putString(firstName, student.getFirstName());
        editor.putString(lastName, student.getLastName());
        editor.putString(mobNumber, student.getMobNumber());
        editor.putString(email, student.getEmail());
        editor.putString(status, student.getStatus());
        editor.apply();
    }

    // This method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(sharedPref, Context.MODE_PRIVATE);
        return sharedPreferences.getString(stuID, null) != null;
    }

    // This method will give the logged in user
    public Student getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(sharedPref, Context.MODE_PRIVATE);
        return new Student(
                sharedPreferences.getString(stuID, null),
                sharedPreferences.getString(nic, null),
                sharedPreferences.getString(firstName, null),
                sharedPreferences.getString(lastName, null),
                sharedPreferences.getString(mobNumber, null),
                sharedPreferences.getString(email, null),
                sharedPreferences.getString(status, null)
        );
    }

    // This method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(sharedPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

}
