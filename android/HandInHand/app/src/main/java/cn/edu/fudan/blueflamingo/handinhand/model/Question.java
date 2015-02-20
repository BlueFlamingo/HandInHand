package cn.edu.fudan.blueflamingo.handinhand.model;

import java.util.ArrayList;

public class Question {

	public int id = -1;
	public String content = "";
	public int score1 = 0;			//点赞，对于问题只要用score1就行了
	public int score2 = 0;			//点踩
	public int uid = -1;
	public long createdTime = 0;
	public long expireTime = 0;
	public String picture = "";
	public String title = "";
	public ArrayList<Integer> topics = new ArrayList<>();

	public int getId() {
		return this.id;
	}

	public int getUid() {
		return this.uid;
	}

	public String getContent() {
		return this.content;
	}

	public int getScore1() {
		return this.score1;
	}

	public int getScore2() {
		return this.score2;
	}

	public long getCreatedTime() {
		return this.createdTime;
	}

	public String getPicture() {
		return this.picture;
	}

	public String getTitle() {
		return this.title;
	}

	public ArrayList<Integer> getTopics() {
		return this.topics;
	}

	public Question(String title, String content, int uid, long createdTime, ArrayList<Integer> topics) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.topics = topics;
	}

	public Question(String title, String content, int uid, long createdTime, long expireTime, ArrayList<Integer> topics) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.topics = topics;
		this.expireTime = expireTime;
	}

	public Question(int id,String content,int score1,int score2,int uid,long createdTime, String picture,String title,ArrayList<Integer> topics){
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

	public String toString() {
		String str = "qid=" + id;
		return str;
	}
}
