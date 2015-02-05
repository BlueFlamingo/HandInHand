package cn.edu.fudan.blueflamingo.handinhand.model;

public class User {

	private int id = -1;
	private String username = "";
	private String nickname = "";
	private String password = "";
	private int score1 = 0;
	private String portrait = "";
	private int score2 = 0;
	private String remark = "";
	private int male = 0;
	private String signature = "";

	public int getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public int getScore1() {
		return this.score1;
	}

	public int getScore2() {
		return this.score2;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public String getRemark() {
		return this.remark;
	}

	public int getMale() {
		return this.male;
	}

	public String getSignature() {
		return this.signature;
	}

	public User(String username, String nickname, String password) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}
}
