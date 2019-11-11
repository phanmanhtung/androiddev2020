package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("abc","create");
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
