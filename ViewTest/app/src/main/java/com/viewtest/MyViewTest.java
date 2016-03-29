package com.viewtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MyViewTest extends Activity {
    CircleProgress circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag", -1);
        switch (flag) {

            case 3:

                setContentView(R.layout.circle_progress);

                 circle = (CircleProgress) findViewById(R.id.circle);

                 circle.setSweepValue(1);
                new ProgressAnimation().execute();



                break;
            case 4:

                setContentView(R.layout.volume);





                break;


        }
    }

    class ProgressAnimation extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 1; i <100; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            circle.setSweepValue(values[0]);
            circle.setShowText(values[0]);
            System.out.println(values[0] + "-------------------");
            super.onProgressUpdate(values);
        }
    }

}
