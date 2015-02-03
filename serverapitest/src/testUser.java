import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperUser;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;

public class testUser {
	
	public static void main(String[] args) {
		HelperUser helperuser = new HelperUser();
		
		helperuser.uploadFile("demo.jpg");
		
		User u1 = new User(1,"jay_ah","jay","123sdfa",0,"i",0,"asd",0,"I am jay");
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
		ArrayList<User> ulist = new ArrayList<User>();
		ulist = helperuser.getByUid(35);
		System.out.print(ulist);
	}
	
}
