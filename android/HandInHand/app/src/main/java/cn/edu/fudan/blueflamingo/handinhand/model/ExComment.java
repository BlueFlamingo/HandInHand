package cn.edu.fudan.blueflamingo.handinhand.model;

public class ExComment extends Comment {
	public String username = "";
	public String userHead = "";

	public String getUsername() {
		return this.username;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public ExComment(int id,int uid,long createdTime,int parentCid, String content,int aid,String username,String userHead) {
		super(id, uid, createdTime, parentCid, content, aid);
		this.username = username;
		this.userHead = userHead;
	}

	public ExComment(){
	}
}
