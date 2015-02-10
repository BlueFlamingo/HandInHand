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

	public ExComment(int id,int uid,long createdTime,int parentCid, String content,int aid,String nickname,String userHead) {
		super(id, uid, createdTime, parentCid, content, aid);
		this.nickname = nickname;
		this.userHead = userHead;
	}

	public ExComment(){
	}
}
