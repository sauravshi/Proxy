package com.sauravshishodiasgmail.proxy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Saurav on 6/16/2015.
 */
public class HotOrNot {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_SUBJECT_CODE = "subjectscode";
    public static final String KEY_SUBJECT_NAME = "subjectsname";

    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "subjectTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper{
       public DbHelper(Context context){
           super(context,DATABASE_NAME,null,DATABASE_VERSION);
       }
       public void onCreate(SQLiteDatabase db){
           db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID +
                           " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SUBJECT_CODE +
                           " TEXT NOT NULL, " + KEY_SUBJECT_NAME + " TEXT NOT NULL);" );
        }

       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion){
           db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
           onCreate(db);
       }

    }
    public HotOrNot(Context c){
        ourContext = c;
    }

    public HotOrNot open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase =  ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }
    public long createEntry(String name, String code){
        ContentValues cv = new ContentValues();
        cv.put(KEY_SUBJECT_NAME, name);
        cv.put(KEY_SUBJECT_CODE, code);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }
}
