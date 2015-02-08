package cn.edu.fudan.blueflamingo.handinhand.model;

public class Comment {
	public int id = -1;
	public int uid = 0;
	public long createdTime = 0;
	public int parentCid = 0;
	public String content = "";
	public int aid = 0;

	//TODO:need to be removed
	public String username;
	public String portrait;

	public int getId() {
		return this.id;
	}

	public int getUid() {
		return this.uid;
	}

	public long getCreatedTime() {
		return this.createdTime;
	}

	public int getParentCid() {
		return this.parentCid;
	}

	public String getContent() {
		return this.content;
	}

	public int getAid() {
		return this.aid;
	}

	//TODO:need to be removed
	public String getUsername() {
		return this.username;
	}
	public String getPortrait() {
		return this.portrait;
	}

	public Comment(int id,int uid,long createdTime,int parentCid, String content,int aid){
		this.id = id;
		this.uid = uid;
		this.createdTime = createdTime;
		this. parentCid =  parentCid;
		this.content = content;
		this.aid = aid;
	}

	public Comment(){
	}

	public String toString() {
		String str = "cid=" + id;
		return str;
	}
}
