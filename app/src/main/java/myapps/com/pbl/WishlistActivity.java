package myapps.com.pbl;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by shaswat on 9/3/18.
 */

public class WishlistActivity extends Activity {

    Button bcode,bshow,bdel;
    EditText e;
    String name;
    TestAdapterWishlist mDbHelper;
    TestAdapterSearchBook sbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        bdel=(Button)findViewById(R.id.del_wish_button);
        bshow=(Button)findViewById(R.id.show_wish_button);
        bcode = (Button) findViewById(R.id.wish_button);
        e = (EditText) findViewById(R.id.book_code);
        //name = e.getText().toString();

        sbHelper=new TestAdapterSearchBook(this);
        sbHelper.createDatabase();
        sbHelper.open();
        sbHelper.close();



        mDbHelper = new TestAdapterWishlist(this);

        mDbHelper.open();
        mDbHelper.createDatabase();

        //viewOne();
        bcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mDbHelper.open();
                //mDbHelper.createDatabase();
                //mDbHelper.close();
                name = e.getText().toString().trim();

                mDbHelper.insertOneData(name);
                e.setText("");

            }
        });

        bshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer=new StringBuffer();
                Cursor cursor=mDbHelper.getTestData();
                String colnames[]=cursor.getColumnNames();
                //Log.i(TAG, "onClick: "+colnames[0]+"\t"+colnames[1]);
                //Log.i(TAG, "onClick: "+cursor.getCount();
                while (cursor.moveToNext()){
                    Log.i(TAG, "onClick: "+cursor.getString(0));
                    Log.i(TAG, "onClick: "+cursor.getString(1));
                    buffer.append("Book Code: "+cursor.getString(0)+"\n");
                    buffer.append("Book Name: "+cursor.getString(1)+"\n\n");
                }
                showMessage("WISHLIST",buffer.toString());

            }
        });


        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = e.getText().toString().trim();

                mDbHelper.deleteOneData(name);
                e.setText("");
            }
        });
    }






    public void showMessage(String title, String Message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        //builder.setTitle(title);
        //builder.setMessage(Message);
        // Initialize a new foreground color span instance
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);

        // Initialize a new spannable string builder instance
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);

        // Apply the text color span
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Set the alert dialog title using spannable string builder
        builder.setTitle(ssBuilder);
        builder.setCancelable(true);
        //builder.setTitle(title);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.BLUE);
        SpannableStringBuilder ssBuilder2 = new SpannableStringBuilder(Message);

        //Log.i(TAG, "showMessage: "+Message.length());
        // Apply the text color span
        ssBuilder2.setSpan(
                foregroundColorSpan2,
                0,
                Message.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        );

        builder.setMessage(ssBuilder2);
        builder.show();
    }
}

