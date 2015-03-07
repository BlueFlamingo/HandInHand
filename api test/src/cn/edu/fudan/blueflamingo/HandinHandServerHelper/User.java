package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class User {
	public int id;
	public String username = "";
	public String nickname = "";
	public String password = "";
	public int score1 = 0;
	public String portrait = "";
	public int score2 = 0;
	public String remark = "";
	public int male = 0;
	public String signature = "";
	
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
