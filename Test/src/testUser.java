import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperUser;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;

public class testUser {
	public static void main(String[] args) {
		User u1 = new User(1,"alice","alice","123sdfa",0,"i",0,"asd",0,"Ialice");
		User u2= new User();
		
		Integer res = HelperUser.register(u1);
		Integer res2 = HelperUser.register(u2);
		User u =new User();
		u = HelperUser.getbasic("alice");
		System.out.print(res);
		System.out.print(res2);
		System.out.print(u);
	}
}
