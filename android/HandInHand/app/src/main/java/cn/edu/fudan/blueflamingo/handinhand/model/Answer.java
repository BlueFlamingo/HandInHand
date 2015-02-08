package cn.edu.fudan.blueflamingo.handinhand.model;

public class Answer {

	public int id = -1;
	public String content = "";
	public int score1 = 0;
	public int score2 = 0;
	public int uid = 0;
	public int qid = 0;
	public long createdTime = 0;
	public String picture = "";
	public int parentAid = 0;

	//added
	//TODO:need to be removed
	public String username = "";

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

	public long getCreatedTime() {
		return this.createdTime;
	}

	public String getPicture() {
		return this.picture;
	}

	//TODO:need to be removed
	public String getUsername() {
		return this.username;
	}

	//only for test
	@Deprecated
	public Answer(String content, int uid) {
		this.content = content;
		this.uid = uid;
	}

	public Answer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.qid = qid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.parentAid = parentAid;
	}

	public Answer(){
	}

	public String toString() {
		String str = "aid=" + id;
		return str;
	}

}
