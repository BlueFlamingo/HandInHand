package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

import java.util.ArrayList;

public class ExQuestion extends Question{
	public String nickname = "";
	public String userHead = "";
	public ExQuestion(int id,String content,int score1,int score2,int uid,long createdTime, String picture,String title,ArrayList<Integer> topics,String nickname,String userHead){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.title = title;
		this.topics = topics;
		this.nickname = nickname;
		this.userHead = userHead;
	}
	public ExQuestion(){
	}
}
