package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Ex comment.
 */
public class ExComment extends Comment {
    /**
     * The Nickname.
     */
    public String nickname = "";
    /**
     * The User head.
     */
    public String userHead = "";

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
		return this.nickname;
	}

    /**
     * Gets user head.
     *
     * @return the user head
     */
    public String getUserHead() {
		return this.userHead;
	}

    /**
     * Instantiates a new Ex comment.
     *
     * @param content the content
     * @param nickname the nickname
     */
    public ExComment(String content, String nickname) {
		this.nickname = nickname;
		this.content = content;
	}

    /**
     * Instantiates a new Ex comment.
     *
     * @param uid the uid
     * @param createdTime the created time
     * @param content the content
     * @param aid the aid
     * @param nickname the nickname
     * @param userHead the user head
     */
    public ExComment(int uid,long createdTime, String content,int aid,String nickname,String userHead) {
		this.uid = uid;
		this.createdTime = createdTime;
		this.content = content;
		this.aid = aid;
		this.nickname = nickname;
		this.userHead = userHead;
	}

    /**
     * Instantiates a new Ex comment.
     */
    public ExComment(){
	}
}
