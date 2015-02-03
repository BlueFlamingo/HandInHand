import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperQuestion;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;

public class testQuestion {
	
	public static void main(String[] args) {
		HelperQuestion helperquestion = new HelperQuestion();
		
		 //helperquestion.uploadFile("demo.jpg");
		
		ArrayList<Integer> topics = new ArrayList<Integer>();
		topics.add(1); 
		topics.add(2);
		//test add
		Question q =new Question(5,"hello",1,2,35,20, "demo.jpg","demo",topics);
		int qid = helperquestion.add(q);
		System.out.print(qid);
		ArrayList<Integer> topics1 = new ArrayList<Integer>();
		topics1.add(1); 
		Question q2 = new Question(6,"cat",1,2,35,20, "demo.jpg","demo",topics1);
		int qid2 = helperquestion.add(q2);
		System.out.print(qid2);
		
		//test update
		Question q1 = new Question(5,"world",1,2,35,20, "demo.jpg","demo",topics);
		int num = helperquestion.update(q1);
		System.out.print(num);
		
		//test delete
		int num1 = helperquestion.delete(5);
		System.out.print(num1);
		
		//test getByTopic
		ArrayList<Question> ques1 = new ArrayList<Question>();
		ArrayList<Question> ques2 = new ArrayList<Question>();
		ques1 = helperquestion.getByTopic(1);
		ques2 = helperquestion.getByTopic(2);
		System.out.print(ques1);
		System.out.print(ques2);
		
		//test getByQid
		Question q3 = new Question();
		q3 = helperquestion.getByQid(40);
		System.out.print(q3);
		
		
		
		
		
		
		
	}
	
}
