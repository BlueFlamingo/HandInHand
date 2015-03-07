import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.FavoriteHelper;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Message;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExQuestion;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExAnswer;
public class testFavorite {

	public static void main(String[] args) {
		
		FavoriteHelper favoritehelper = new FavoriteHelper();
		
		//test isQuestionFavorite
		int num1 = favoritehelper.isQuestionFavorite(36, 40);
		System.out.print(num1);
		
		//test isAnswerFavorite
		int num2 = favoritehelper.isAnswerFavorite(36, 57);
		System.out.print(num2);
		
		//test switchQuestionFavorite
		int num3 = favoritehelper.switchQuestionFavorite(36, 40);
		System.out.print(num3);
		
		//test switchAnswerFavorite
		int num4 = favoritehelper.switchAnswerFavorite(36, 57);
		System.out.print(num4);
				
		//test listQuestions
		ArrayList<ExQuestion> exqlist = favoritehelper.listQuestions(36);
		System.out.print(exqlist);

		//test listAnswers
		ArrayList<ExAnswer> exalist = favoritehelper.listAnswers(36);
		System.out.print(exalist);
		
		//test isUserFavorite
		int num5 = favoritehelper.isUserFavorite(3, 4);
		System.out.print(num5);
		
		//test switchUserFavorite
		int num6 = favoritehelper.switchUserFavorite(3, 4);
		System.out.print(num6);
		
		//test listUsers
		ArrayList<User> ulist1 = favoritehelper.listUsers(3);
		
		System.out.print(ulist1);

	}
}
