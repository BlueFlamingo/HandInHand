package cn.edu.fudan.blueflamingo.handinhand.model;

public class ExAnswer extends Answer{
	public String nickname = "";
	public String userHead = "";

	public String getNickname() {
		return this.nickname;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public ExAnswer(String content) {
		this.content = content;
	}

	public ExAnswer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid,String nickname,String userHead) {
		super(id, content, score1, score2, uid, qid, createdTime, picture, parentAid);
		this.parentAid = parentAid;
		this.nickname = nickname;
		this.userHead = userHead;
	}

	public ExAnswer(){
	}
}
