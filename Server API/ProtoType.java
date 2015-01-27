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

class Topic {
	public int id;
	public String topic = "";
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