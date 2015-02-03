import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperFavorite;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Comment;

public class testFavorite {
	
	public static void main(String[] args) {
		HelperFavorite helperfavorite = new HelperFavorite();
		
		helperfavorite.uploadFile("demo.jpg");
		
		//test showQuestionStatus
		int num1 = helperfavorite.showQuestionStatus(36, 40);
		System.out.print(num1);
		
		//test showAnswerStatus
		int num2 = helperfavorite.showAnswerStatus(36, 57);
		System.out.print(num2);
		
		//test toggleQuestion
		int num3 = helperfavorite.toggleQuestion(36, 40);
		System.out.print(num3);
		
		//test toggleAnswer
		int num4 = helperfavorite.toggleAnswer(36, 57);
		System.out.print(num4);
				
		//test listQuestions
		ArrayList<Integer> qlist = new ArrayList<Integer>();
		qlist = helperfavorite.listQuestions(40);
		System.out.print(qlist);

		//test listAnswers
		ArrayList<Integer> alist = new ArrayList<Integer>();
		alist = helperfavorite.listAnswers(57);
		System.out.print(alist);
		
		//test showUserStatus
		int num5 = helperfavorite.showUserStatus(3, 4);
		System.out.print(num5);
		
		//test toggleUser
		int num6 = helperfavorite.toggleUser(3, 4);
		System.out.print(num6);
		
		//test listUsers
		ArrayList<Integer> ulist = new ArrayList<Integer>();
		ulist = helperfavorite.listUsers(3);
		System.out.print(ulist);
		
		
		
		
		
		
		
	}
	
}


