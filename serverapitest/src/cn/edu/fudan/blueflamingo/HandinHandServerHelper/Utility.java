package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

import java.util.ArrayList;

public class Utility {
	
	public static ExAnswer answerToExAnswer(Answer atemp) {
		ExAnswer etemp = new ExAnswer();
		etemp.id = atemp.id;
		etemp.content = atemp.content;
		etemp.score1 = atemp.score1;
		etemp.score2 = atemp.score2;
		etemp.uid = atemp.uid;
		etemp.qid = atemp.qid;
		etemp.createdTime = atemp.createdTime;
		etemp. picture = atemp.picture;
		etemp.parentAid = atemp.parentAid;
		User u = new User();
		u = new HelperUser().getByUid(atemp.uid);
		etemp.username = u.username;
		etemp.userHead = u.portrait;
		return etemp;
	}
	
	public static ArrayList<ExAnswer> answerToExAnswer(ArrayList<Answer> as) {
		ArrayList<ExAnswer> eas = new ArrayList<ExAnswer>();
		for (Answer a : as) {
			eas.add(answerToExAnswer(a));
		}
		return eas;
	}
}
