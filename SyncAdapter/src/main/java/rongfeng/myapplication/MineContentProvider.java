package rongfeng.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Chen on 2015/8/31.
 */
public class MineContentProvider extends ContentProvider {

    public static final String AUTHORITY = MineContentProvider.class.getCanonicalName();
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + MineDBOpenHelper.TABLE_NAME);
    public static final String DEFAULT_SORT_ORDER = "_id ASC";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/rongfeng.myapplication.MineContentProvider/todos";
    public static final String CONTENT_TYPE_ID = "vnd.android.cursor.item/rongfeng.myapplication.MineContentProvider/todos";
    private static final UriMatcher uriMatcher;
    private static final int TODO = 1;
    private static final int TODO_ID = 2;
    private static HashMap<String, String> projectionMap;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, MineDBOpenHelper.TABLE_NAME, TODO);
        uriMatcher.addURI(AUTHORITY, MineDBOpenHelper.TABLE_NAME + "/#", TODO_ID);

        projectionMap = new HashMap<>();
        projectionMap.put(MineDBOpenHelper.COLUMN_ID, MineDBOpenHelper.COLUMN_ID);
        projectionMap.put(MineDBOpenHelper.COLUMN_SERVER_ID, MineDBOpenHelper.COLUMN_SERVER_ID);
        projectionMap.put(MineDBOpenHelper.COLUMN_TITLE, MineDBOpenHelper.COLUMN_TITLE);
        projectionMap.put(MineDBOpenHelper.COLUMN_STATUS_FLAG, MineDBOpenHelper.COLUMN_STATUS_FLAG);
    }

    private MineDBOpenHelper dbOpenHelper;

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MineDBOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case TODO:
                qb.setTables(MineDBOpenHelper.TABLE_NAME);
                qb.setProjectionMap(projectionMap);
                break;
            case TODO_ID:
                qb.setTables(MineDBOpenHelper.TABLE_NAME);
                qb.setProjectionMap(projectionMap);
                qb.appendWhere(MineDBOpenHelper.COLUMN_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new RuntimeException("Unknown URI");
        }

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case TODO:
                return CONTENT_TYPE;
            case TODO_ID:
                return CONTENT_TYPE_ID;
            default:
                throw new RuntimeException("Unknown URI");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        ContentValues mContentValues;
        if (values != null) {
            mContentValues = new ContentValues(values);
        } else {
            mContentValues = new ContentValues();
        }
        String table = null;
        String nullableCol = null;
        switch (uriMatcher.match(uri)) {
            case TODO:
                table = MineDBOpenHelper.TABLE_NAME;
                nullableCol = MineDBOpenHelper.COLUMN_ID;
                break;
        }
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long rowId = db.insert(table, nullableCol, mContentValues);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int count;
        switch (uriMatcher.match(uri)){
            case TODO:
                count =db.delete(MineDBOpenHelper.TABLE_NAME,selection,selectionArgs);
                break;
            case TODO_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(MineDBOpenHelper.TABLE_NAME,MineDBOpenHelper.COLUMN_ID+"="+id+(!TextUtils.isEmpty(selection)?"AND (" + selection +")":""),selectionArgs);
                break;
            default:
                throw new RuntimeException("Unkown : "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
