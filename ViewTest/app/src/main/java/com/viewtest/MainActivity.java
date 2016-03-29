package com.viewtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, MyViewTest.class);
    }
    public void cicle(View view){
        mIntent.putExtra("flag", 3);
        startActivity(mIntent);
    }

    public void titlebar(View view){
        startActivity(new Intent(this, TitleBarTest.class));
    }
    public void btnVolumeView(View view) {
        mIntent.putExtra("flag", 4);
        startActivity(mIntent);
    }
}
