package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Answer.
 */
public class Answer {

    /**
     * The Id.
     */
    public int id = -1;
    /**
     * The Content.
     */
    public String content = "";
    /**
     * The Score 1.
     */
    public int score1 = 0;
    /**
     * The Score 2.
     */
    public int score2 = 0;
    /**
     * The Uid.
     */
    public int uid = 0;
    /**
     * The Qid.
     */
    public int qid = 0;
    /**
     * The Created time.
     */
    public long createdTime = 0;
    /**
     * The Picture.
     */
    public String picture = "";
    /**
     * The Parent aid.
     */
    public int parentAid = 0;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
		return this.id;
	}

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
		return this.content;
	}

    /**
     * Gets score 1.
     *
     * @return the score 1
     */
    public int getScore1() {
		return this.score1;
	}

    /**
     * Gets score 2.
     *
     * @return the score 2
     */
    public int getScore2() {
		return this.score2;
	}

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public int getUid() {
		return this.uid;
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
     * Gets created time.
     *
     * @return the created time
     */
    public long getCreatedTime() {
		return this.createdTime;
	}

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
		return this.picture;
	}

    /**
     * Instantiates a new Answer.
     *
     * @param content the content
     * @param uid the uid
     * @param qid the qid
     * @param createdTime the created time
     */
    public Answer(String content, int uid, int qid, long createdTime) {
		this.content = content;
		this.uid = uid;
		this.qid = qid;
		this.createdTime = createdTime;
	}

    /**
     * Instantiates a new Answer.
     *
     * @param id the id
     * @param content the content
     * @param score1 the score 1
     * @param score2 the score 2
     * @param uid the uid
     * @param qid the qid
     * @param createdTime the created time
     * @param picture the picture
     * @param parentAid the parent aid
     */
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

    /**
     * Instantiates a new Answer.
     */
    public Answer(){
	}

	public String toString() {
		String str = "aid=" + id;
		return str;
	}

}
