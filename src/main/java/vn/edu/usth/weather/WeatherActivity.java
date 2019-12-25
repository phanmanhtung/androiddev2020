package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.util.Log;
import android.media.MediaPlayer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.os.Environment;
import android.content.res.AssetFileDescriptor;


import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("abc", "create");

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        copyFileToExternalStorage(R.raw.mydearest, "mydearest.mp3");

        player = MediaPlayer.create(WeatherActivity.this, R.raw.mydearest);
        player.start();

    }

    private void copyFileToExternalStorage(int resourceId, String resourceName) {
        String pathSDCard = Environment.getExternalStorageDirectory()
                + "/Android/data/vn.edu.usth.weather/" + resourceName;
        try {
            InputStream in = getResources().openRawResource(resourceId);
            FileOutputStream out = null;
            out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    protected void onStart(){
        super.onStart();
        Log.i("abc","start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("abc","resume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("abc","pause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("abc","stop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("abc","destroy");
    }
}
