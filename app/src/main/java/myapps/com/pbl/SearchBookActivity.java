package myapps.com.pbl;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by shaswat on 9/3/18.
 */

public class SearchBookActivity extends Activity {

    Button bname,bauthor;
    EditText e;
    String name;
    TestAdapterSearchBook mDbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_book);

        bname=(Button)findViewById(R.id.by_name);
        bauthor=(Button)findViewById(R.id.by_author);
        e=(EditText)findViewById(R.id.input);
        name=e.getText().toString();

        mDbHelper = new TestAdapterSearchBook(this);
        viewOne();


    }

    public void viewOne(){
        bname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=e.getText().toString().toUpperCase();
                if(name.equals("")||name==null){
                    Toast.makeText(SearchBookActivity.this, "Field cannot be Left Blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SearchBookActivity.this, name, Toast.LENGTH_SHORT).show();

                //showMessage("Hello","World");
                mDbHelper.createDatabase();
                mDbHelper.open();

                Cursor testdata = mDbHelper.getOneData("Title",name);

                if(testdata.getCount()==0){
                    showMessage("Error!","Nothing Found");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(testdata.moveToNext()){
           /*         buffer.append("SNo: "+testdata.getString(1)+"\n");
                    buffer.append("Name: "+testdata.getString(2)+"\n");
                    buffer.append("Code: "+testdata.getString(3)+"\n");
                    buffer.append("Category: "+testdata.getString(4)+"\n");
                    buffer.append("Group: "+testdata.getString(5)+"\n\n");
            */
                    buffer.append("Code: "+testdata.getString(1)+"\n");
                    buffer.append("Name: "+testdata.getString(2)+"\n");
                    buffer.append("Author: "+testdata.getString(3)+"\n");
                    buffer.append("Status: "+testdata.getString(8)+"\n\n");

                }

                showMessage("DATA",buffer.toString());


                mDbHelper.close();

            }
        });

        bauthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=e.getText().toString().toUpperCase();
                if(name.equals("")||name==null){
                    Toast.makeText(SearchBookActivity.this, "Field cannot be Left Blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SearchBookActivity.this, name, Toast.LENGTH_SHORT).show();

                //showMessage("Hello","World");
                mDbHelper.createDatabase();
                mDbHelper.open();

                Cursor testdata = mDbHelper.getOneData("Author",name);

                if(testdata.getCount()==0){
                    showMessage("Error!","Nothing Found");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(testdata.moveToNext()){
            /*      buffer.append("SNo: "+testdata.getString(1)+"\n");
                    buffer.append("Name: "+testdata.getString(2)+"\n");
                    buffer.append("Code: "+testdata.getString(3)+"\n");
                    buffer.append("Category: "+testdata.getString(4)+"\n");
                    buffer.append("Group: "+testdata.getString(5)+"\n\n");
             */
                    buffer.append("Code: "+testdata.getString(1)+"\n");
                    buffer.append("Name: "+testdata.getString(2)+"\n");
                    buffer.append("Author: "+testdata.getString(3)+"\n");
              //      buffer.append("Status: "+testdata.getString(8)+"\n\n");
                    buffer.append("Status: "+testdata.getString(8)+"\n\n");


                }


                showMessage("DATA",buffer.toString());


                mDbHelper.close();

            }
        });

    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);


        // Initialize a new foreground color span instance
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);

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
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.green));
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
        //builder.setMessage(Message);
        //builder.setTitle(Html.fromHtml("<font color='#FF7F27'>"+title+"</font>"));
        //builder.setMessage(Html.fromHtml("<font color='#FF7F20'>"+Message+"</font>"));
        builder.show();
    }

}

