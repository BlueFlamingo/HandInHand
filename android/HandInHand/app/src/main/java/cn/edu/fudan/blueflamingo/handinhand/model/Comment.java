package cn.edu.fudan.blueflamingo.handinhand.model;

public class Comment {
	public int id = -1;
	public int uid = 0;
	public long createdTime = 0;
	public int parentCid = 0;
	public String content = "";
	public int aid = 0;

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

	public Comment(int uid,long createdTime, String content,int aid){
		this.uid = uid;
		this.createdTime = createdTime;
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
