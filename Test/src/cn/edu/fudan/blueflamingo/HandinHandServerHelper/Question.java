package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

import java.util.ArrayList;

public class Question {
	public int id;
	public String content = "";
	public int score1 = 0;
	public int score2 = 0;
	public int uid;
	public int createdTime = 0;
	public String picture = "";
	public String title = "";
	
	public ArrayList<Integer> topics = new ArrayList<Integer>();
	
	public Question(int id,String content,int score1,int score2,int uid,int createdTime, String picture,String title,ArrayList<Integer> topics){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.title = title;
		this.topics = topics;
	}
	public Question(){
	}
}
