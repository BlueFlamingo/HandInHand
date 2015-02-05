package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class Comment {
	public int id;
	public int uid = 0;
	public long createdTime = 0;
	public int parentCid = 0;
	public String content = "";
	public int aid = 0;
	
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
}
