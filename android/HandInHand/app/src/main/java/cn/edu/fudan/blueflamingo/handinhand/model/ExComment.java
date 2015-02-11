package cn.edu.fudan.blueflamingo.handinhand.model;

public class ExComment extends Comment {
	public String nickname = "";
	public String userHead = "";

	public String getNickname() {
		return this.nickname;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public ExComment(String content, String nickname) {
		this.nickname = nickname;
		this.content = content;
	}

	public ExComment(int uid,long createdTime, String content,int aid,String nickname,String userHead) {
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
		this.nickname = nickname;
		this.userHead = userHead;
	}

	public ExComment(){
	}
}
