package cn.edu.fudan.blueflamingo.handinhand.model;

import java.util.ArrayList;

/**
 * The type Question.
 */
public class Question {

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
    public int score1 = 0;			//点赞，对于问题只要用score1就行了
    /**
     * The Score 2.
     */
    public int score2 = 0;			//点踩
    /**
     * The Uid.
     */
    public int uid = -1;
    /**
     * The Created time.
     */
    public long createdTime = 0;
    /**
     * The Expire time.
     */
    public long expireTime = 0;
    /**
     * The Picture.
     */
    public String picture = "";
    /**
     * The Title.
     */
    public String title = "";
    /**
     * The Topics.
     */
    public ArrayList<Integer> topics = new ArrayList<>();

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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
		return this.title;
	}

    /**
     * Gets topics.
     *
     * @return the topics
     */
    public ArrayList<Integer> getTopics() {
		return this.topics;
	}

    /**
     * Instantiates a new Question.
     *
     * @param title the title
     * @param content the content
     * @param uid the uid
     * @param createdTime the created time
     * @param topics the topics
     */
    public Question(String title, String content, int uid, long createdTime, ArrayList<Integer> topics) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.topics = topics;
	}

    /**
     * Instantiates a new Question.
     *
     * @param title the title
     * @param content the content
     * @param uid the uid
     * @param createdTime the created time
     * @param expireTime the expire time
     * @param topics the topics
     */
    public Question(String title, String content, int uid, long createdTime, long expireTime, ArrayList<Integer> topics) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.createdTime = createdTime;
		this.topics = topics;
		this.expireTime = expireTime;
	}

    /**
     * Instantiates a new Question.
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
     */
    public Question(int id,String content,int score1,int score2,int uid,long createdTime, String picture,String title,ArrayList<Integer> topics){
		this.id = id;
		this.content = content;
		this.score1 = score1;
		this.score2 = score2;
		this.uid = uid;
		this.createdTime = createdTime;
		this. picture =  picture;
		this.title = title;
		this.topics = topics;
	}

    /**
     * Instantiates a new Question.
     */
    public Question(){
	}

	public String toString() {
		String str = "qid=" + id;
		return str;
	}
}
