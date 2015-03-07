import java.util.ArrayList;


public class ProtoType {
	
}


class User {
	
	public int id;
	public String username = "";
	public String nickname = "";
	public String password = "";
	public int score1 = 0;
	public String portrait = "";
	public int score2 = 0;
	public String remark = "";
	public int male = 0;
	public String signature = "";
	
}

class Question {
	
	public int id;
	public String content = "";
	public int score1 = 0;
	public int score2 = 0;
	public int uid;
	public int createdTime = 0;
	public String picture = "";
	public String title = "";
	
	public ArrayList<Integer> topics = new ArrayList<Integer>();
	
}

class Answer {
	
	public int id;
	public String content = "";
	public int score1 = 0;
	public int score2 = 0;
	public int uid = 0;
	public int qid = 0;
	public int createdTime = 0;
	public String picture = "";
	public int parentAid = 0;
	
}

class Comment {
	
	public int id;
	public int uid = 0;
	public int createdTime = 0;
	public int parentCid = 0;
	public String content = "";
	public int aid = 0;
	
}

class Message {
	
	public int id;
	public int sender;
	public int receiver;
	public String title = "";
	public String content = "";
	public int createdTime = 0;
	public int status = 0; // 0未读 1已读
	
}