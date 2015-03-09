package cn.edu.fudan.blueflamingo.handinhand.model;

/**
 * The type Message.
 */
public class Message {

    /**
     * The Id.
     */
    public int id;
    /**
     * The Sender.
     */
    public int sender;
    /**
     * The Receiver.
     */
    public int receiver;
    /**
     * The Title.
     */
    public String title = "";
    /**
     * The Content.
     */
    public String content = "";
    /**
     * The Created time.
     */
    public long createdTime = 0;
    /**
     * The Status.
     */
    public int status = 0;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
		return this.id;
	}

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public int getSender() {
		return this.sender;
	}

    /**
     * Gets receiver.
     *
     * @return the receiver
     */
    public int getReceiver() {
		return this.receiver;
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
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
		return this.content;
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
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
		return this.status;
	}

    /**
     * Instantiates a new Message.
     *
     * @param id the id
     * @param sender the sender
     * @param receiver the receiver
     * @param title the title
     * @param content the content
     * @param createdTime the created time
     * @param status the status
     */
    public Message(int id,int sender,int receiver,String title,String content,long createdTime,int status){
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.status = status;
	}

    /**
     * Instantiates a new Message.
     */
    public Message(){
	}

	public String toString() {
		String str = "mid=" + id;
		return str;
	}
}
