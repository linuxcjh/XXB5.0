package rongfeng.myapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chen on 2015/8/31.
 */
public class MineDBOpenHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "todo.do";
    public static final String TABLE_NAME="todos";
    public static final  String COLUMN_ID = "_id";
    public static final  String COLUMN_SERVER_ID = "server_id";
    public static final  String COLUMN_TITLE = "title";
    public static final  String COLUMN_STATUS_FLAG = "status_flag";
    private static final int DATABASE_VERSION = 1;

    public MineDBOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  " +COLUMN_SERVER_ID +" INTEGER " + COLUMN_TITLE +" LONGTEXT " + COLUMN_STATUS_FLAG + " INTEGER " + " ); " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
