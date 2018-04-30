package saim.com.navanahr;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import saim.com.navanahr.Utils.SharedPrefDatabase;

public class MainActivity extends AppCompatActivity {

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        haveStoragePermission();

        Log.d("SAIM SERVER LAT LON", new SharedPrefDatabase(getApplicationContext()).RetrivePROJECT_LAT() + " " + new SharedPrefDatabase(getApplicationContext()).RetrivePROJECT_LON());

        new Handler().post(updateLocation);
    }

    Runnable updateLocation = new Runnable() {
        @Override
        public void run() {
            gps = new GPSTracker(MainActivity.this);
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                Log.d("SAIM POSITION", "Lat : " + latitude + "\nLon : " + longitude);


                double sLat = Double.parseDouble(new SharedPrefDatabase(getApplicationContext()).RetrivePROJECT_LAT());
                double sLatMax = (double) (sLat + 0.000300);
                double sLatMin = (double) (sLat - 0.000300);
                double sLon = Double.parseDouble(new SharedPrefDatabase(getApplicationContext()).RetrivePROJECT_LON());
                double sLonMax = (double) (sLon + 0.000300);
                double sLonMin = (double) (sLon - 0.000300);
                Log.d("SAIM POSITION MAX MIN", "Lat Max: " + sLatMax + "\nLat Min : " + sLatMin + "\nLon Max: " + sLonMax + "\nLon Min : " + sLonMin);
                /*if ( (latitude <= sLatMax && latitude >= sLatMin) && (longitude <= sLatMax && longitude >= sLonMin ) ) {
                    Toast.makeText(getApplicationContext(), "Your Location is in office" + longitude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Your Location is not in office" + longitude, Toast.LENGTH_LONG).show();
                }*/

                if (latitude < sLatMax && latitude > sLatMin) {
                    if (longitude < sLonMax && longitude > sLonMin) {
                        Toast.makeText(getApplicationContext(), "Your Location is in office", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your Location is not not in office2", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Your Location is not in office1", Toast.LENGTH_LONG).show();
                }


            }else{
                gps.showSettingsAlert();
            }

            new Handler().postDelayed(updateLocation, 5000);
        }
    };


    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
}
