package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type User.
 */
public class User {

    /**
     * The Id.
     */
    public int id = -1;
    /**
     * The Username.
     */
    public String username = "";
    /**
     * The Nickname.
     */
    public String nickname = "";
    /**
     * The Password.
     */
    public String password = "";
    /**
     * The Score 1.
     */
    public int score1 = 0;
    /**
     * The Portrait.
     */
    public String portrait = "";
    /**
     * The Score 2.
     */
    public int score2 = 0;
    /**
     * The Remark.
     */
    public String remark = "";
    /**
     * The Male.
     */
    public int male = 0;		//0 for male, 1 for female
    /**
     * The Signature.
     */
    public String signature = "";

    /**
     * Instantiates a new User.
     *
     * @param id the id
     * @param username the username
     * @param nickname the nickname
     * @param password the password
     * @param score1 the score 1
     * @param score2 the score 2
     * @param portrait the portrait
     * @param remark the remark
     * @param signature the signature
     */
    public User(int id, String username, String nickname, String password, int score1, int score2,
				String portrait, String remark, String signature) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.score1 = score1;
		this.score2 = score2;
		this.portrait = portrait;
		this.remark = remark;
		this.signature = signature;
	}

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
		return this.id;
	}

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
		return this.username;
	}

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
		return this.nickname;
	}

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
		return this.password;
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
     * Gets portrait.
     *
     * @return the portrait
     */
    public String getPortrait() {
		return this.portrait;
	}

    /**
     * Gets remark.
     *
     * @return the remark
     */
    public String getRemark() {
		return this.remark;
	}

    /**
     * Gets male.
     *
     * @return the male
     */
    public int getMale() {
		return this.male;
	}

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
		return this.signature;
	}

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param nickname the nickname
     * @param password the password
     */
    public User(String username,String nickname,String password){
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}

    /**
     * Instantiates a new User.
     */
    public User(){
	}

    /**
     * Equals boolean.
     *
     * @param u1 the u 1
     * @param u2 the u 2
     * @return the boolean
     */
    public static boolean equals(User u1, User u2) {
		return u1.portrait.equals(u2.portrait)
				&& u1.signature.equals(u2.signature)
				&& u1.male == u2.male
				&& u1.nickname.equals(u2.nickname);
	}
}
