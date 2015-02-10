package cn.edu.fudan.blueflamingo.handinhand.model;

//the full question
public class AnswerHeader {
	private String title = "";
	private String description = "";
	private int watchNum = 0;
	private int qid;

	public AnswerHeader() {

	}

	public AnswerHeader(String title, String description, int watchNum, int qid) {
		this.title = title;
		this.description = description;
		this.watchNum = watchNum;
		this.qid = qid;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public int getWatchNum() {
		return this.watchNum;
	}

	public int getQid() {
		return this.qid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWatchNum(int watchNum) {
		this.watchNum = watchNum;
	}

}
