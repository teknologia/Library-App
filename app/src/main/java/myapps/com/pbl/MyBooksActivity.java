package myapps.com.pbl;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

/**
 * Created by shaswat on 9/3/18.
 */

public class MyBooksActivity extends Activity {

    Button bcode;
    EditText e;
    String name;
    TestAdapterMyBook mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_book);

        bcode=(Button)findViewById(R.id.button_my_book);
        e=(EditText)findViewById(R.id.my_code);
        name=e.getText().toString();

        mDbHelper = new TestAdapterMyBook(this);
        viewOne();
    }

    void viewOne(){

    bcode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            name=e.getText().toString().toUpperCase();
            Toast.makeText(MyBooksActivity.this, name, Toast.LENGTH_SHORT).show();

            //showMessage("Hello","World");
            mDbHelper.createDatabase();
            mDbHelper.open();

            Cursor testdata = mDbHelper.getOneData("Code",name);

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

                String sqlissuedate="SELECT DATE('1899-12-30', '"+testdata.getString(7)+" days') as issuedate";
                //DataBaseHelperMyBooks issd=new DataBaseHelperMyBooks(getBaseContext()).rawQuery(sqldate,null);
                SQLiteDatabase db=new DataBaseHelperMyBooks(getBaseContext()).getReadableDatabase();
                Cursor issd=db.rawQuery(sqlissuedate,null);
                issd.moveToNext();

                String sqlduedate="SELECT DATE('1899-12-30', '"+testdata.getString(8)+" days') as duedate";
                //DataBaseHelperMyBooks issd=new DataBaseHelperMyBooks(getBaseContext()).rawQuery(sqldate,null);
                Cursor dued=db.rawQuery(sqlduedate,null);
                dued.moveToNext();


                buffer.append("Name: "+testdata.getString(2)+"\n");
                buffer.append("Code: "+testdata.getString(3)+"\n");
                buffer.append("Book: "+testdata.getString(4)+"\n");
                buffer.append("Author: "+testdata.getString(5)+"\n");
                buffer.append("Book Code: "+testdata.getString(6)+"\n");
                buffer.append("Issued: "+issd.getString(0)+"\n");
                buffer.append("Due Date: "+dued.getString(0)+"\n\n");

            }


            showMessage("DATA",buffer.toString());


            mDbHelper.close();

        }
    });

}

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

