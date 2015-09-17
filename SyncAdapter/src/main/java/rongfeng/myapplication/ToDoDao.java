package rongfeng.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Chen on 2015/8/31.
 */
public class ToDoDao {

    private static final ToDoDao instance = new ToDoDao();

    public ToDoDao() {
    }

    public static ToDoDao getInstance() {
        return instance;
    }

    public void addNewTodo(ContentResolver cr, ToDo todo, int flag) {
        ContentValues cv = getTodoContentValues(todo, flag);
        cr.insert(MineContentProvider.CONTENT_URI, cv);
    }

    private ContentValues getTodoContentValues(ToDo todo, int flag) {

        ContentValues cv = new ContentValues();
        cv.put(MineDBOpenHelper.COLUMN_SERVER_ID, todo.getId());
        cv.put(MineDBOpenHelper.COLUMN_TITLE, todo.getTitle());
        cv.put(MineDBOpenHelper.COLUMN_STATUS_FLAG, flag);

        return cv;

    }


    public int deleteTodo(ContentResolver cr, Long id) {
        int ret = 0;
        switch (getTodoStatus(cr,id)){
            case StatusFlag.ADD:
                ret = cr.delete(MineContentProvider.CONTENT_URI,MineDBOpenHelper.COLUMN_ID +"="+id,null);
                break;
            case StatusFlag.MOD:
            case StatusFlag.CLEAN:
                ContentValues cv =new ContentValues();
                cv.put(MineDBOpenHelper.COLUMN_STATUS_FLAG,StatusFlag.DELETE);
                cr.update(MineContentProvider.CONTENT_URI,cv,MineDBOpenHelper.COLUMN_ID+"+"+id,null);
                break;
            default:
                throw new RuntimeException("Tried to delete a todo with invalid status");
        }
        return ret;
    }

    public int getTodoStatus(ContentResolver cr, Long id) {
        Cursor c = cr.query(MineContentProvider.CONTENT_URI, null, MineDBOpenHelper.COLUMN_ID + "=" + id, null, null);
        int status = 0;
        if (c.moveToFirst()) {
            status = c.getInt(c.getColumnIndexOrThrow(MineDBOpenHelper.COLUMN_STATUS_FLAG));
        } else {
            throw new RuntimeException("Tried to delete a non existent todo");
        }
        c.close();
        return status;
    }

}
