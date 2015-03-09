package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

/**
 * The type Global.
 */
public class Global extends Application {

    /**
     * Gets login flag.
     *
     * @return the login flag
     */
    public boolean getLoginFlag() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getBoolean("loginFlag", false);
	}

    /**
     * Sets login flag.
     *
     * @param flag the flag
     */
    public void setLoginFlag(boolean flag) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.putBoolean("loginFlag", flag);
		mEditor.apply();
	}

    /**
     * Gets first open flag.
     *
     * @return the first open flag
     */
    public boolean getFirstOpenFlag() {
        SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
        return mPrefs.getBoolean("openFlag", true);
    }

    /**
     * Sets first open flag.
     *
     * @param flag the flag
     */
    public void setFirstOpenFlag(boolean flag) {
        SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean("openFlag", flag);
        mEditor.apply();
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(int uid) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.putInt("uid", uid);
		mEditor.apply();
	}

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public int getUid() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getInt("uid", -1);
	}

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString("nickname", nickname);
		editor.apply();
	}

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		return mPrefs.getString("nickname", "e");
	}
}
