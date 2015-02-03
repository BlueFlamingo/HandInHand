package cn.edu.fudan.blueflamingo.handinhand.model;

public class AnswerHeader {
	private String title = "";
	private String description = "";
	private int watchNum = 0;

	public AnswerHeader() {

	}

	public AnswerHeader(String title, String description, int watchNum) {
		this.title = title;
		this.description = description;
		this.watchNum = watchNum;
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
}
