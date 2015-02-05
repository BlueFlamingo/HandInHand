import java.io.File;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.HandinHandServerHelper.Answer;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.ScoreHelper;
public class testScore {
	public static void main(String[] args) {
	ScoreHelper scorehelper = new ScoreHelper();
	
	        //test isApprove
			int num7 = scorehelper.isApprove(3, 4);
			System.out.print(num7);
			
			//test isOppose
			int num8 = scorehelper.isOppose(3, 4);
			System.out.print(num8);
			
			//test switchApprove
			int num9 =scorehelper.switchApprove(3, 4);
			System.out.print(num9);
			
			//test switchOppose
			int num10 = scorehelper.switchOppose(3, 4);
			System.out.print(num10);
}
}
