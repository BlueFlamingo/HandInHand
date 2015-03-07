package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class ExComment extends Comment{
	public String nickname = "";
	public String userHead = "";
	public ExComment(int id,int uid,long createdTime,int parentCid, String content,int aid,String nickname,String userHead){
		this.id = id;
		this.uid = uid;
		this.createdTime = createdTime;
		this. parentCid =  parentCid;
		this.content = content;
		this.aid = aid;
		this.nickname = nickname;
		this.userHead =userHead;
	}
	public ExComment(){
	}
}
