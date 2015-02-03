import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperUser;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Message;


public class testUser {
	
	public static void main(String[] args) {
		HelperUser helperuser = new HelperUser();
		
		//helperuser.uploadFile("demo.jpg");
		
		/*User u1 = new User(1,"jay_ah","jay","123sdfa",0,"i",0,"asd",0,"I am jay");
		User u2= new User(35,"alice","amy","a",0,"i",2,"asd",0,"Iamamy");
		
		Integer res = helperuser.register(u1);
		Integer res2 = helperuser.update(u2);
		Integer res3 = helperuser.count("alice");
		Integer res4 = helperuser.authenticate("jay_ah", "123");
		
		User u =new User();
		u = helperuser.getbasic("alice");
		
		Integer res5 = helperuser.countQuestions(35);
		
		ArrayList<Question> q = new ArrayList<Question>();
		q = helperuser.getQuestions(35);
		
		Integer res6 = helperuser.countAnswers(35);
		
		ArrayList<Answer> a = new ArrayList<Answer>();
		a = helperuser.getAnswers(35);
		
		
		
		System.out.print(res);
		System.out.print(res2);
		System.out.print(res3);
		System.out.print(res4);
		System.out.print(res5);
		System.out.print(res6);
		System.out.print(u);
		System.out.print(q);
		System.out.print(a);
		
		//test getByUid
		User user = new User();
		user = helperuser.getByUid(35);
		System.out.print(user);
		
		       //test showQuestionStatus
				int num1 = helperuser.showQuestionStatus(36, 40);
				System.out.print(num1);
				
				//test showAnswerStatus
				int num2 = helperuser.showAnswerStatus(36, 57);
				System.out.print(num2);
				
				//test toggleQuestion
				int num3 = helperuser.toggleQuestion(36, 40);
				System.out.print(num3);
				
				//test toggleAnswer
				int num4 = helperuser.toggleAnswer(36, 57);
				System.out.print(num4);
						
				//test listQuestions
				ArrayList<Integer> qlist = new ArrayList<Integer>();
				qlist = helperuser.listQuestions(40);
				System.out.print(qlist);

				//test listAnswers
				ArrayList<Integer> alist = new ArrayList<Integer>();
				alist = helperuser.listAnswers(57);
				System.out.print(alist);
				
				//test showUserStatus
				int num5 = helperuser.showUserStatus(3, 4);
				System.out.print(num5);
				
				//test toggleUser
				int num6 = helperuser.toggleUser(3, 4);
				System.out.print(num6);
				
				//test listUsers
				ArrayList<Integer> ulist1 = new ArrayList<Integer>();
				ulist1 = helperuser.listUsers(3);
				System.out.print(ulist1);
		
		//test showScore1Status
		int num7 = helperuser.showScore1Status(3, 4);
		System.out.print(num7);
		
		//test showScore2Status
		int num8 = helperuser.showScore2Status(3, 4);
		System.out.print(num8);
		
		//test toggleScore1
		int num9 = helperuser.toggleScore1(3,4);
		System.out.print(num9);
		
		//test toggleScore2
		int num10 = helperuser.toggleScore2(3, 4);
		System.out.print(num10);
		
		//test SendMsgs
		Message m = new Message(6,3,4,"hello","hello",20,0);
		int num11 = helperuser.SendMsgs(m);
		System.out.print(num11);
		
		//test getSentMsgs
		ArrayList<Message> m1 = new ArrayList<Message>();
		m1 = helperuser.getSentMsgs(6);
		System.out.print(m1);
		
		//test getAllReceivedMsgs
		ArrayList<Message> m2 = new ArrayList<Message>();
		m2 = helperuser.getAllReceivedMsgs(3);
		System.out.print(m2);
		
		//test getUnhandledMsgs
		ArrayList<Message> m3 = new ArrayList<Message>();
		m3 = helperuser.getUnhandledMsgs(5);
		System.out.print(m3);*/
		
		//test Search
		ArrayList< ArrayList<Integer> >  list = helperuser.Search("hello");
		System.out.print(list);
		
		
		
	}
	
}
