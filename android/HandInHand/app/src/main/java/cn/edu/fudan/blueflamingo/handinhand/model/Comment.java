package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Comment.
 */
public class Comment {
    /**
     * The Id.
     */
    public int id = -1;
    /**
     * The Uid.
     */
    public int uid = 0;
    /**
     * The Created time.
     */
    public long createdTime = 0;
    /**
     * The Parent cid.
     */
    public int parentCid = 0;
    /**
     * The Content.
     */
    public String content = "";
    /**
     * The Aid.
     */
    public int aid = 0;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
		return this.id;
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
     * Gets created time.
     *
     * @return the created time
     */
    public long getCreatedTime() {
		return this.createdTime;
	}

    /**
     * Gets parent cid.
     *
     * @return the parent cid
     */
    public int getParentCid() {
		return this.parentCid;
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
     * Gets aid.
     *
     * @return the aid
     */
    public int getAid() {
		return this.aid;
	}

    /**
     * Instantiates a new Comment.
     *
     * @param uid the uid
     * @param createdTime the created time
     * @param content the content
     * @param aid the aid
     */
    public Comment(int uid,long createdTime, String content,int aid){
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
	}

    /**
     * Instantiates a new Comment.
     */
    public Comment(){
	}

	public String toString() {
		String str = "cid=" + id;
		return str;
	}
}
