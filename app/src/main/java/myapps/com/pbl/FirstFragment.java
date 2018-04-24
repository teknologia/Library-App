package myapps.com.pbl;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shaswat on 9/3/18.
 */

/*
public class FirstFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.first_layout,container,false);
        return myView;
    }
}
*/

public class FirstFragment extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        TextView textView =(TextView)findViewById(R.id.textView2);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.youtube.com/watch?v=6ZfuNTqbHE8'> Click Here to Watch our Tutorial Video! </a>";
        textView.setText(Html.fromHtml(text));
    }
}
