package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

public class Message {
	public int id;
	public int sender;
	public int receiver;
	public String title = "";
	public String content = "";
	public int createdTime = 0;
	public int status = 0; // 0Î´¶Á 1ÒÑ¶Á
	
	public Message(int id,int sender,int receiver,String title,String content,int createdTime,int status){
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.status = status;
	}
	public Message(){
	}
}
