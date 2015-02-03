package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class ExAnswer extends Answer{
	public String username = "";
	public String userHead = "";
	public ExAnswer(int id,String content,int score1,int score2,int uid,int qid,int createdTime, String picture,int parentAid,String username,String userHead){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.qid = qid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.parentAid = parentAid;
		this.username = username;
		this.userHead = userHead;
	}
	public ExAnswer(){
	}
}
