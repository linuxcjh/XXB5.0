package rongfeng.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.w3c.dom.Text;

/**
 * Created by Chen on 2015/8/31.
 */
public class MainAdapter extends CursorAdapter {

    private static final String[] PROJECTION_IDS_TITLE_AND_STATUS = new String[]{
            MineDBOpenHelper.COLUMN_ID, MineDBOpenHelper.COLUMN_TITLE, MineDBOpenHelper.COLUMN_STATUS_FLAG
    };
    private LayoutInflater mInflater;
//    private int mTitleIndex;
//    private int mInternalIdIndex;
//    private int mInternalStatusIndex;
    private Activity mActivity;

    public MainAdapter(Activity activity) {
        super(activity, getManagedCursor(activity), true);
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        final Cursor c = getCursor();

    }

    private static Cursor getManagedCursor(Activity activity) {
        return activity.managedQuery(MineContentProvider.CONTENT_URI, PROJECTION_IDS_TITLE_AND_STATUS, MineDBOpenHelper.COLUMN_STATUS_FLAG + " !=" + StatusFlag.DELETE, null, MineContentProvider.DEFAULT_SORT_ORDER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.id = (TextView) view.findViewById(R.id.id);
        holder.title = (TextView) view.findViewById(R.id.title);
        holder.deleteBt = (Button) view.findViewById(R.id.delete);

        view.setTag(holder);
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();
      int  mInternalIdIndex = cursor.getColumnIndexOrThrow(MineDBOpenHelper.COLUMN_ID);
        int  mTitleIndex = cursor.getColumnIndexOrThrow(MineDBOpenHelper.COLUMN_TITLE);
        int mInternalStatusIndex = cursor.getColumnIndexOrThrow(MineDBOpenHelper.COLUMN_STATUS_FLAG);
        holder.id.setText(cursor.getString(mInternalIdIndex));
        holder.title.setText(cursor.getString(mTitleIndex));
        int status = cursor.getInt(mInternalStatusIndex);
        if (StatusFlag.CLEAN != status) {
            holder.title.setBackgroundColor(Color.RED);
        } else {
            holder.title.setBackgroundColor(Color.GREEN);
        }
        final Long id = Long.valueOf(holder.id.getText().toString());
        holder.deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoDao.getInstance().deleteTodo(mActivity.getContentResolver(), id);
            }
        });

    }

    private static class ViewHolder {
        TextView id;
        TextView title;
        Button deleteBt;
    }
}
