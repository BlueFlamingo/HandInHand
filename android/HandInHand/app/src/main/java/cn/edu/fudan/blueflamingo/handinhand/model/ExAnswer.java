package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Ex answer.
 */
public class ExAnswer extends Answer{
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
     * Instantiates a new Ex answer.
     *
     * @param content the content
     */
    public ExAnswer(String content) {
		this.content = content;
	}

    /**
     * Instantiates a new Ex answer.
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
     * @param nickname the nickname
     * @param userHead the user head
     */
    public ExAnswer(int id,String content,int score1,int score2,int uid,int qid,long createdTime, String picture,int parentAid,String nickname,String userHead) {
		super(id, content, score1, score2, uid, qid, createdTime, picture, parentAid);
		this.parentAid = parentAid;
		this.nickname = nickname;
		this.userHead = userHead;
	}

    /**
     * Instantiates a new Ex answer.
     */
    public ExAnswer(){
	}
}
