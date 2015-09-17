package com.mingzhi.samattendance.action;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * ActivityBase
 */
public abstract class ActivityBase extends FragmentActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(setContentViewResId());
		findWigetAndListener();
	}

	/**
	 *
	 * @param id
	 * @param <T>
	 * @return
	 */
	public <T extends View> T getViewById(int id) {
		return (T) findViewById(id);
	}

	public abstract int setContentViewResId();

	public abstract void findWigetAndListener();


}
