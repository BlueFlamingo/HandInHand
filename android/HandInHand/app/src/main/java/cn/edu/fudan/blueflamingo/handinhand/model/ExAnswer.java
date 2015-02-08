package cn.edu.fudan.blueflamingo.handinhand.model;

public class ExAnswer extends Answer{
	public String username = "";
	public String userHead = "";

	public String getUsername() {
		return this.username;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public ExAnswer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid,String username,String userHead) {
		super(id, content, score1, score2, uid, qid, createdTime, picture, parentAid);
		this.parentAid = parentAid;
		this.username = username;
		this.userHead = userHead;
	}

	public ExAnswer(){
	}
}
