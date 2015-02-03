package cn.edu.fudan.blueflamingo.handinhand.model;

public class Answer {

	private int id = -1;
	private String content = "";
	private int score1 = 0;
	private int score2 = 0;
	private int uid = 0;
	private int qid = 0;
	private int createdTime = 0;
	private String picture = "";

	//added
	private String username = "";

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

	public int getUid() {
		return this.uid;
	}

	public int getQid() {
		return this.qid;
	}

	public int getCreatedTime() {
		return this.createdTime;
	}

	public String getPicture() {
		return this.picture;
	}

	public String getUsername() {
		return this.username;
	}

	public Answer(String content, int uid) {
		this.content = content;
		this.uid = uid;
	}

}
