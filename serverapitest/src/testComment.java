import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.CommentHelper;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Question;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Comment;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ExComment;

public class testComment {
	
	public static void main(String[] args) {
		CommentHelper helpercomment = new CommentHelper();
		
		//helpercomment.uploadFile("demo.jpg");
		
		//test add
		Comment c = new Comment(1,2,30,3,"well",40);
		int cid = helpercomment.add(c);
		System.out.print(cid);
		
		//test update
		Comment c1 = new Comment(36,2,30,3,"good",40);
		int num = helpercomment.update(c1);
		System.out.print(num);
		
		//test delete
		int num1 = helpercomment.delete(37);
		System.out.print(num1);
				
		//test getByAid
		ArrayList<ExComment> c2 = helpercomment.getByAid(40);
		System.out.print(c2);
		
		//test getByCid
		ExComment c3 = helpercomment.getByCid(20);
		System.out.print(c3);
		
		
		
		
		
		
		
	}
	
}

