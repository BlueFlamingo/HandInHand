package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Application;

public class global extends Application {
	public static int gLogin = 0;

	public void setgLogin(int val) {
		gLogin = val;
	}

	public int getgLogin() {
		return gLogin;
	}
}
