import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.UserHelper;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Message;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExQuestion;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExAnswer;


public class testUser {
	
	public static void main(String[] args) {
		UserHelper helperuser = new UserHelper();
		
		//helperuser.uploadFile("demo.jpg");
		
		User u1 = new User(1,"jay_ah","jay","123sdfa",0,"i",0,"asd",0,"I am jay");
		User u2= new User(35,"alice","amy","a",0,"i",2,"asd",0,"Iamamy");
		
		Integer res = helperuser.register(u1);
		Integer res2 = helperuser.update(u2);
		Integer res3 = helperuser.count("alice");
		Integer res4 = helperuser.authenticate("jay_ah", "123");
		
		User u =new User();
		u = helperuser.getbasic("alice");
		
		Integer res5 = helperuser.countQuestions(35);
		
		ArrayList<ExQuestion> q = new ArrayList<ExQuestion>();
		q = helperuser.getQuestions(35);
		System.out.print(q);
		Integer res6 = helperuser.countAnswers(35);
		
		ArrayList<ExAnswer> a = new ArrayList<ExAnswer>();
		a = helperuser.getAnswers(35);
		System.out.print(a);
		
		
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
		System.out.print(m3);
		
		
		
		
		
	}
	
}
