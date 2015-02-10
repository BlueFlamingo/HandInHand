package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class ExAnswer extends Answer{
	public String nickname = "";
	public String userHead = "";
	public ExAnswer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid,String nickname,String userHead){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.qid = qid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.parentAid = parentAid;
		this.nickname = nickname;
		this.userHead = userHead;
	}
	public ExAnswer(){
	}
}
