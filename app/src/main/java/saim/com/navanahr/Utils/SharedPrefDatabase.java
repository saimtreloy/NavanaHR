package saim.com.navanahr.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.BoringLayout;

public class SharedPrefDatabase {

    public static final String KEY_LOGIN_STATUS = "KEY_LOGIN_STATUS";
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_DESIGNATION = "KEY_DESIGNATION";
    public static final String KEY_DEPARTMENT = "KEY_DEPARTMENT";
    public static final String KEY_PROJECT_NAME = "KEY_PROJECT_NAME";


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    public SharedPrefDatabase(Context ctx) {
        this.context = ctx;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = sharedPreferences.edit();
    }

    public void StoreLOGIN_STATUS(Boolean data){
        editor.putBoolean(KEY_LOGIN_STATUS, data);
        editor.commit();
    }
    public Boolean RetriveLOGIN_STATUS(){
        Boolean text = sharedPreferences.getBoolean(KEY_LOGIN_STATUS, false);
        return text;
    }

    public void StoreID(String data){
        editor.putString(KEY_ID, data);
        editor.commit();
    }
    public String RetriveID(){
        String text = sharedPreferences.getString(KEY_ID, null);
        return text;
    }

    public void StoreNAME(String data){
        editor.putString(KEY_NAME, data);
        editor.commit();
    }
    public String RetriveNAME(){
        String text = sharedPreferences.getString(KEY_NAME, null);
        return text;
    }

    public void StoreDESIGNATION(String data){
        editor.putString(KEY_DESIGNATION, data);
        editor.commit();
    }
    public String RetriveDESIGNATION(){
        String text = sharedPreferences.getString(KEY_DESIGNATION, null);
        return text;
    }

    public void StoreDEPARTMENT(String data){
        editor.putString(KEY_DEPARTMENT, data);
        editor.commit();
    }
    public String RetriveDEPARTMENT(){
        String text = sharedPreferences.getString(KEY_DEPARTMENT, null);
        return text;
    }

    public void StorePROJECT_NAME(String data){
        editor.putString(KEY_PROJECT_NAME, data);
        editor.commit();
    }
    public String RetrivePROJECT_NAME(){
        String text = sharedPreferences.getString(KEY_PROJECT_NAME, null);
        return text;
    }

}
