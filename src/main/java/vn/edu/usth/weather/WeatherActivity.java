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

import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.material.tabs.TabLayout;
import android.view.Menu;
import android.app.ProgressDialog;

import android.view.MenuInflater;
import android.os.AsyncTask;

public class WeatherActivity extends AppCompatActivity {

    MediaPlayer player;


    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            // This method is executed in main thread
            String content = msg.getData().getString("server_response");
            Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
        }
    };


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

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread. sleep(5000);
                }
                catch (InterruptedException e) {
                    e. printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putString("server-response", "some sample json here");

                Message msg = new Message();
                msg. setData(bundle);
                handler. sendMessage(msg);
            }
        });
        t. start();


    }
    private class AsyncTaskRunner extends AsyncTask<String,String,String> {
        private String r;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            // do some preparation here, if needed
            progressDialog = ProgressDialog.show(WeatherActivity.this,
                    "Updating weather...",
                    "Wait for 5 seconds!");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
                r = "Sleep for 5 seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                r = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                r = e.getMessage();
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // Assume that we got our data from server
            Bundle bundle = new Bundle();
            bundle.putString("server_response", "some sample json here");
            // notify main thread
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        @Override
        protected void onProgressUpdate(String... text) {
            // Do something here
        }

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
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute("5000");
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
