package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

public class global extends Application {

	public boolean getLoginFlag() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getBoolean("loginFlag", false);
	}

	public void setLoginFlag(boolean flag) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.putBoolean("loginFlag", flag);
		mEditor.apply();
	}
}
