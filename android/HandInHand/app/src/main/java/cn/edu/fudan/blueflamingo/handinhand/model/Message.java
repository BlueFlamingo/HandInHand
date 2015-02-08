package cn.edu.fudan.blueflamingo.handinhand.model;

public class Message {

	public int id;
	public int sender;
	public int receiver;
	public String title = "";
	public String content = "";
	public long createdTime = 0;
	public int status = 0;

	public int getId() {
		return this.id;
	}

	public int getSender() {
		return this.sender;
	}

	public int getReceiver() {
		return this.receiver;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public long getCreatedTime() {
		return this.createdTime;
	}

	public int getStatus() {
		return this.status;
	}

	public Message(int id,int sender,int receiver,String title,String content,long createdTime,int status){
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

	public String toString() {
		String str = "mid=" + id;
		return str;
	}
}
