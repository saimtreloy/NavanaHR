package saim.com.navanahr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {
        setTheme(R.style.AppThemeSplash);

        configSplash.setBackgroundColor(R.color.colorPrimaryDark); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1500); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Landing);

        configSplash.setTitleSplash("");
        configSplash.setTitleTextSize(24f);
        configSplash.setAnimTitleDuration(50);
        configSplash.setTitleTextColor(R.color.colorAccent);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
