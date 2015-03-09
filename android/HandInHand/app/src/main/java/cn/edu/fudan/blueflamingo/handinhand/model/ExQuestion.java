package cn.edu.fudan.blueflamingo.handinhand.model;


import java.util.ArrayList;

/**
 * The type Ex question.
 */
public class ExQuestion extends Question {
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
     * Instantiates a new Ex question.
     *
     * @param id the id
     * @param content the content
     * @param score1 the score 1
     * @param score2 the score 2
     * @param uid the uid
     * @param createdTime the created time
     * @param picture the picture
     * @param title the title
     * @param topics the topics
     * @param nickname the nickname
     * @param userHead the user head
     */
    public ExQuestion(int id,String content,int score1,int score2,int uid,long createdTime, String picture,String title,ArrayList<Integer> topics,String nickname,String userHead) {
		super(id, content, score1, score2, uid, createdTime, picture, title, topics);
		this.nickname = nickname;
		this.userHead = userHead;
	}

    /**
     * Instantiates a new Ex question.
     *
     * @param id the id
     * @param content the content
     * @param score1 the score 1
     * @param score2 the score 2
     * @param uid the uid
     * @param createdTime the created time
     * @param expireTime the expire time
     * @param picture the picture
     * @param title the title
     * @param topics the topics
     * @param nickname the nickname
     * @param userHead the user head
     */
    public ExQuestion(int id, String content, int score1, int score2, int uid, long createdTime, long expireTime, String picture, String title, ArrayList<Integer> topics, String nickname, String userHead) {
		super(id, content, score1, score2, uid, createdTime, picture, title, topics);
		this.nickname = nickname;
		this.userHead = userHead;
		this.expireTime = expireTime;
	}

    /**
     * Instantiates a new Ex question.
     */
    public ExQuestion(){
	}
}
