package myapps.com.pbl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by shaswat on 8/5/18.
 */

public class MadeInIndia extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_made_in_india);

        ImageView mii=(ImageView)findViewById(R.id.mii_pic);
        mii.setImageResource(R.drawable.l12);
    }
}
