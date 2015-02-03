package cn.edu.fudan.blueflamingo.handinhand.model;

import java.util.ArrayList;

public class Question {

	private int id = -1;
	private String content = "";
	private int score1 = 0;			//点赞，对于问题只要用score1就行了
	private int score2 = 0;			//点踩
	private int uid = -1;
	private int createdTime = 0;
	private String picture = "";
	private String title = "";
	private ArrayList<Integer> topics = new ArrayList<>();

	public int getId() {
		return this.id;
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

	public int getCreatedTime() {
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

	public Question(int id, String content, int uid, int createdTime, String title, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.title = title;
		this.topics = topics;
	}

	public Question(int id, String content, int uid, int createdTime, String title, String picture, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.title = title;
		this.picture = picture;
		this.topics = topics;
	}

	public Question(int id, String content, int uid,int score1, int createdTime, String title, String picture, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.uid = uid;
		this.score1 = score1;
		this.createdTime = createdTime;
		this.title = title;
		this.picture = picture;
		this.topics = topics;
	}

	public Question(int id, String content, int uid, int score1, int createdTime, String title, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.uid = uid;
		this.createdTime = createdTime;
		this.title = title;
		this.topics = topics;
	}

	public Question(int id, String content, int score1, int score2, int uid, int createdTime, String title, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.createdTime = createdTime;
		this.title = title;
		this.topics = topics;
	}

	public Question(int id, String content, int score1, int score2, int uid, int createdTime, String picture, String title, ArrayList<Integer> topics) {
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.createdTime = createdTime;
		this.picture = picture;
		this.title = title;
		this.topics = topics;
	}
}
