package myapps.com.pbl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by shaswat on 9/3/18.
 */

public class WelcomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView logo=(ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.drawable.rit_logo);

        Toast.makeText(this, "Loading App...", Toast.LENGTH_SHORT).show();

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
