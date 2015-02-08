package cn.edu.fudan.blueflamingo.handinhand.model;

public class User {

	public int id = -1;
	public String username = "";
	public String nickname = "";
	public String password = "";
	public int score1 = 0;
	public String portrait = "";
	public int score2 = 0;
	public String remark = "";
	public int male = 0;
	public String signature = "";

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

	//only for test
	@Deprecated
	public User(String username, String nickname, String password) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}

	public User(int id,String username,String nickname,String password,int score1,String portrait, int score2,String remark,int male,String signature){
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.score1 = score1;
		this.portrait = portrait;
		this.score2 = score2;
		this.remark = remark;
		this.male = male;
		this.signature = signature;
	}

	public User(){
	}
}
