package cn.edu.fudan.blueflamingo.handinhand.model;

public class Comment {
	private int id = -1;
	private int uid = 0;
	private int createdTime = 0;
	private int parentCid = 0;
	private String content = "";
	private int aid = 0;

	private String username;
	private String portrait;

	public int getId() {
		return this.id;
	}

	public int getUid() {
		return this.uid;
	}

	public int getCreatedTime() {
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

	public String getUsername() {
		return this.username;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public Comment(int id, int uid, int createdTime, String content, int aid) {
		this.id = id;
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
	}

	public Comment(int id, int uid, int createdTime, String content, int aid, String username) {
		this.id = id;
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
		this.username = username;
	}

	public Comment(int id, int uid, int createdTime, String content, int aid, String username, String portrait) {
		this.id = id;
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
		this.username = username;
		this.portrait = portrait;
	}
}
