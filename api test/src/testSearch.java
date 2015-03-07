import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.SearchHelper;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Message;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExQuestion;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExAnswer;

public class testSearch {
	public static void main(String[] args) {
		SearchHelper searchhelper = new SearchHelper();
		
		//test Search
				ArrayList< ArrayList<Object> >  list = searchhelper.Search("q");
				System.out.print(list);
	}
}
