package myapps.com.pbl;

/**
 * Created by shaswat on 10/3/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class TestAdapterWishlist
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelperWishlist mDbHelper;

    public TestAdapterWishlist(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelperWishlist(mContext);
    }

    public TestAdapterWishlist createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapterWishlist open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            //mDbHelper.close();
            //mDb = mDbHelper.getWritableDatabase();
            mDb=mDbHelper.getmDataBase();
            //Toast.makeText(mContext, ""+mDbHelper.getDatabaseName(), Toast.LENGTH_SHORT).show();


        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getTestData()
    {
        //mDb.execSQL("insert into MyWishlist values('2','def');");
        //mDb.execSQL("delete from MyWishlist;");
        try
        {
            String sql ="SELECT * FROM MyWishlist;";

            Cursor mCur = mDb.rawQuery(sql, null);
        /*
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
        */
            return mCur;

        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void insertOneData(String name)
    {

        if(name.equals("") || name==null){
            Toast.makeText(mContext, "Field cannot be Left Blank!", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor cursor=mDb.rawQuery("select * from MyWishlist where book_id='"+name+"';",null);
        Log.i(TAG, "insertOneData: "+cursor.getCount());
        if(cursor.getCount()>0){
            Toast.makeText(mContext, "Already in Wishlist!", Toast.LENGTH_SHORT).show();
            return;

        }
            
        try
        {
            //String sql ="SELECT * FROM CheckOutMemberReport WHERE "+attr+" LIKE '%"+name+"%'";
            //String sql ="SELECT * FROM CheckOutMemberReport";
            //Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
            String sql ="SELECT Title FROM BookList WHERE Accn_No='"+name+"';";

            DataBaseHelperSearchBook booksfinder=new DataBaseHelperSearchBook(mContext);
            SQLiteDatabase books=booksfinder.getReadableDatabase();
            Cursor mCur = books.rawQuery(sql,null);
            //    Cursor mCur = mDb.rawQuery("SELECT * FROM CheckOutMemberReport WHERE Code=",);
        /*
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
        */
            if(mCur.getCount()==0){
                Toast.makeText(mContext, "No Such Book Found!", Toast.LENGTH_SHORT).show();
                return;
            }

            mCur.moveToNext();
            String bookname=mCur.getString(0);
            //Log.i(TAG, "insertOneData: "+bookname);
            ContentValues values=new ContentValues();
            values.put("book_id",name);
            values.put("book_name",bookname);

            long l=mDb.insertOrThrow("MyWishlist",null,values);
            Toast.makeText(mContext, "Inserted in Wishlist!", Toast.LENGTH_SHORT).show();

            //Log.i(TAG, "insertOneData: "+l);
            //mDb.execSQL("insert into MyWishlist values('"+name+"','"+bookname+"');");
            //return mCur;

        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void deleteOneData(String name)
    {

        if(name.equals("") || name==null){
            Toast.makeText(mContext, "Field cannot be Left Blank!", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor cursor=mDb.rawQuery("select * from MyWishlist where book_id='"+name+"';",null);
        Log.i(TAG, "insertOneData: "+cursor.getCount());
        if(cursor.getCount()==0){
            Toast.makeText(mContext, "No Such Book in Wishlist!!", Toast.LENGTH_SHORT).show();
            return;

        }

        mDb.execSQL("delete from MyWishlist where book_id='"+name+"';");
        Toast.makeText(mContext, "Removed from Wishlist!", Toast.LENGTH_SHORT).show();
        
    }
}