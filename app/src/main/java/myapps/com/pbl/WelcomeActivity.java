package myapps.com.pbl;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by shaswat on 9/3/18.
 */

public class WelcomeActivity extends Activity{


    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        mProgress=(ProgressBar)findViewById(R.id.splash_screen_progress_bar) ;
       /*
        TestAdapterSearchBook db=new TestAdapterSearchBook(getBaseContext());
        db.createDatabase();
        db.open();
        */
        ImageView logo=(ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.drawable.rit_logo);


        Toast.makeText(this, "Loading App...", Toast.LENGTH_SHORT).show();

        /*
        Thread timer=new Thread(){
            public void run(){


                try{

                    sleep(2500);

                }catch (InterruptedException e){
                    e.printStackTrace();

                }finally {

                    Intent myIntent = new Intent(WelcomeActivity.this,
                            MainActivity.class);
                    startActivity(myIntent);
                }



            }

        };
        timer.start();
        */
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }



    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(500);
                mProgress.setProgress(progress);
                Log.i(TAG, "doWork: "+mProgress.getProgress());
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
