package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Answer header.
 */
//the full question
public class AnswerHeader {
	private String title = "";
	private String description = "";
	private int watchNum = 0;
	private int qid;

    /**
     * Instantiates a new Answer header.
     */
    public AnswerHeader() {

	}

    /**
     * Instantiates a new Answer header.
     *
     * @param title the title
     * @param description the description
     * @param watchNum the watch num
     * @param qid the qid
     */
    public AnswerHeader(String title, String description, int watchNum, int qid) {
		this.title = title;
		this.description = description;
		this.watchNum = watchNum;
		this.qid = qid;
	}

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
		return this.title;
	}

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
		return this.description;
	}

    /**
     * Gets watch num.
     *
     * @return the watch num
     */
    public int getWatchNum() {
		return this.watchNum;
	}

    /**
     * Gets qid.
     *
     * @return the qid
     */
    public int getQid() {
		return this.qid;
	}

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
		this.title = title;
	}

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Sets watch num.
     *
     * @param watchNum the watch num
     */
    public void setWatchNum(int watchNum) {
		this.watchNum = watchNum;
	}

}
