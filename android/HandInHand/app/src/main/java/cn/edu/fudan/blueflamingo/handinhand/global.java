package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

public class Global extends Application {

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

	public void setUid(int uid) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.putInt("uid", uid);
		mEditor.apply();
	}

	public int getUid() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getInt("uid", -1);
	}

	public void setNickname(String nickname) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString("nickname", nickname);
		editor.apply();
	}

	public String getNickname() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getString("nickname", "e");
	}
}
