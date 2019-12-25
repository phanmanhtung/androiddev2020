package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.media.MediaPlayer;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.os.Environment;
import android.content.res.AssetFileDescriptor;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import android.view.Menu;

import android.view.MenuInflater;

public class WeatherActivity extends AppCompatActivity {
    private Toolbar mTopToolbar;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.refresh:
                Toast.makeText(WeatherActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
        }


        return super.onOptionsItemSelected(item);
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
