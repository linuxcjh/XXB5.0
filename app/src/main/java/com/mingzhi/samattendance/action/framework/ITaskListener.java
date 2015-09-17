package com.mingzhi.samattendance.action.framework;

/**
 * Created by Chen on 2015/5/25.
 */
public interface  ITaskListener {

    public void onTaskPreExecute(Task<?, ?> task);

    public void onTaskPostExecute(Task<?, ?> task, Object[] result);

    public void onTaskProgressUpdate(Task<?, ?> task, String... values);
}
