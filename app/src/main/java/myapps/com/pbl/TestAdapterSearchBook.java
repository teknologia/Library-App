package myapps.com.pbl;

/**
 * Created by shaswat on 10/3/18.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class TestAdapterSearchBook
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelperSearchBook mDbHelper;

    public TestAdapterSearchBook(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelperSearchBook(mContext);
    }

    public TestAdapterSearchBook createDatabase() throws SQLException
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

    public TestAdapterSearchBook open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
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
        try
        {
            String sql ="SELECT * FROM CheckOutMemberReport";

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

    public Cursor getOneData(String attr,String name)
    {


        try
        {
            //String sql ="SELECT * FROM CheckOutMemberReport WHERE "+attr+" LIKE '%"+name+"%'";
            //String sql ="SELECT * FROM CheckOutMemberReport";

            String sql ="SELECT * FROM BookList WHERE "+attr+" LIKE '%"+name+"%'";

            Cursor mCur = mDb.rawQuery(sql,null);
        //    Cursor mCur = mDb.rawQuery("SELECT * FROM CheckOutMemberReport WHERE Code=",);
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
}