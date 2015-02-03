import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperAnswer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;

public class testAnswer {
	
	public static void main(String[] args) {
		HelperAnswer helperanswer = new HelperAnswer();
		
		helperanswer.uploadFile("demo.jpg");
		
		//test add
		Answer a =new Answer(5,"dog",2,3,35,40,20, "demo",0);
		int aid = helperanswer.add(a);
		System.out.print(aid);
		
		//test update
		Answer a1 =new Answer(5,"cat",2,3,35,40,20, "demo",0);
		int num = helperanswer.update(a1);
		System.out.print(num);
		
		//test delete
		int num1 = helperanswer.delete(5);
		System.out.print(num1);
				
		//test getByQid
		ArrayList<Answer> a2 = new ArrayList<Answer>();
		a2 = helperanswer.getByQid(40);
		System.out.print(a2);
		
		
		//test getByQid
		ArrayList<Answer> a3 = new ArrayList<Answer>();
		a3 = helperanswer.getByAid(49);
		System.out.print(a3);
				
		
		
		
		
		
	}
	
}

