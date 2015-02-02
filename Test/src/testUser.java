import cn.edu.fudan.blueflamingo.HandinHandServerHelper.HelperUser;
import cn.edu.fudan.blueflamingo.HandinHandServerHelper.User;

public class testUser {
	public static void main(String[] args) {
		HelperUser helperuser = new HelperUser();
		User u1 = new User(1,"jay_ah","jay","123sdfa",0,"i",0,"asd",0,"I am jay");
		User u2= new User(5,"alice","amycd","a",0,"i",0,"asd",0,"Iamamy");
		
		Integer res = helperuser.register(u1);
		Integer res2 = helperuser.update(u2);
		Integer res3 = helperuser.count("alice");
		Integer res4 = helperuser.authenticate("jay_ah", "123sdfa");
		User u =new User();
		u = helperuser.getbasic("alice");
		System.out.print(res);
		System.out.print(res2);
		System.out.print(res3);
		System.out.print(res4);
		System.out.print(u);
	}
}
