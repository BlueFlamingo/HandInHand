package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class Answer {
	public int id;
	public String content = "";
	public int score1 = 0;
	public int score2 = 0;
	public int uid = 0;
	public int qid = 0;
	public long createdTime = 0;
	public String picture = "";
	public int parentAid = 0;
	
	public Answer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.qid = qid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.parentAid = parentAid;
	}
	public Answer(){
	}
	
	public String toString() {
		String str = "aid=" + id;
		return str;
	}
}
