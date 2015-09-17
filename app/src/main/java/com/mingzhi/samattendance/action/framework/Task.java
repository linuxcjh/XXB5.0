package com.mingzhi.samattendance.action.framework;

import android.os.AsyncTask;

/**
 * 
 * @file : Task.java [V 1.0.0]
 * 
 * @author: 陈建辉
 * 
 * @time : CREAT AT 2013-6-18 上午10:59:10
 * 
 * @TODO : 【框架中所有AsyncTask任务的基类】
 * 
 */
public class Task<R, S> extends AsyncTask<R, S, Object[]> {
	
	private ITaskListener m_listener;

	private int m_id;
	

	public Task(ITaskListener listener) {
		m_listener = listener;
	}

	protected ITaskListener getListener() {
		return m_listener;
	}

	/**
	 * 获取任务ID
	 *
	 * @return
	 */
	public int getId() {
		return m_id;
	}

	/**
	 * 设置任务ID
	 *
	 * @param id
	 */
	public void setId(int id) {
		m_id = id;
	}

	@Override
	protected void onPreExecute() {
		m_listener.onTaskPreExecute(this);
	}

	@Override
	protected void onPostExecute(Object[] result) {
		m_listener.onTaskPostExecute(this, result);
	}

	@Override
	protected void onProgressUpdate(S... values) {
		m_listener.onTaskProgressUpdate(this, values[0].toString());
	}

	@Override
	protected Object[] doInBackground(R... values) {
		return null;
	}

}
