package cn.edu.fudan.blueflamingo.handinhand.model;


import java.util.ArrayList;

public class ExQuestion extends Question {
	public String username = "";
	public String userHead = "";

	public String getUsername() {
		return this.username;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public ExQuestion(int id,String content,int score1,int score2,int uid,long createdTime, String picture,String title,ArrayList<Integer> topics,String username,String userHead) {
		super(id, content, score1, score2, uid, createdTime, picture, title, topics);
		this.username = username;
		this.userHead = userHead;
	}

	public ExQuestion(){
	}
}
