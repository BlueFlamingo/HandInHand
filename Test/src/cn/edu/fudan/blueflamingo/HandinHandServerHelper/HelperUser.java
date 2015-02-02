package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class HelperUser {

	public static Integer register(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String entry = "";
			try {
				entry = mapper.writeValueAsString(u);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String temp;
			Integer uid;
			temp = sendGet(url, "op=register&entry=" + entry);
			uid = Integer.valueOf(temp);
			return uid;
		  }
		
	    public void update(User u){
	    String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String entry = "";
			try {
				entry = mapper.writeValueAsString(u);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	        sendGet(url, "op=update&entry=" + entry);
	    }

	    public Integer count(String username){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			String temp;
			Integer count;
			temp = sendGet(url, "op=count&username=" + username);
			count = Integer.valueOf(temp);
			return count;
		
		}
	 	
		public Integer authenticate(String username, String password){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			
			String temp;
			Integer ismatch;
			temp = sendGet(url, "authenticate&username=" + username + "&password=" + password);
			ismatch = Integer.valueOf(temp);
			return ismatch;
		}
		//???
		public static User getbasic(String username){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			
			String temp;
			User user = new User();
			ObjectMapper mapper1 = new ObjectMapper();
			temp = sendGet(url, "op=get&username=" + username);
			System.out.print(temp);
			try {
				user = mapper1.readValue(temp, new TypeReference<User>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;
		
		}
		
		public Integer countQuestions(int id){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			String uid = String.valueOf(id);
			String temp;
			Integer countQuestions;
			temp = sendGet(url, "op=countQuestions&uid=" + uid);
			countQuestions = Integer.valueOf(temp);
			return countQuestions;
		}
		
		public ArrayList<Question> getQuestions(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String uid = "";
			try {
				uid = mapper.writeValueAsString(u.id);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String temp;
			
			ArrayList<Question> questions = new ArrayList<Question>();
			
			temp = sendGet(url, "op=getQuestions&uid=" + uid);
			
			ObjectMapper mapper1 = new ObjectMapper();
			try {
				questions = mapper1.readValue(temp, new TypeReference<ArrayList<Question>>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return questions;
		}
		
		public Integer countAnswers(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String uid = "";
			try {
				uid = mapper.writeValueAsString(u.id);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String temp;
			Integer countAnswers;
			temp = sendGet(url, "op=countAnswers&uid=" + uid);
			countAnswers = Integer.valueOf(temp);
			return countAnswers;
		}
		
		public ArrayList<Answer> getAnswers(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String uid = "";
			try {
				uid = mapper.writeValueAsString(u.id);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String temp;
			
			ArrayList<Answer> answers = new ArrayList<Answer>();
			
			temp = sendGet(url, "op=getQuestions&uid=" + uid);
			
			ObjectMapper mapper1 = new ObjectMapper();
			
			try {
				answers = mapper1.readValue(temp, new TypeReference<ArrayList<Answer>>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return answers;
		}
		
		public static String sendGet(String url, String param) {
	        String result = "";
	        System.out.println(param);
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + "?" + param;
	            System.out.println(urlNameString);
	            URL realUrl = new URL(urlNameString);
	            // �򿪺�URL֮�������
	            URLConnection connection = realUrl.openConnection();
	            // ����ͨ�õ���������
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // ����ʵ�ʵ�����
	            connection.connect();
	            // ��ȡ������Ӧͷ�ֶ�
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // �������е���Ӧͷ�ֶ�
	            for (String key : map.keySet()) {
	                //System.out.println(key + "--->" + map.get(key));
	            }
	            // ���� BufferedReader����������ȡURL����Ӧ
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("����GET��������쳣��" + e);
	            e.printStackTrace();
	        }
	        // ʹ��finally�����ر�������
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        
	        System.out.println(result);
	        return result;
	    }
}
